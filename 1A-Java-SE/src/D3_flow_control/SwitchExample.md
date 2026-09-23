# Selection Statement: `switch`

`switch` compares a value against a list of `case` labels and runs the matching branch. `break` stops execution from "falling through" into the next case; `default` runs when nothing else matches.

```java
int i = 2;

switch (i) {
    case 0:
        System.out.println("Find 0");
        break; // If we don't use the 'break' statement, execution will keep running into case 1
    case 1:
        System.out.println("Find 1");
        break;
    default:
        System.out.println("default");
}
```

Output:

```
default
```

`i` is `2`, which doesn't match `case 0` or `case 1`, so execution falls through to `default`.
