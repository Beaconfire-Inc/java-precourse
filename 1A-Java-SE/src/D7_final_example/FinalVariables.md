# `final` Variable

`final` means a variable can be assigned a value only once — after that, any attempt to reassign it is a compile error. (`pi` here also happens to satisfy Java's stricter definition of a *compile-time constant*, since it's `final`, a primitive, and initialized with a literal — but `final` alone doesn't guarantee that: a `final` variable initialized from a method call, for instance, still can't be reassigned, yet isn't a compile-time constant. `final` is also a different thing from *immutability*: a `final` reference to a mutable object still lets you change that object's internal state — it just stops you from pointing the reference at a different object.)

```java
public static void main(String[] args) {
    final double pi = 3.1415926;
}
```

Trying to assign a new value afterward does not compile:

```java
pi = 4;
```

`pi` is `final`, so the compiler rejects any further assignment to it once its initial value has been set.
