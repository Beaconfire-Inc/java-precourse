# String Pool

A `String` is a sequence of characters. In Java, `String` objects are **immutable** — once created, they cannot be changed. Because of this, Java can safely maintain a **String Pool**: a collection of strings stored in the heap that can be reused instead of creating duplicates.

## String literal vs. `new String(...)`

A string literal (e.g. `"dog"`) is automatically placed in the String Pool. If the same literal is used again, Java reuses the existing object instead of creating a new one. Calling `new String(...)`, on the other hand, always creates a brand-new object on the heap, outside the pool.

```java
String s1 = "dog";
String s2 = "dog";
String s3 = new String("dog");  // create new object in heap memory
```

`==` compares reference identity — whether two variables point to the exact same object — not content:

```java
System.out.println(s1 == s2);
```

Output:

```
true
```

`s1` and `s2` both point to the same `"dog"` in the String Pool.

```java
System.out.println(s1 == s3);
```

Output:

```
false
```

`s3` points to a separate object in heap memory, not the pooled `"dog"`.

## `intern()`

Calling `intern()` on a string returns the canonical pooled reference for that content: if an equal string is already in the pool, you get back that existing pooled instance (which is what happens below, since the literal `"dog"` is already there); otherwise, this string itself is added to the pool and returned. Either way, it lets you save memory and reuse existing strings instead of keeping duplicates around.

```java
s3 = s3.intern();
System.out.println(s1 == s3);
```

Output:

```
true
```

After interning, `s3` is reassigned to point at the same pooled `"dog"` as `s1` — the original heap object `s3` used to point to is left untouched and unreferenced; it wasn't moved anywhere.
