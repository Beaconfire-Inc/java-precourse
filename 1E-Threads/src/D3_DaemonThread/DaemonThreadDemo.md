# Daemon Threads

A **daemon** thread is a background thread that doesn't keep the JVM alive — once every non-daemon thread finishes, the JVM exits immediately, even if daemon threads are still running (mid-task or not). Regular threads default to non-daemon, so the JVM normally waits for all of them.

```java
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after sleeping");
        });

        daemonThread.setDaemon(true); // Is false by default
        daemonThread.start();
        System.out.println("From main");
    }
}
```

Output:

```
From main
```

That's the whole output — `"after sleeping"` never prints. `daemonThread.setDaemon(true)` is called *before* `start()` (required — it can't be changed after the thread has started), marking it as a background thread. `main` has nothing left to do after printing `"From main"`, so it returns almost immediately. Since `daemonThread` is the *only* other thread, and it's a daemon, the JVM doesn't wait around for its `Thread.sleep(1000)` to finish — the whole process exits right after `main` returns, cutting the daemon thread off mid-sleep, before it ever reaches the `println` after it.

This is exactly the behavior daemon threads are for: background work (cache cleanup, periodic polling, garbage collection itself runs on daemon threads) that generally shouldn't be the reason the program keeps running. If `setDaemon(true)` were removed (or set to `false`, the default), the JVM would wait for `daemonThread` to finish its full second of sleep, and `"after sleeping"` would print before the program exits.
