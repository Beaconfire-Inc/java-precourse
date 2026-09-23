# `Supplier<T>`

`Supplier<T>` is a functional interface that takes **no** arguments and returns a value — it *supplies* something, typically by computing or creating it on demand.

```java
Supplier<String> helloSupplier = () -> "Hello World";
System.out.println(helloSupplier.get());
```

Output:

```
Hello World
```

`helloSupplier` matches `Supplier<String>`'s single abstract method, `get()`, which takes nothing and returns a `String`. `Supplier` is the "opposite" of [`Consumer`](ConsumerDemo.md) (which takes an argument and returns nothing) — between the two, and [`Function`](FunctionDemo.md) (which does both), the `java.util.function` package covers the shapes of computation Java's functional-style APIs need.

This particular pattern — deferring a computation until `.get()` is actually called — already showed up in [OptionalDemo](../D1_Exception/OptionalDemo.md)'s `orElseGet(Supplier)`: the fallback value is only computed when it's genuinely needed, instead of being built eagerly every time regardless of whether it'll be used.
