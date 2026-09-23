# Thread-Safe Lazy Singleton

A singleton guarantees at most one instance of a class ever exists. That's easy when the instance is created eagerly (up front, before any thread could possibly ask for it) — the hard part, and the point of this file, is doing it **lazily** (only creating the instance the first time it's actually requested) while still being safe when multiple threads ask for it at the same time.

```java
public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {
        System.out.println("Constructor called by " + Thread.currentThread().getName());
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) { // double check
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

The constructor is `private` (so nobody can bypass `getInstance()` with `new Singleton()` directly) and deliberately slow — `Thread.sleep(1000)` simulates expensive setup work, making it far more likely that multiple threads will call `getInstance()` while the instance is still being created, which is exactly the scenario worth getting right.

## Running the demo

```java
public static void main(String[] args) {
    Thread t1 = new Thread(() -> System.out.println(Singleton.getInstance()));
    Thread t2 = new Thread(() -> System.out.println(Singleton.getInstance()));
    Thread t3 = new Thread(() -> System.out.println(Singleton.getInstance()));
    t1.start();
    t2.start();
    t3.start();
}
```

Output:

```
Constructor called by Thread-0
D5_Singleton.Singleton@6d09e1f2
D5_Singleton.Singleton@6d09e1f2
D5_Singleton.Singleton@6d09e1f2
```

(The exact thread name and hash code will vary between runs — what matters is the shape: exactly one `"Constructor called by..."` line, and all three printed objects showing the *same* hash code. That's a strong signal all three threads got the same instance, but it isn't rigorous proof by itself — two distinct objects are legally allowed to share a hash code (a collision). The airtight way to check is reference equality, e.g. `getInstance() == getInstance()`, which is `true` here precisely because it really is the same object each time.)

## Why the double check, and why `volatile`

`getInstance()` checks `instance == null` **twice** — once before entering the `synchronized` block, and again inside it. Both checks are necessary for different reasons:

- The **outer** check (before synchronizing) is a performance optimization: once `instance` has been created, every future call skips the `synchronized` block entirely, since acquiring a lock is unnecessary once there's nothing left to create. Without it, *every* call to `getInstance()` — forever — would have to acquire the lock, even long after the singleton is fully initialized.
- The **inner** check (after synchronizing) is what actually prevents a duplicate. Imagine `t1` and `t2` both see `instance == null` at the outer check and both proceed toward the `synchronized` block — only one of them (say `t1`) can enter it at a time. `t1` creates the instance, then exits the block. Now `t2` enters the block — if it didn't check `instance == null` *again* here, it would blindly create a **second** `Singleton`, defeating the whole point. The inner check catches this: `t2` sees `instance` is no longer `null` and skips construction.

`volatile` on the `instance` field matters for a subtler reason: `new Singleton()` isn't truly one atomic step — the JVM can, in principle, make the reference visible to other threads *before* the constructor has fully finished initializing the object (an effect of instruction reordering, allowed by the Java Memory Model in the absence of other synchronization). Without `volatile`, a second thread's outer `instance == null` check could theoretically see a non-null but only *partially constructed* object. `volatile` prevents that reordering, ensuring any thread that sees a non-null `instance` sees a fully-initialized one.

## Simpler alternatives, and why they're commented out

```java
// private volatile static Singleton instance = new Singleton(); // eager-initialization
```

**Eager initialization** — creating the instance immediately when the class is loaded, rather than waiting for the first `getInstance()` call — sidesteps the whole thread-safety problem entirely (there's no "first call" race to worry about, since the JVM guarantees class initialization itself happens safely, exactly once). The tradeoff is losing laziness: the expensive constructor runs at class-load time whether or not the singleton ever ends up being used.

```java
// not thread-safe
// public static Singleton getInstance() {
//     if (instance == null) {
//         instance = new Singleton();
//     }
//     return instance;
// }
```

The most naive lazy version — no synchronization at all. With this version, two threads can both pass the `instance == null` check before either has finished constructing the object, and both proceed to create their own `Singleton`, producing two different instances (defeating the "single" in singleton). This is the specific bug the rest of the file exists to fix.

```java
// thread-safe reached by lazy initialization (Solution 1)
// public synchronized static Singleton getInstance() {
//     if (instance == null) {
//         instance = new Singleton();
//     }
//     return instance;
// }
```

Making the **entire method** `synchronized` fixes the thread-safety bug correctly — only one thread can be inside `getInstance()` at a time, so there's no way for two threads to both see `instance == null` and both construct an object. The downside is performance: *every single call to `getInstance()`, forever*, has to acquire the lock, even decades after the instance was created and there's no more risk to protect against. The double-checked locking version in the active code exists specifically to avoid paying that cost on every call, while still being just as safe.
