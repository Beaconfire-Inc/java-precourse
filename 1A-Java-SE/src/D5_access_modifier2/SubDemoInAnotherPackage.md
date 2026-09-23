# Access Modifiers: `protected` Across Packages via Inheritance

`SubDemoInAnotherPackage` is in a different package than `Demo`, but it **extends** `Demo` — this is the one case where `protectedField` becomes reachable from outside `Demo`'s own package.

```java
package D5_access_modifier2;

import D5_access_modifier.Demo;

public class SubDemoInAnotherPackage extends Demo {
    void foo(){
        Demo demo = new Demo();
        System.out.println(protectedField); // can access protectedField of its parent class, but not other Demo instances
    }

    public static void main(String[] args) {
        SubDemoInAnotherPackage subDemoInAnotherPackage = new SubDemoInAnotherPackage();
        subDemoInAnotherPackage.foo();
    }
}
```

Output:

```
0
```

Note the asymmetry inside `foo()`:

```java
System.out.println(demo.protectedField); // cannot do that
```

This line does **not** compile, even though `foo()` is inside a subclass of `Demo`. When accessing an **inherited** `protected` member from a different package, you can only do so through the subclass's own instance (`this`, i.e. `protectedField` or `this.protectedField`) — not through an arbitrary separate instance of the parent class (`demo.protectedField`). That's why the working line prints `protectedField` directly (referring to the current object's inherited field) rather than going through the local `demo` variable.
