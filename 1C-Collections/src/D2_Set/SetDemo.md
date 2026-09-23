# Set

`Set` is a `Collection` with one defining rule: **no duplicate elements**. Unlike `List`, it has no index-based access — you can't ask a `Set` for "the element at position 1," because a set doesn't promise to keep elements in any particular order (that depends on which `Set` implementation you use).

```java
Set<String> set = new HashSet<>();
set.add("New York");
set.add("New Jersey");
set.add("Washington DC");
System.out.println(set);
```

Output:

```
[New York, Washington DC, New Jersey]
```

Notice the printed order (`New York, Washington DC, New Jersey`) doesn't match the order the elements were added in (`New York, New Jersey, Washington DC`). `HashSet` makes **no guarantee** about iteration order — internally it organizes elements by hash code, not insertion order, so what you see printed can vary and shouldn't be relied on.

```java
Set<String> linkedSet = new LinkedHashSet<>();
linkedSet.add("New York");
linkedSet.add("New Jersey");
linkedSet.add("Washington DC");
System.out.println(linkedSet);
```

Output:

```
[New York, New Jersey, Washington DC]
```

`LinkedHashSet` is a `HashSet` that additionally maintains a linked list running through its entries, tracking insertion order — so iterating over it (including printing it) always reflects the order elements were added in. It costs a little more memory than a plain `HashSet` for that guarantee.

If you try adding a duplicate to either set (e.g. `set.add("New York")` again), nothing changes — the size stays the same and the call simply has no effect, since `Set` silently ignores duplicates rather than throwing an error.
