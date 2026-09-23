# MyHashMapNode (Helper Class)

This is a supporting class for [MyHashMap](MyHashMap.md) — it has no `main` and isn't meant to be run on its own. It represents one entry in a hand-built hash map: a key, a value, and a link to the *next* node in the same bucket.

```java
public class MyHashMapNode<K, V> {
    private K key;
    private V value;
    private MyHashMapNode<K, V> next;

    public MyHashMapNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public MyHashMapNode<K, V> getNext() {
        return next;
    }

    public void setNext(MyHashMapNode<K, V> next) {
        this.next = next;
    }
}
```

That `next` field is the key thing to notice: this node doesn't just hold one key-value pair, it can **chain** to another node. That's the building block for **separate chaining** — the strategy [MyHashMap](MyHashMap.md) uses to handle the case where two different keys hash to the same bucket. Instead of overwriting one with the other, the bucket becomes a small linked list of `MyHashMapNode`s, and `MyHashMap` walks that chain to find the right one.
