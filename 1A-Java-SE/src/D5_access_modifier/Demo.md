# Access Modifiers

Java has four access modifiers for fields, methods, constructors, and classes, each with a different visibility scope:

| Modifier | Class | Package | Subclass | Global |
|---|---|---|---|---|
| `public` | ✓ | ✓ | ✓ | ✓ |
| `protected` | ✓ | ✓ | ✓ | ✗ |
| *(default, no keyword)* | ✓ | ✓ | ✗ | ✗ |
| `private` | ✓ | ✗ | ✗ | ✗ |

```java
package D5_access_modifier; // folder's name

public class Demo { //public class has same name as the file
    int defaultField;
    private int privateField;
    public int publicField;
    protected int protectedField;

    int foo() {
        return this.privateField;
    }

    public static void main(String[] args) {
        System.out.println(new Demo().privateField);
    }
}
```

Even though `privateField` is `private`, it can still be accessed from **within the same class** — `foo()` and `main()` are both members of `Demo`, so both are allowed to read `privateField` directly.

Output:

```
0
```

`privateField` is never explicitly assigned, so it holds its default `int` value, `0`.
