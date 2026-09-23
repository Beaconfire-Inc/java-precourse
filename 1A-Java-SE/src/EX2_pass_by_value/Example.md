# Exercise: Pass-by-Value

Java is always **pass-by-value** — including for objects. What gets copied into a method parameter is either the primitive value itself, or (for objects) a copy of the *reference* (the address pointing to the object). This file demonstrates what that does and doesn't let you do.

```java
package EX2_pass_by_value;

class Cat {
    String name;
    Cat(String name) {
        this.name = name;
    }
}
```

## Primitives: the method gets its own copy of the value

```java
public static void test1(int value) {
    value = 1;
}
```

```java
int a = 0;
test1(a);
System.out.println(a);
```

Output:

```
0
```

`test1` receives a *copy* of `a`'s value. Reassigning the parameter `value` inside the method has no effect on the caller's `a`.

## Reassigning an object parameter doesn't affect the caller's variable

```java
public static void foo(Cat c) {
    c = new Cat("Oliver");
}
```

```java
Cat aCat = new Cat("Keane");
foo(aCat);
System.out.println(aCat.name);
```

Output:

```
Keane
```

`aCat` is copied into `foo`'s parameter `c` — but that copy is of the *reference*, not the object itself. Reassigning `c` to point at a brand-new `Cat("Oliver")` only changes what the local variable `c` points to; it doesn't change what `aCat` (back in `main`) points to. `aCat` still points at the original `"Keane"` cat.

## Mutating the object through the reference *does* affect the caller

```java
public static void foo2(Cat c) {
    c.name = "Jessie";
}
```

```java
foo2(aCat);
System.out.println(aCat.name);
```

Output:

```
Jessie
```

This time, `foo2` doesn't reassign `c` to a different object — it follows the reference it was given and modifies the *same* `Cat` object that `aCat` also points to. Since both `c` and `aCat` point at the same object in memory, the change is visible through `aCat` too.
