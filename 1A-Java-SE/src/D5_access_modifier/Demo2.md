# Access Modifiers: Same Package, Different Class

`Demo2` is in the same package (`D5_access_modifier`) as `Demo`, but it is a different class.

```java
package D5_access_modifier;

public class Demo2 {
    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.defaultField);
    }
}
```

Output:

```
0
```

`defaultField` has no access modifier (package-private / "default" access), which means it's visible to any class **within the same package** — so `Demo2` can read it even though it isn't `Demo` itself.

`privateField`, however, cannot be accessed here:

```java
System.out.println(demo.privateField);
```

This does not compile. `private` members are only visible inside their own declaring class — being in the same package isn't enough.
