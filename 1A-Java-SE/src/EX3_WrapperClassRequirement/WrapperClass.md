# Exercise: Why Wrapper Classes Are Needed

Generic types like `List<T>` can only hold objects — they cannot be parameterized with a primitive type like `int`. That's exactly what wrapper classes (`Integer`, `Double`, `Character`, `Boolean`, etc.) are for: each one wraps a primitive value inside an object, so it can be used anywhere an object is required.

```java
package EX3_WrapperClassRequirement;

import java.util.ArrayList;
import java.util.List;

public class WrapperClass {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        int[] arr = new int[20];
        list.add(1); // auto-boxing: primitive -> object
        System.out.println(list.get(0).getClass().getName());

        System.out.println(Integer.valueOf("12"));
        System.out.println(Integer.MAX_VALUE);
    }
}
```

Output:

```
java.lang.Integer
12
2147483647
```

- `int[] arr` is declared as a contrast: arrays of primitives (`int[]`) are allowed directly, but you cannot write `List<int>` — this is exactly why `List<Integer>` exists.
- `list.add(1)` — even though `1` is a primitive `int` literal, Java automatically wraps it into an `Integer` object before adding it to the list. This automatic conversion is called **autoboxing**. `list.get(0).getClass().getName()` confirms this by printing `java.lang.Integer`, not `int`.
- `Integer.valueOf("12")` parses a `String` into an `Integer` object.
- `Integer.MAX_VALUE` is a constant exposing the largest possible `int` value, `2147483647`.
