# Jump Statement: `continue`

`continue` skips the rest of the current iteration and jumps straight to the next one — unlike `break`, it does not exit the loop.

```java
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0)
        continue;

    System.out.println(i + " ");
}
```

Output:

```
1
3
5
7
9
```

For every even `i`, `continue` skips the `System.out.println` call and moves on to the next loop iteration, so only the odd numbers get printed.
