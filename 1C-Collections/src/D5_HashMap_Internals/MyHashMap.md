# Building a HashMap From Scratch

`java.util.HashMap` looks like magic from the outside — `put`/`get` in roughly constant time on average — but the idea behind it is simple enough to build yourself. This file implements a basic version using an array of buckets, where each bucket is a linked chain of [MyHashMapNode](MyHashMapNode.md)s. That "average constant time" comes with caveats even for the real `java.util.HashMap`, and more so here: lookup/insert cost is proportional to how long the chain in the relevant bucket is, so it's *average*-case near `O(1)` only when keys are spread evenly across buckets and chains stay short — worst case (many keys colliding into one bucket) degrades to `O(n)`, scanning the whole chain. Unlike `java.util.HashMap`, this implementation also never resizes `bucket` as more entries are added, so chains only ever grow, never get redistributed across more buckets.

```java
public class MyHashMap<K, V> {
    private int capacity;
    private MyHashMapNode<K, V>[] bucket;

    public MyHashMap() {
    }

    public MyHashMap(int capacity) {
        this.capacity = capacity;
        this.bucket = new MyHashMapNode[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    ...
}
```

`bucket` is a fixed-size array — `capacity` slots, decided up front in the constructor. `hash(key)` turns any key into an index into that array: take the key's `hashCode()`, use `Math.abs(...)` to make it non-negative, and reduce it into range with `% capacity`. (`new MyHashMapNode[capacity]` — without a type parameter — is a generic array creation workaround; Java doesn't allow `new MyHashMapNode<K, V>[capacity]` directly, so this array is created as a raw type and relies on an unchecked cast, which is why compiling this file prints an "unchecked operations" warning.)

> **Note:** `hash(key)` has a few edge cases the demo below never exercises:
> - `Math.abs(...)` doesn't *always* return a non-negative number — `Math.abs(Integer.MIN_VALUE)` is still `Integer.MIN_VALUE` (still negative), because two's-complement `int` has one more negative value than positive one, so negating the most negative `int` overflows back to itself. A key whose `hashCode()` happens to be `Integer.MIN_VALUE` would make `hash(key)` return a negative index, and indexing `bucket[]` with it would throw `ArrayIndexOutOfBoundsException`.
> - `hash(key)` calls `key.hashCode()` directly with no null check, so a `null` key throws `NullPointerException` — unlike `java.util.HashMap`, which has special-case handling for a `null` key.
> - `capacity` is only ever set by the one-argument constructor; the no-arg `MyHashMap()` leaves `capacity` at its default `0` and never initializes `bucket` at all. Calling `hash(...)` on such an instance divides by zero (`% 0`), throwing `ArithmeticException`, and `put`/`get` would separately hit a `NullPointerException` on the uninitialized `bucket` array.
>
> None of these come up in the demo below, since it always constructs `MyHashMap` with a fixed positive capacity and only uses ordinary string keys.

## Inserting — `put`

```java
public void put(K key, V value) {
    int bucketPos = hash(key);

    if (bucket[bucketPos] == null) {
        bucket[bucketPos] = new MyHashMapNode<K, V>(key, value);
    } else {
        MyHashMapNode<K, V> prevNode = null;
        MyHashMapNode<K, V> currentNode = bucket[bucketPos];

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                currentNode.setValue(value);
                break;
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (currentNode == null) prevNode.setNext(new MyHashMapNode<K, V>(key, value));
    }
}
```

Two cases:

1. **The bucket is empty** — just drop a new node straight in.
2. **The bucket already has a chain** — walk the chain looking for a node whose key already matches (`equals`). If found, update its value in place (this is how `put`-ing the same key twice *overwrites* instead of duplicating). If the loop runs off the end without finding a match, `currentNode` ends up `null` and `prevNode` is the last real node in the chain — so a new node gets appended after it. This chaining is exactly what [MyHashMapNode](MyHashMapNode.md)'s `next` field is for: it's how two different keys that hash to the *same* bucket ("a collision") both get to coexist.

## Reading — `get`

```java
public V get(K key) {
    int bucketPos = hash(key);
    MyHashMapNode<K, V> currentNode = bucket[bucketPos];
    while (currentNode != null) {
        if (currentNode.getKey().equals(key)) {
            break;
        }
        currentNode = currentNode.getNext();
    }
    return currentNode.getValue();
}
```

Same idea: hash to a bucket, then walk the chain looking for a matching key.

> **Note:** there's a real bug here. If the key isn't found, the `while` loop exits with `currentNode` equal to `null` (either the bucket was empty, or the chain was walked all the way through without a match) — and the very next line calls `currentNode.getValue()` on that `null` reference, throwing a `NullPointerException`. Compare this to `java.util.HashMap.get(...)`, which returns `null` for a missing key instead of throwing. Try it yourself:
> ```java
> map.get("NotThere"); // throws NullPointerException
> ```
> A correct fix would return `null` when `currentNode` is `null`, e.g. `return currentNode == null ? null : currentNode.getValue();` — but that's left as-is here since it isn't exercised by the demo below.

## Running the demo

```java
public static void main(String args[]) {
    MyHashMap<String, Integer> map = new MyHashMap<>(10);

    map.put("Apple", 20);
    System.out.println(map);
    System.out.println(map.get("Apple"));

    map.put("Apple", -10);
    System.out.println(map);

    map.put("Banana", 30);
    System.out.println(map);

    map.put("Mango", 100);
    System.out.println(map);
}
```

Output:

```
[0]: 
[1]: 
[2]: 
[3]: 
[4]: 
[5]: 
[6]: 
[7]: 
[8]: {Apple:20} -> 
[9]: 

20
[0]: 
[1]: 
[2]: 
[3]: 
[4]: 
[5]: 
[6]: 
[7]: 
[8]: {Apple:-10} -> 
[9]: 

[0]: 
[1]: 
[2]: 
[3]: 
[4]: 
[5]: 
[6]: 
[7]: {Banana:30} -> 
[8]: {Apple:-10} -> 
[9]: 

[0]: 
[1]: 
[2]: 
[3]: 
[4]: 
[5]: 
[6]: 
[7]: {Banana:30} -> 
[8]: {Apple:-10} -> {Mango:100} -> 
[9]: 

```

`toString()` prints all 10 buckets, empty or not, which makes the internal layout visible: `"Apple"` and `"Mango"` both hash to bucket 8 — a collision — so `"Mango"` gets chained after `"Apple"` rather than overwriting it. `put("Apple", -10)` after `put("Apple", 20)`, on the other hand, hits the "key already exists" branch in `put`, so it *updates* the existing node's value (`20` → `-10`) instead of adding a second entry — bucket 8 still shows only one `Apple` node before `Mango` is added.

> **Note on the source file:** the middle three `System.out.println(...)` calls above are commented out in `MyHashMap.java`'s actual `main()`, so running it as-is only prints the final state (the last `toString()`) and the one `get("Apple")` call. The version shown here uncomments them to make each `put` step visible.
