# `BlockingQueue`: Producer-Consumer Without Manual `wait()`/`notify()`

[ProducerConsumer](../D2_LifeCycle/ProducerConsumer.md) built a bounded buffer by hand with `wait()`/`notify()`. `BlockingQueue` is a built-in collection that does the same coordination internally, so producer/consumer code can be written without touching locks at all.

```java
class Producer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int value = 0;
            while (true) {
                System.out.println("Producer produced: " + value);
                System.out.println("Queue size: " + queue.size());
                queue.put(value);  // blocks if queue is full
                value++;
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = queue.take(); // blocks if queue is empty
                System.out.println("Consumer consumed: " + value);
                System.out.println("Queue size: " + queue.size());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5); // capacity = 5

        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));

        producerThread.start();
        consumerThread.start();
    }
}
```

`queue.put(value)` and `queue.take()` are the whole trick: `put` automatically blocks the producer if the queue is already at capacity (5 here), and `take` automatically blocks the consumer if the queue is empty — the exact same guarantees [ProducerConsumer](../D2_LifeCycle/ProducerConsumer.md) built manually with `wait()`/`isFull()`/`notify()`, but handled internally by `ArrayBlockingQueue` so neither `Producer` nor `Consumer` needs a `synchronized` block or a shared lock object at all.

> **This program never stops on its own.** Both `run()` methods loop `while (true)` with no exit condition — unlike every other demo in this repo, running `BlockingQueueDemo` directly means it keeps going until you manually kill it (Ctrl+C in a terminal). This is intentional: it models a long-running service (think a message queue consumer) rather than a one-shot demo with a clear "done" state.

Sample output (captured a couple of seconds in, then stopped — yours will differ in the exact numbers, though the pattern will match):

```
Producer produced: 0
Queue size: 0
Consumer consumed: 0
Queue size: 0
Producer produced: 1
Queue size: 0
Producer produced: 2
Queue size: 1
Producer produced: 3
Queue size: 2
Producer produced: 4
Queue size: 3
Producer produced: 5
Queue size: 4
Producer produced: 6
Queue size: 5
Consumer consumed: 1
Queue size: 4
Producer produced: 7
Queue size: 5
```

The producer sleeps only 10ms between items, while the consumer sleeps a full 1000ms — so the producer runs about 100x faster. You can see the effect immediately: the queue fills up to its capacity of 5 within the first handful of iterations and stays there, with the producer blocking on `put()` (waiting for the slow consumer to free up a slot) far more than the reverse. If you let it run, the queue will spend almost all its time sitting at size 5, freeing exactly one slot every second when the consumer finally calls `take()`.
