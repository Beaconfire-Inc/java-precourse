# `final` Class

Once a class is declared `final`, it cannot be extended/subclassed at all.

```java
final class Bank {
    int cash;
}
```

Trying to subclass it does not compile:

```java
class MyBank extends Bank {
}
```

Because `Bank` is `final`, no other class is allowed to `extends` it — the compiler rejects `MyBank` outright.
