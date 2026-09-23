# Counter — Synchronization, and a Second Demo Hidden Inside

This file's class-level comment calls it "A demo of race condition." As actually written, though, it doesn't produce one — worth working through carefully, since understanding *why* it doesn't race is as useful as seeing a race would have been.

```java
public class Counter {
    private volatile int c = 0;
    private final Random random = new Random();

    public void increment() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 10000; i++) {
                c++;
            }
        }
    }

    public void decrement() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 10000; i++) {
                c--;
            }
        }
    }
}
```

```java
public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();
    Thread t1 = new Thread(() -> {
        try { counter.increment(); } catch (InterruptedException e) { throw new RuntimeException(e); }
    });
    Thread t2 = new Thread(() -> {
        try { counter.decrement(); } catch (InterruptedException e) { throw new RuntimeException(e); }
    });
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(counter.c);
}
```

Output (every time you run it):

```
0
```

Here's why there's no race: `increment()` wraps its **entire** 10,000-iteration loop in one `synchronized (this)` block, and so does `decrement()`. Since both synchronize on the same object (`this`, the shared `counter`), only one of `t1`/`t2` can be inside its method *at all* at any given moment — whichever thread gets there first runs its full 10,000 iterations to completion before the other one is even allowed to start. There's no interleaving between the two loops, so there's nothing for a race to corrupt: the result is always exactly `10000 - 10000 = 0`.

A genuine race-condition version of this file would need to remove synchronization from around the `c++`/`c--` operations themselves, so a thread could read `c`, get interrupted mid-operation, and have the other thread modify `c` before the first thread writes its result back. Narrowing the `synchronized` block to wrap just each individual `c++`/`c--` (instead of the whole loop) would **not** be enough to reintroduce a race — each increment/decrement would still happen atomically under the lock, so no interleaving *within* a single `c++`/`c--` could occur, and the final result would still deterministically be `0`; it would just let the two loops interleave operation-by-operation instead of running one after the other. Removing synchronization entirely around `c++`/`c--` is what actually opens the window for lost updates. The commented-out `Thread.sleep(random.nextInt(5))` inside each loop hints at what this file may have originally been building toward (widening that window) before the `synchronized` blocks were added around the whole loop — but as it stands now, the synchronization is broad enough to eliminate the race entirely, at the cost of the two threads never actually running concurrently in any meaningful sense.

`t1.join()`/`t2.join()` before printing `counter.c` matter for a different reason than the synchronization: without them, `main` could print `c` before either thread finishes, showing a value mid-computation rather than the final `0`.

## A second demo, hidden in a nested class

```java
public static class driver {
    public static void main(String[] args) {
        Object key1 = new Object();
        Object key2 = new Object();
        Thread t8 = new Thread(() -> {
            synchronized (key1) {
                System.out.println("t8 has key 1.");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (key2) {
                    System.out.println("t8 has key 2");
                }
            }
        });
        Thread t9 = new Thread(() -> {
            synchronized (key2) {
                System.out.println("t9 has key 2.");
                synchronized (key1) {
                    System.out.println("t9 has key 1");
                }
            }
        });
        t8.start();
        t9.start();
    }
}
```

> **Note:** `Counter.java` contains a *second*, unrelated `main()` method, tucked inside a nested static class named `driver` (lowercase — an unconventional name for a class, normally `Driver`). Since it's a separate class with its own `main`, it has to be run with its own fully-qualified name, including the `$` that Java uses for nested classes:
> ```bash
> java D4_ThreadProblems.Counter\$driver
> ```
> (The backslash escapes `$` from the shell, which would otherwise try to treat it as a variable reference.)

This nested demo genuinely deadlocks, matching what its code sets out to do — unlike [DeadLockDemo](DeadLockDemo.md) next door, where the code doesn't end up matching its own comments:

```
t8 has key 1.
t9 has key 2.
```

And then the program hangs forever (confirmed by running it and waiting — it does not recover, and must be killed manually). Tracing through why: `t8` locks `key1` and sleeps for 5 seconds while still holding it. Meanwhile `t9` locks `key2` immediately, then tries to also lock `key1` — but `t8` is holding it, so `t9` blocks, *while still holding `key2`*. When `t8` wakes up after 5 seconds and tries to lock `key2`, `t9` is holding that one — so `t8` blocks too. Now both threads are stuck forever, each holding the lock the other one needs: a genuine circular-wait deadlock. This is the textbook shape [DeadLockDemo](DeadLockDemo.md) is trying (and, per its own note, failing) to demonstrate with two named resources instead of `key1`/`key2`.
