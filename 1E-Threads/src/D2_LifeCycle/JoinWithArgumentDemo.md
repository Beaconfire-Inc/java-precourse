# `join(timeout)`

`Thread.join()` makes the calling thread wait for another thread to finish. This file shows the timed variant, `join(long millis)`, which waits *at most* that long instead of forever.

```java
public class JoinWithArgumentDemo {
    public static void main(String[] args) {
        System.out.println("Main thread starts");

        Thread threadA = new Thread(() -> {
            System.out.println("sleeping for 5 seconds");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleep over");
        });

        threadA.start();
        try {
            threadA.join(4000); // main thread will wait for at most 4 seconds till threadA die.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread ends");
    }
}
```

Output:

```
Main thread starts
sleeping for 5 seconds
Main thread ends
sleep over
```

`threadA` sleeps for 5 seconds before printing `"sleep over"`. The main thread calls `threadA.join(4000)`, which waits for `threadA` to finish — but only for up to 4 seconds. Since `threadA` needs 5 seconds, the 4-second wait times out *before* `threadA` is done, and `join` simply returns (no exception — a timeout on `join` isn't an error, it's expected behavior). That's why `"Main thread ends"` prints at around the 4-second mark, **before** `"sleep over"`, which only shows up about a second later once `threadA` actually finishes.

Notice the program doesn't actually exit the moment `"Main thread ends"` prints — `main` returning doesn't end the JVM process while other **non-daemon** threads (like `threadA`, which was never marked as a daemon) are still running. So the whole program takes about 5 seconds total, even though `main`'s own code finishes after about 4.

Compare this to plain `threadA.join()` with no argument (commented out just above the timed version in the source) — that would wait however long it takes, with no timeout, so `"Main thread ends"` would always print *after* `"sleep over"`, not before.

The commented note about `InterruptedException` in the source is worth a correction, not just a repeat: `join()` throws it on the thread that's *currently blocked inside the `join()` call* — here, that's `main`, waiting on `threadA.join(4000)` — if *that* thread (`main`) gets interrupted, i.e. some other code calls `main`'s own `interrupt()`. Calling `threadA.interrupt()` instead only interrupts `threadA` itself — which, in this file, would show up inside `threadA`'s own `Thread.sleep(5000)`, throwing an `InterruptedException` there (caught by `threadA`'s own `catch` block), not inside `main`'s `join()` call at all. So the source comment's claim is backwards: it's a different mechanism from the timeout shown here, but it isn't triggered by `threadA.interrupt()`.
