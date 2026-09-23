# Map

`Map` stores key-value pairs. Unlike `List`, `Set`, and `Queue`, `Map` is **not** a `Collection` at all — it's a separate interface (a `Map` doesn't hold single elements, it holds *pairs*), though it's still considered part of the Collections Framework.

## Basic `HashMap` operations

```java
Map<String,String> stores = new HashMap();
stores.put("Store 1","Costco");
stores.put("Store 2","Walmart");
stores.put("Store 3","CVS");
stores.put("Store 4","WholeFoods");

System.out.println(stores.get("Store 1"));
stores.remove("Store 1");
System.out.println(stores);

System.out.println(stores.containsKey("Store 1"));
System.out.println(stores.containsValue("Costco"));

System.out.println(stores.keySet());
System.out.println(stores.values());
System.out.println(stores.entrySet());
```

Output:

```
Costco
{Store 2=Walmart, Store 3=CVS, Store 4=WholeFoods}
false
false
[Store 2, Store 3, Store 4]
[Walmart, CVS, WholeFoods]
[Store 2=Walmart, Store 3=CVS, Store 4=WholeFoods]
```

- `get("Store 1")` returns `"Costco"`, the value tied to that key.
- `remove("Store 1")` deletes that entry entirely — afterward, `containsKey("Store 1")` and `containsValue("Costco")` both correctly report `false`, since removing the key also took its value with it.
- `keySet()` returns just the keys, `values()` returns just the values, and `entrySet()` returns the key-value pairs together (each one printed as `key=value`) — three different views over the same underlying data.

> **Note:** `new HashMap()` here is a *raw type* — no `<String, String>` on the right-hand side. It compiles (with an unchecked-conversion warning) because the left-hand side's type, `Map<String,String>`, is used for all the type-checking that matters. Still, the more consistent style is `new HashMap<>()`, letting the diamond operator infer the type from the declared variable — that's what's used everywhere else in this file.

## `LinkedHashMap` and `TreeMap` — two different orderings

```java
Map<Integer,String> linkedHashMap = new LinkedHashMap<>();
linkedHashMap.put(1,"one");
linkedHashMap.put(3,"three");
linkedHashMap.put(2,"two");

System.out.println("LinkedHashMap is: " + linkedHashMap);

Map<Integer,String> treeMap = new TreeMap<>();
treeMap.put(1,"one");
treeMap.put(3,"three");
treeMap.put(2,"two");

System.out.println("TreeMap is: " + treeMap);
```

Output:

```
LinkedHashMap is: {1=one, 3=three, 2=two}
TreeMap is: {1=one, 2=two, 3=three}
```

Both are put in the same order (`1, 3, 2`), but they print differently:

- **`LinkedHashMap`** preserves **insertion order** — same idea as `LinkedHashSet` from the `Set` topic. It prints back out in exactly the order the keys were `put`.
- **`TreeMap`** keeps its keys in **sorted order** at all times (ascending, by default) — it prints `1, 2, 3` regardless of the order they were inserted in, because it's backed by a sorted tree structure (a red-black tree), not insertion history.

A plain `HashMap`, for comparison, guarantees neither — like `HashSet`, its iteration order depends on hash codes and shouldn't be relied on.
