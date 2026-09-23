package D5_HashMap_Internals;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucket.length; i++) {
            sb.append("[").append(i).append("]: ");
            MyHashMapNode<K, V> currentNode = bucket[i];
            while(currentNode != null) {
                sb.append("{").append(currentNode.getKey()).append(":").append(currentNode.getValue()).append("} ");
                sb.append("-> ");
                currentNode = currentNode.getNext();
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        MyHashMap<String, Integer> map = new MyHashMap<>(10);
//        Map<String, Integer> map = new HashMap<>();

        map.put("Apple", 20);
//        System.out.println(map);
//        System.out.println(map.get("Apple"));

        map.put("Apple", -10);
//        System.out.println(map);
//
        map.put("Banana", 30);
//        System.out.println(map);

        map.put("Mango", 100);
        System.out.println(map);
    }
}
