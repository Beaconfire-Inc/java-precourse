# Creating a Thread: `Runnable`

There are two classic ways to give a thread work to do: implement `Runnable`, or extend `Thread` directly (covered next, in [MyThread](MyThread.md)). This file shows the `Runnable` approach — generally the preferred one, since a class can implement an interface while still extending something else, whereas extending `Thread` uses up a class's one allowed superclass.

```java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Current thread name: " + Thread.currentThread().getName());
        System.out.println("run() from implementing Thread class");
    }
}
```

`Runnable` is a functional interface with one method, `run()` — it describes *what* work should happen, without saying anything about threads itself. `Thread.currentThread().getName()` reports whatever thread actually executes `run()`, which is what makes the two approaches below interesting to compare.

> **Note on file layout:** the code that actually runs (`main`) doesn't live in the public `MyRunnable` class — it's in a second, package-private class called `Demo`, defined further down in the same file. Java allows multiple top-level classes in one file as long as at most one is `public` (and it must match the filename) — the rest just aren't accessible from outside the package.

## The traditional way: a `Runnable` object handed to a `Thread`

```java
MyRunnable myRunnable = new MyRunnable(); // task
Thread thread = new Thread(myRunnable);   // pass the task to a thread
thread.start();
```

This is the explicit version: create the task (`MyRunnable`, an object implementing `Runnable`), wrap it in a `Thread`, then `start()` it. `start()` is what actually spawns a new OS-level thread and, on that new thread, calls `run()`.

> **Note on the source file:** this three-line block is commented out in `MyRunnable.java` — only the lambda version below actually runs.

## The shorter way: a lambda

```java
Runnable lambdaRunnable = () -> {
    System.out.println("Current thread name: " + Thread.currentThread().getName());
    System.out.println("Runnable with Lambda Expression");
};
new Thread(lambdaRunnable).start();
```

Output:

```
Current thread name: Thread-0
Runnable with Lambda Expression
```

Since `Runnable` is a functional interface (one abstract method), a lambda can stand in for a whole implementing class — no separate `MyRunnable` class needed at all. `Thread-0` is the JVM's default auto-generated name for the first unnamed thread created in this program; it's not `main`, confirming the lambda's body really did execute on a separate thread from the one that created it.
