# Static vs. Instance Members

```java
package D6_static_example;

public class Sample {

    public static int staticVariable = 100;
    private static int psv;

    public int instanceVariable;

    public Sample(int instanceVariable) {
        this.instanceVariable = instanceVariable;
    }

    public int getInstanceVariable(){
        return instanceVariable;
    }

    public static int getStaticVariable(){
        return staticVariable;
    }
}
```

- `staticVariable` belongs to the **class** itself. There is only one copy of it, shared by every `Sample` object.
- `instanceVariable` belongs to each **object**. Every `Sample` instance gets its own independent copy.
- `getInstanceVariable()` is a non-static (instance) method — it needs an actual object to run against, because it reads that object's own `instanceVariable`.
- `getStaticVariable()` is a static method — it can be called without creating any `Sample` object at all, because it only reads the shared `staticVariable`.

See [Driver.java](Driver.md) for a runnable demonstration of how these behave differently.
