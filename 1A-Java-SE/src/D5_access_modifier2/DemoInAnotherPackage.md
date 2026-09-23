# Access Modifiers: Different Package

`DemoInAnotherPackage` lives in a different package (`D5_access_modifier2`) than `Demo` (`D5_access_modifier`), and imports it.

```java
package D5_access_modifier2;

import D5_access_modifier.Demo;

public class DemoInAnotherPackage {
    // Only able to access public fields
    int foo() {
        Demo demo = new Demo();
        return demo.publicField;
    }
}
```

From a different package, only `publicField` is reachable — `defaultField` (package-private) and `protectedField` are both off-limits here, since this class is neither in the same package nor a subclass of `Demo`. Only `public` members are visible across package boundaries to unrelated classes.

This class has no `main` method, so it isn't meant to be run directly — it exists to illustrate what a class from another package *can* and *can't* reach on `Demo`.
