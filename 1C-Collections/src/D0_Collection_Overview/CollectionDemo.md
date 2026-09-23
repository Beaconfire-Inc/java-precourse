# Collection vs. Collections

Two very similarly-named things kick off this topic, and mixing them up is an easy mistake to make:

- **`Collection`** (singular) is an **interface** — the root of the collections hierarchy (`List`, `Set`, and `Queue` all extend it).
- **`Collections`** (plural) is a **final utility class** full of `static` helper methods (`sort`, `reverse`, `min`, `max`, `binarySearch`, ...) that operate *on* collections.

## `Collection<Object>` — and why you shouldn't do this

```java
Collection<Object> collection = new ArrayList<>();
collection.add(1);
collection.add("new");
collection.add(22.2);
collection.add(false);
System.out.println(collection);
```

Output:

```
[1, new, 22.2, false]
```

This compiles and runs, because `Object` is the superclass of everything — an `Integer`, a `String`, a `Double`, and a `Boolean` can all be boxed and stored in it. But that's exactly the problem: `Collection<Object>` gives up type safety entirely. Nothing stops you from mixing unrelated types in the same collection, and reading an element back out gives you an `Object` that you'd have to cast (and guess the right type for) before doing anything useful with it. In real code, you'd use a specific type parameter (`Collection<Integer>`, `Collection<String>`, etc.) so the compiler can catch type mistakes for you.

A few more `Collection` methods worth knowing, beyond `add`:

```java
System.out.println("Collection contains 1: " + collection.contains(1));
System.out.println("Collection is empty: " + collection.isEmpty());
collection.remove(22.2);
System.out.println("The size of the collection is: " + collection.size());
System.out.println(collection);
```

`contains`, `isEmpty`, `remove`, and `size` are all declared on the `Collection` interface itself, so every collection type (`List`, `Set`, `Queue`, ...) gets them for free.

## `Collections` — the utility class

```java
Collections.sort(Arrays.asList(3,2,1));
Collections.reverse(Arrays.asList(1,2,3));
```

> **Note:** as written, these two lines don't actually show you anything — `Arrays.asList(3,2,1)` creates a throwaway list that's sorted (or reversed) in place, but since it's never stored in a variable or printed, the result is invisible. To actually see `Collections.sort`/`reverse` at work, capture the list first:

```java
List<Integer> nums = new ArrayList<>(Arrays.asList(3, 2, 1));
Collections.sort(nums);
System.out.println("sorted: " + nums);
Collections.reverse(nums);
System.out.println("reversed: " + nums);
```

Output:

```
sorted: [1, 2, 3]
reversed: [3, 2, 1]
```

## Searching a sorted list

```java
List<Integer> serachList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
System.out.println("The min value is: " + Collections.min(serachList));
System.out.println("The max value is: " + Collections.max(serachList));
System.out.println("The position of the element 3 is at: " + Collections.binarySearch(serachList,3));
Collections.reverse(serachList);
System.out.println("The position of the element 3 after reverse is at: " + Collections.binarySearch(serachList,3));
```

Output:

```
The min value is: 1
The max value is: 9
The position of the element 3 is at: 2
The position of the element 3 after reverse is at: -1
```

`Collections.min`/`max` scan the whole list and work regardless of order. `Collections.binarySearch`, on the other hand, **requires the list to already be sorted in ascending order** — that's the whole reason binary search is fast. Once `serachList` is reversed into descending order, that precondition is broken, and `binarySearch` no longer behaves reliably. The `-1` printed above is **not** a trustworthy "not found" answer — it's the undefined result of running binary search on data it was never meant to handle. Always sort a list ascending before calling `Collections.binarySearch` on it.
