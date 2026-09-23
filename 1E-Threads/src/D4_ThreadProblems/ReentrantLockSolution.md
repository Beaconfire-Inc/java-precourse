# Avoiding the Mutual-`join()` Wait with `tryLock()`

[DeadLockDemo2](DeadLockDemo2.md) resolved its mutual-`join()` problem with a timeout — both threads eventually got unstuck, but only after actually waiting several seconds. This version prevents the mutual wait from ever starting, using a `ReentrantLock` and `tryLock()`.

```java
public class ReentrantLockSolution {
    static Thread threadA;
    static Thread threadB;
    static final ReentrantLock joinLock = new ReentrantLock();

    public static void main(String[] args) {
        threadA = new Thread(() -> {
            System.out.println("Thread A started");
            if (joinLock.tryLock()) {
                try {
                    threadB.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    joinLock.unlock();
                }
            } else {
                System.out.println("Thread A: skip threadB.join() to avoid deadlock");
            }
            System.out.println("Thread A finished");
        });

        threadB = new Thread(() -> {
            System.out.println("Thread B started");
            if (joinLock.tryLock()) {
                try {
                    threadA.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    joinLock.unlock();
                }
            } else {
                System.out.println("Thread B: skip threadA.join() to avoid deadlock");
            }
            System.out.println("Thread B finished");
        });

        threadA.start();
        threadB.start();
    }
}
```

Output (one possible run — see note below):

```
Thread A started
Thread B started
Thread B: skip threadA.join() to avoid deadlock
Thread B finished
Thread A finished
```

`joinLock.tryLock()` attempts to acquire the lock **without blocking** — it returns `true` immediately if it got the lock, or `false` immediately if someone else already holds it, instead of waiting. Both `threadA` and `threadB` race to call `tryLock()` on the *same* shared `joinLock`, and only one of them can be *holding* it at any given instant — that's what `ReentrantLock` guarantees. Whichever thread doesn't get the lock at that moment takes the `else` branch, skips its `join()` call entirely, and finishes right away — so there's never a moment where both threads are simultaneously blocked waiting on each other, which is what actually rules out the deadlock. (In principle, if the winner's `join()` returns quickly and it reaches `joinLock.unlock()` before the other thread even attempts `tryLock()`, that second thread could then also succeed at acquiring the now-free lock — just at a later point in time, never *at the same time* as the first. The code only guarantees mutual exclusion at any single instant, not that only one of them will ever succeed across the whole run. In practice, given how the demo is structured, the winner typically stays blocked inside `join()` — waiting on the very thread that's meanwhile failing to get the lock and finishing quickly — for long enough that the loser's single `tryLock()` attempt fails, which is why the output usually shows exactly one "skip" line.) The winner proceeds to actually `join()` the other thread, but by then there's no mutual wait to deadlock on, since the loser isn't waiting on anything.

> **Which thread wins is not deterministic** — it depends on which one happens to call `tryLock()` first, which can vary between runs. Either `"Thread A: skip..."` or `"Thread B: skip..."` is equally valid output; the guarantee this design provides isn't about *which* thread waits, it's that the program reliably finishes quickly (well under a second) instead of stalling the way [DeadLockDemo2](DeadLockDemo2.md) did.

This is a meaningfully different fix from a timeout: [DeadLockDemo2](DeadLockDemo2.md)'s approach still pays the cost of waiting (up to the shorter timeout) before recovering, while this approach avoids the mutual wait altogether by having the two threads coordinate about which one is even allowed to attempt the wait.
