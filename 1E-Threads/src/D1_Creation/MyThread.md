# Creating a Thread: Extending `Thread`

The second way to give a thread work: extend `Thread` itself and override `run()`.

```java
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Current thread is " + Thread.currentThread().getName());
        System.out.println("run() from extending Thread class");
    }
}
```

## `start()` vs. calling `run()` directly

```java
public static void main(String[] args) throws Exception {
    System.out.println(Thread.currentThread().getName());
    Thread thread = new MyThread();
    thread.start();
    Thread.sleep(1000);
    thread.run();
    thread.run();
    (new MyThread()).start();
}
```

Output:

```
main
Current thread is Thread-0
run() from extending Thread class
Current thread is main
run() from extending Thread class
Current thread is main
run() from extending Thread class
Current thread is Thread-1
run() from extending Thread class
```

Walking through it:

1. `Thread.currentThread().getName()` at the very top of `main` prints `main` — confirming the program itself starts on a thread literally named `"main"`.
2. `thread.start()` spawns a real new thread (`Thread-0`) and calls `run()` on it — that's why the next two lines report `Thread-0`, not `main`.
3. `Thread.sleep(1000)` pauses the **main** thread for a second — just long enough to be confident `Thread-0` has already finished printing before moving on, so the output above appears in a predictable order.
4. `thread.run()` — called twice — does **not** start a new thread. Called this way, `run()` is just an ordinary method call, executed synchronously on whichever thread calls it (`main`, here). That's why both of these calls report `Current thread is main`, not `Thread-0`. This is the core distinction the file is demonstrating: `start()` schedules `run()` to execute on a new thread; calling `run()` directly just runs that code on the *current* thread, like calling any other method.
5. `(new MyThread()).start()` creates a **new** `MyThread` object and starts it, producing `Thread-1`. This is necessary — see the note below.

> **Why a new object, not reusing `thread`:** a `Thread` can only be `start()`-ed once. Calling `start()` a second time on the *same* `Thread` object throws `IllegalThreadStateException` — that's exactly what [StartVSRunDemo](../D2_LifeCycle/StartVSRunDemo.md) demonstrates. Since this file wants to show a second thread actually starting (not crashing), it constructs a fresh `MyThread` instance for that last line instead of calling `thread.start()` again.
