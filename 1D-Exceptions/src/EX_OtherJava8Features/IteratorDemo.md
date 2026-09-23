# Iterator

`Iterator` is what actually powers the enhanced `for` loop (`for (x : collection)`) under the hood — this file uses it directly, spelling out the mechanism that syntax normally hides.

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
Iterator<Integer> i = list.iterator();

while (i.hasNext()) {
    Integer num = i.next();
    System.out.println(num);
}
```

Output:

```
1
2
3
4
5
```

`list.iterator()` returns an `Iterator` positioned just before the first element. `hasNext()` checks whether there's another element left to visit, and `next()` both advances the iterator *and* returns the element it just moved past. The `while (i.hasNext())` loop is exactly what `for (Integer num : list)` compiles down to — this file just writes out that translation explicitly.

`Iterator` also has a `remove()` method (not used here) that, for most mutable collections (a plain `ArrayList`, `HashSet`, etc.), safely deletes the current element from the underlying collection *during* iteration — the standard safe way to modify one of those while iterating over it. Removing through the collection itself instead (e.g. `list.remove(i)` inside the loop) is exactly the kind of structural modification that trips the fail-fast behavior covered in [1C's Fail-Fast vs. Fail-Safe topic](../../../1C-Collections/src/D7_FailFast_FailSafe/FailFastFailSafeIteratorDemo.md).

> **Note:** `Iterator.remove()` is documented as an *optional operation* — not every iterator implements it. The `list` used in this file specifically comes from `Arrays.asList(...)`, which returns a fixed-size list backed by the original array; its iterator's `remove()` throws `UnsupportedOperationException` rather than actually removing anything. So while the pattern described above is the right one for an ordinary `ArrayList`/`LinkedList`/`HashSet`, it wouldn't work if you tried it on `list` as constructed here.
