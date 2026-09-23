# A Mutual-`join()` Deadlock — Rescued by a Timeout

`join()` isn't a lock like `synchronized`, but two threads waiting on *each other* via `join()` creates the same kind of circular dependency as [DeadLockDemo](DeadLockDemo.md)'s intended lock-ordering problem.

```java
public class DeadLockDemo2 {
    static Thread threadA;
    static Thread threadB;

    public static void main(String[] args) throws InterruptedException {
        threadA = new Thread(() -> {
            System.out.println("Thread A started");
            try {
                threadB.join(4000);  // A waits for B to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread A finished");
        });

        threadB = new Thread(() -> {
            System.out.println("Thread B started");
            try {
                threadA.join(10000);  // B waits for A to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread B finished");
        });

        threadA.start();
        threadB.start();
    }
}
```

Output (timed — see below):

```
Thread A started
Thread B started
Thread A finished
Thread B finished
```

Both threads try to wait for each other: `threadA` calls `threadB.join(4000)`, and `threadB` calls `threadA.join(10000)`. Neither thread does anything else that would let it finish on its own — without the timeouts, this is a genuine circular wait, just like a lock-based deadlock. But because **both calls use the timed version of `join`**, neither wait is permanent: `threadA`'s 4-second timeout is shorter, so it elapses first. At that point, `join` simply gives up waiting (no exception — a `join` timeout isn't an error) and `threadA` proceeds to print `"Thread A finished"` and exit. Once `threadA` has actually terminated, `threadB`'s `join(10000)` — which had 10 seconds to work with, well more than the 4 it ended up needing — detects that `threadA` is done and returns immediately, letting `threadB` finish too.

Running this takes about 4 seconds — the length of the *shorter* of the two timeouts, since that's what determines when the deadlock first breaks.

This demonstrates something worth internalizing: a timed wait (`join(millis)`, and other timed blocking calls) turns a potential permanent deadlock into a temporary stall — the program is still stuck for a while and something is still logically wrong (both threads shouldn't need to wait on each other in the first place), but it recovers instead of hanging forever. [ReentrantLockSolution](ReentrantLockSolution.md) shows a cleaner way to prevent the mutual wait from happening at all, rather than just bounding how long it lasts.
