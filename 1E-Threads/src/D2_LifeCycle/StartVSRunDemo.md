# A Thread Can Only Be Started Once

This file exists to demonstrate a specific rule: calling `start()` more than once on the *same* `Thread` object is illegal — a `Thread` can only transition out of its "new" state one time.

```java
class MyThread_BA extends Thread {
    public void run() {
        System.out.println("Current running in thread: " + Thread.currentThread().getName());
        System.out.println("run() method called");
    }
}
public class StartVSRunDemo {
    public static void main(String[] args) {
        MyThread_BA t = new MyThread_BA();
        t.start();
        t.start();
    }
}
```

Output:

```
Exception in thread "main" java.lang.IllegalThreadStateException
	at java.base/java.lang.Thread.start(Thread.java:1525)
	at D2_LifeCycle.StartVSRunDemo.main(StartVSRunDemo.java:17)
Current running in thread: Thread-0
run() method called
```

This crash is **the point of the demo**, not a bug. The first `t.start()` succeeds and schedules `Thread-0` to run `run()`. The second `t.start()`, on the exact same `Thread` object, throws `IllegalThreadStateException` immediately — a `Thread`'s internal state changes the moment `start()` begins, so trying to start it again (whether it's still running, or has already finished) is always rejected. Since `Thread.start()` throws synchronously on the calling thread (`main`), the exception and stack trace print right away, while `Thread-0`'s two lines appear once the OS actually gets around to scheduling it — which is why the exception can appear to print "before" `Thread-0`'s own output in the combined log, even though both are really happening within a few milliseconds of each other.

This is exactly why [MyThread](../D1_Creation/MyThread.md) constructs a **new** `Thread` object for its second `start()` call instead of reusing the first one — reusing it would hit this same exception.

The commented-out `t.run()` line in the source is worth comparing against this: calling `run()` directly (instead of `start()`) is just an ordinary method call, and *can* be called as many times as you like on the same object, precisely because it doesn't touch the `Thread`'s internal lifecycle state at all — it never actually spawns a thread. See [MyThread](../D1_Creation/MyThread.md) for that distinction in action.
