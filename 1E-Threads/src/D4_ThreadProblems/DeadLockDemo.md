# Deadlock — Attempted

The file's own header comment describes the intent clearly:

> This is a demonstration of how NOT to write multithreaded programs. It is a program that purposely causes deadlock between two threads that are both trying to acquire locks for the same two resources.

```java
public class DeadLockDemo {
    public static void main(String[] args){
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";

        // Thread 1: locks resource1, then resource2
        Thread t1 = new Thread() {
            public void run() {
                synchronized(resource1){
                    System.out.println("Thread 1: locked resource 1");
                    try{ Thread.sleep(3000); } catch (InterruptedException e) { System.out.println("Interrupted"); }
                    synchronized(resource2){
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };

        // Thread 2: comment says "locks resource 2 right away"...
        Thread t2 = new Thread(){
            public void run(){
                synchronized(resource1){
                    System.out.println("Thread 2: locked resource 1");
                    try{ Thread.sleep(1000); } catch (InterruptedException e){}
                    synchronized(resource2){
                        System.out.println("Thread 2: locked resource 2");
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}
```

> **Note: this file does not actually deadlock**, contrary to its own comments and intent. `t2`'s comment says it "locks resource 2 right away," but the code right below it synchronizes on `resource1` — the *same* resource `t1` locks first, not `resource2`. A real deadlock needs the two threads to acquire two shared resources in **opposite orders** (t1: 1 then 2; t2: 2 then 1) — that circular dependency is what makes each thread end up waiting on the other. With both threads locking `resource1` first, there's no circular wait, just ordinary contention over one lock — the program runs to completion in a few seconds instead of hanging.

Output, confirmed by actually running it — it finishes, it doesn't hang:

```
Thread 1: locked resource 1
Thread 1: locked resource 2
Thread 2: locked resource 1
Thread 2: locked resource 2
```

What happens: `t1` locks `resource1` and sleeps for 3 seconds, holding it the whole time. `t2` tries to lock `resource1` too, and blocks (waiting its turn — not deadlocked, just waiting) until `t1` finishes and releases it. Once `t1` finishes (locks and immediately releases `resource2`, then releases `resource1`), `t2` gets its turn: it locks `resource1`, sleeps 1 second, then locks `resource2` (free at that point) and finishes. Total run time is around 4 seconds (3s + 1s), and the program exits normally.

## What the intended version looks like

Changing only `t2` so it locks `resource2` first (matching what the comment describes) reproduces the real deadlock:

```java
Thread t2 = new Thread(){
    public void run(){
        synchronized(resource2){
            System.out.println("Thread 2: locked resource 2");
            try{ Thread.sleep(1000); } catch (InterruptedException e){}
            synchronized(resource1){
                System.out.println("Thread 2: locked resource 1");
            }
        }
    }
};
```

Output, then the program hangs forever (confirmed — it does not recover on its own and must be killed manually):

```
Thread 1: locked resource 1
Thread 2: locked resource 2
```

Now `t1` holds `resource1` and is waiting on `resource2`; `t2` holds `resource2` and is waiting on `resource1`. Neither can ever proceed — each is holding exactly what the other needs. This is the genuine deadlock the file's comments describe, and it's the same shape as the hidden demo in [Counter.java's nested `driver` class](Counter.md#a-second-demo-hidden-in-a-nested-class), which reproduces this same lock-ordering pattern.

The fix for real multi-lock code, as the file's own header comment says, is to make sure every thread always acquires shared locks **in the same order** — if both `t1` and `t2` always lock `resource1` before `resource2` (as the code above happens to do), a deadlock like this cannot occur, because there's no way for a circular wait to form.
