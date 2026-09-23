# Jump Statement: `return`

`return` exits the current method immediately — any code after it in that method does not execute.

```java
boolean t = true;
System.out.println("Before the return.");

if (t)
    return;

System.out.println("This won't execute");
```

Output:

```
Before the return.
```

Since `t` is `true`, the method returns as soon as it hits `return;`. The last `System.out.println` is never reached.
