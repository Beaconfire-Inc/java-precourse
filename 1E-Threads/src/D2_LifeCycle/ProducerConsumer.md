# `wait()` / `notify()`: The Producer-Consumer Pattern

This is the classic example for `Object.wait()` and `Object.notify()` — two threads sharing a bounded buffer, one adding items, one removing them, coordinating so the producer never overflows the buffer and the consumer never reads from an empty one.

```java
public class ProducerConsumer {
    private static final Object lock = new Object();
    private static int[] buffer; // Shared buffer
    private static int count;    // Number of items currently in the buffer

    static class Producer {
        void produce() {
            synchronized (lock) {
                while (isFull(buffer)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[count++] = 1;
                lock.notify();
            }
        }
    }

    static class Consumer {
        void consume() {
            synchronized (lock) {
                while (isEmpty(buffer)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[--count] = 0;
                lock.notify();
            }
        }
    }

    static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    static boolean isEmpty(int[] buffer) {
        return count == 0;
    }
}
```

`wait()` and `notify()` are methods on `Object`, not `Thread` — any object can be used as a coordination point, as long as the code accessing it is `synchronized` on that same object. Here, `lock` (a plain `Object`, created just to have something to synchronize on) is that coordination point:

- **`lock.wait()`** — called only while already holding `lock`'s monitor (inside a `synchronized (lock)` block) — releases the lock and pauses the current thread until some other thread calls `notify()`/`notifyAll()` on the same `lock`. This is different from `Thread.sleep()`: `sleep()` holds onto any locks it has while pausing; `wait()` gives the lock up so other threads can actually make progress (like a producer filling the buffer the consumer is waiting on).
- **`lock.notify()`** — wakes up one thread that's currently `wait()`-ing on `lock` (if any). It doesn't release the lock immediately; the woken thread still has to wait its turn to reacquire it once the notifying thread's `synchronized` block ends.
- The `while (isFull(...))` / `while (isEmpty(...))` — **`while`, not `if`** — matters: when a waiting thread wakes up, it re-checks the condition before proceeding, rather than assuming the notify meant "your exact condition is now satisfied." That `while` loop is what keeps a thread from proceeding *incorrectly* if it wakes up and the condition isn't actually true yet.

> **Note:** that `while` loop only protects against proceeding incorrectly (safety) — it doesn't fix a separate problem that shows up once there's more than one producer or more than one consumer sharing this same `lock`. `notify()` wakes *one arbitrary* thread waiting on `lock`, with no way to target "a waiting consumer" specifically rather than "a waiting producer." With multiple producers and consumers all queued on the same lock, a `notify()` can repeatedly wake the wrong kind of thread — one whose own `while` condition is still true, so it just goes straight back to `wait()` — while the thread that was actually waiting on the *right* condition never gets picked, potentially stalling indefinitely (a liveness problem, not just a correctness one). This demo only ever runs one `Producer` and one `Consumer` (see `main` below), so there's no "wrong kind of thread" for `notify()` to pick — every waiter really is the one the notifier means to wake, which is why `notify()` alone is safe here. Generalizing this to multiple producers/consumers would need either `notifyAll()` (wakes everyone to re-check, safe but less targeted), two separate `Condition`s (one for "not full," one for "not empty," so producers and consumers can be woken independently), or simply using a real `java.util.concurrent.BlockingQueue` (see [BlockingQueueDemo](../D4_ThreadProblems/BlockingQueueDemo.md)), which handles all of this internally.

## Running the demo

```java
public static void main(String[] args) throws InterruptedException {
    buffer = new int[10];
    count = 0;

    Producer producer = new Producer();
    Consumer consumer = new Consumer();

    Runnable produceTask = () -> {
        for (int i = 0; i < 50; i++) {
            producer.produce();
        }
        System.out.println("Done producing");
    };

    Runnable consumeTask = () -> {
        for (int i = 0; i < 45; i++) {
            consumer.consume();
        }
        System.out.println("Done consuming");
    };

    Thread producerThread = new Thread(produceTask);
    Thread consumerThread = new Thread(consumeTask);

    producerThread.start();
    consumerThread.start();

    producerThread.join();
    consumerThread.join();

    System.out.println(count + " elements in the buffer.");
}
```

Output:

```
Done producing
Done consuming
5 elements in the buffer.
```

The buffer holds up to 10 items (`buffer.length`). The producer adds 50 items total; the consumer removes 45. Since `50 - 45 = 5`, and the `wait()`/`notify()` coordination guarantees no item is ever added past capacity or removed from empty (each thread blocks and waits its turn instead), the buffer reliably ends up with exactly 5 items left — this result is deterministic every time you run it, unlike [TicketSeller](../D1_Creation/TicketSeller.md)'s interleaving order, because the *final count* here only depends on the fixed totals (50 produced, 45 consumed), not on the order production and consumption happen to interleave in.

`producerThread.join()` and `consumerThread.join()` at the end make `main` wait for both worker threads to fully finish before reading `count` — without them, `main` might print the buffer size while the other threads are still mid-execution, showing an inconsistent, still-changing number.
