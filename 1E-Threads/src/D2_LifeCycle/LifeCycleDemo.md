# Thread Life Cycle: `sleep()`, `interrupt()`, `join()`

A thread moves through several states over its life, defined by the `Thread.State` enum: `NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, and `TERMINATED`. (There's no separate "running" state in that enum — a thread that's actually executing on a CPU right now is still classified as `RUNNABLE`; the JVM doesn't distinguish "ready to run" from "currently running," since that's an OS scheduling detail.) This file touches three of the methods used to move between those states: `sleep()`, `interrupt()`, and `join()` — plus points ahead to `wait()`/`notify()` in [ProducerConsumer](ProducerConsumer.md).

```java
Runnable sleepTask = () -> {
    try {
        System.out.println("Task is running in: " + Thread.currentThread().getName());
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        System.out.println("----Interrupted in: " + Thread.currentThread().getName() + "----");
    }
};
Thread t = new Thread(sleepTask);
```

## `sleep()` — pausing a thread for a fixed time

```java
System.out.println("Main thread sleeping for 2 seconds.");
Thread.sleep(2000);  // Main thread sleeps
System.out.println("Main thread wakes up.");
```

Output:

```
Main thread sleeping for 2 seconds.
Main thread wakes up.
```

This is the only part of the file that actually runs `main()` as-is. `Thread.sleep(2000)` here pauses the **main** thread itself (it's a `static` method, called without any particular `Thread` object — it always affects whoever is calling it) for 2 seconds; the `sleepTask`/`t` defined above are set up but never started in the version that runs by default.

## `interrupt()` and `join()` — waking a sleeping thread, then waiting for it

> **Note on the source file:** this whole section is commented out. `t` is never started or interrupted in the default run — the code below is real and works, just not part of what executes by default.

```java
System.out.println("Check point 1 - current thread is : " + Thread.currentThread().getName());
t.start();
t.interrupt(); // throws InterruptedException
t.join();
System.out.println("Check point 2 - current thread is : " + Thread.currentThread().getName());
```

Output:

```
Check point 1 - current thread is : main
Task is running in: Thread-0
----Interrupted in: Thread-0----
Check point 2 - current thread is : main
```

`t.start()` begins `sleepTask` on a new thread (`Thread-0`), which immediately calls `Thread.sleep(5000)`. `t.interrupt()`, called right after from the main thread, doesn't forcibly stop `Thread-0` — it sets an internal "interrupted" flag, and since `Thread-0` happens to be inside `sleep()` at that moment, the JVM responds by throwing `InterruptedException` *inside* `Thread-0`, right where it's sleeping. That's caught by the `catch` block in `sleepTask`, printing `"----Interrupted in: Thread-0----"` almost immediately — instead of waiting the full 5 seconds. `t.join()` then has the main thread wait for `Thread-0` to actually finish (which, thanks to the interrupt, happens quickly) before printing "Check point 2".

The two commented-out throw statements in `sleepTask`'s `catch` block (`throw new RuntimeException()` vs `throw new Exception()`) make a Java-specific point: a lambda implementing `Runnable` can only throw **unchecked** exceptions, because `Runnable.run()`'s signature doesn't declare any checked exceptions — `RuntimeException` compiles fine here, but a checked `Exception` would not.

## Where `wait()`/`notify()` fit in

The remaining life-cycle methods — `wait()`, `notify()`, `notifyAll()` — aren't demonstrated in this file at all; see [ProducerConsumer](ProducerConsumer.md) for those, working together to coordinate two threads around a shared buffer.
