# List

`List` is a `Collection` that adds ordering and index-based access — elements have a position, duplicates are allowed, and you can look things up by index instead of just iterating.

```java
List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));
list.add(1,100);

System.out.println(list);
System.out.println(list.get(1));
list.set(2,1000);
System.out.println(list);
list.remove(1);
list.remove(Integer.valueOf(2));
System.out.println(list);
```

Output:

```
[1, 100, 2, 3]
100
[1, 100, 1000, 3]
[1, 1000, 3]
```

Walking through each call:

- `list.add(1, 100)` inserts `100` **at index 1**, shifting everything after it over by one: `[1, 2, 3]` → `[1, 100, 2, 3]`. (This is `List`'s indexed `add(int index, E element)` — `Collection` alone doesn't have this, since a plain `Collection` has no concept of position.)
- `list.get(1)` reads back the element at index 1, which is `100`.
- `list.set(2, 1000)` **replaces** whatever is at index 2 (which was `2`) with `1000` — `[1, 100, 2, 3]` → `[1, 100, 1000, 3]`. `set` doesn't shift anything; it's a straight replacement.
- `list.remove(1)` removes **by index** — index 1 holds `100`, so that's what gets removed: `[1, 100, 1000, 3]` → `[1, 1000, 3]`.
- `list.remove(Integer.valueOf(2))` removes **by value** instead. This line is a good trap to understand: `list.remove(2)` (with a plain `int`) would call the index-based overload and remove whatever is at index 2. Wrapping `2` in `Integer.valueOf(2)` forces Java to match the `remove(Object)` overload instead, which searches for and removes the *value* `2` — except by this point `2` isn't in the list anymore (it was replaced by `1000` in the `set` step), so this call has no effect. That's why the list is unchanged after this line.

## Swapping the implementation

```java
list = new LinkedList<>();
```

`list` is declared as a `List<Integer>`, so it can be reassigned to point at any class that implements `List` — here, swapping from `ArrayList` (backed by a resizable array, fast random access) to `LinkedList` (a doubly-linked list, fast insertion/removal at the ends). Everything from this point on would still be called through the same `List` methods; the concrete implementation underneath is free to change without touching the rest of the code. That's the point of programming against the `List` interface instead of a specific class.

```java
list.add(10);
list.add(20);
list.add(0, 5);
System.out.println(list);
```

Output:

```
[5, 10, 20]
```

Same `add` methods, same behavior from the caller's point of view — just backed by a linked list instead of an array now.
