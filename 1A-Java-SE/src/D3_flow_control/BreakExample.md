# Jump Statement: `break`

`break` immediately terminates the loop it's inside, skipping any remaining iterations.

```java
for (int i = 0; i < 10; i++) {
    if (i == 5)
        break;
    System.out.println("i: " + i);
}
System.out.println("Loop complete.");
```

Output:

```
i: 0
i: 1
i: 2
i: 3
i: 4
Loop complete.
```

The loop is set up to run from `0` to `9`, but as soon as `i` reaches `5`, `break` exits the loop entirely — the remaining iterations (`5` through `9`) never happen, and control moves straight to `"Loop complete."`.
