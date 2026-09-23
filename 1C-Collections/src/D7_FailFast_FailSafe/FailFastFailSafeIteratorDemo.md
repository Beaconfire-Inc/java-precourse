# Fail-Fast vs. Fail-Safe Iterators

What happens if you modify a collection *while* iterating over it depends entirely on which collection you're using. This file contrasts two different answers to that question.

## Fail-fast: `ArrayList`

```java
List<Integer> list = new ArrayList<>();
list.add(1);
list.add(2);
list.add(3);

for (Integer i : list) {
    if (i == 2) {
        list.add(4);   // Structural modification
    }
    System.out.println(i);
}
```

Output:

```
1
2
Exception in thread "main" java.util.ConcurrentModificationException
	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
	at D7_FailFast_FailSafe.FailFastFailSafeIteratorDemo.main(FailFastFailSafeIteratorDemo.java:16)
```

This crash is **the whole point of the demo**, not a bug — `ArrayList`'s iterator is *fail-fast*. Internally, the list tracks a `modCount` (how many times it's been structurally modified), and the iterator records what that count was when it was created. Every call to `next()` checks whether `modCount` still matches — `list.add(4)` inside the loop bumps it, so the very next `next()` call (trying to move on to `3`) notices the mismatch and throws `ConcurrentModificationException` immediately, rather than silently producing wrong or inconsistent results. The exception fires on the iteration *after* the modification, which is why `1` and `2` still print before the crash — it isn't caught until the loop tries to advance past the point where the list changed.

Because the exception stops the program, **the second part of this file never runs** when you execute `FailFastFailSafeIteratorDemo` directly — everything below is real code from the same file, just shown running on its own so you can see it complete.

## Fail-safe: `ConcurrentHashMap`

```java
ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
map.put(1, "A");
map.put(2, "B");
map.put(3, "C");

for (Integer key : map.keySet()) {
    if (key == 2) {
        map.put(4, "D");   // Safe modification
    }
    System.out.println(key);
}

System.out.println(map);
```

Output:

```
1
2
3
4
{1=A, 2=B, 3=C, 4=D}
```

No exception this time — `ConcurrentHashMap`'s iterator is *fail-safe* (more precisely, "weakly consistent"). Rather than checking a modification counter and throwing, it's designed to tolerate concurrent structural changes without crashing, which is essential for a map meant to be shared across threads.

One subtlety worth being honest about: this run happens to print `4` too, meaning the iterator ended up seeing the entry added mid-loop. That's **not a guarantee** — `ConcurrentHashMap`'s JavaDoc only promises the iterator *won't throw* and reflects the state of the map "as of some point at or since the creation of the iterator." Whether a concurrent addition shows up in a given pass isn't something your code should depend on either way. The one thing you can rely on is that it will not throw `ConcurrentModificationException`, no matter what else is happening to the map during iteration.

## Why this matters

`ArrayList` (and most of the non-concurrent collections — `HashMap`, `HashSet`, etc.) trade safety for speed: fail-fast is cheap to implement and catches bugs early by crashing loudly the moment you modify a collection mid-iteration. `ConcurrentHashMap` (and the rest of `java.util.concurrent`) trade a bit of that strictness for the ability to be safely read and written from multiple threads at once without external locking. Picking the right collection depends on whether you need that concurrency guarantee at all — reaching for `ConcurrentHashMap` "just in case" when you're not actually sharing the map across threads adds overhead for no benefit.
