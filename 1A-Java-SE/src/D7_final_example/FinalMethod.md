# `final` Method

Once a method is declared `final`, it cannot be overridden by a subclass.

```java
class Dog {
    final void eat() {
        System.out.println("Woof");
    }
}

class Husky extends Dog {
}
```

`Husky extends Dog`, but it cannot override `eat()`:

```java
class Husky extends Dog {
    @Override
    void eat() {}
}
```

This does not compile — the compiler blocks any subclass from providing its own version of a `final` method, since the whole point of marking it `final` is to guarantee its behavior can't be changed by inheritance.
