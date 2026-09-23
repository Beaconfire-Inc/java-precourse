# Polymorphism

"Polymorphism" means the same method call can behave differently depending on context. Java has two kinds: **overriding** (decided at runtime, based on the actual object type) and **overloading** (decided at compile time, based on the argument types).

## Runtime polymorphism: overriding

```java
class Animal {
    public Animal sleep() {
        System.out.println("Animal sleep");
        return new Animal();
    }

    public Animal sleep(int hours) {
        System.out.println("Animal sleep for " + hours + " hours");
        return new Animal();
    }
}

class Cat extends Animal {
    @Override
    public Cat sleep() {
        System.out.println("Cat sleep");
        return new Cat();
    }

    public String someThing() {
        return "";
    }
}
```

```java
Animal cat = new Cat();
cat.sleep();
cat.sleep(8);
```

The variable `cat` is declared with type `Animal`, but the object it actually points to is a `Cat`. When `sleep()` is called, Java looks at the *actual* object's type at runtime — not the variable's declared type — and runs `Cat`'s overridden version. That's why this prints `"Cat sleep"`, not `"Animal sleep"`.

`sleep(int hours)`, on the other hand, is only defined on `Animal` — `Cat` doesn't override it — so `cat.sleep(8)` runs `Animal`'s version and prints `"Animal sleep for 8 hours"`.

Also notice `Cat.sleep()` is declared to return `Cat`, while `Animal.sleep()` returns `Animal`. This is legal — it's called a *covariant return type*: since `Cat` **is an** `Animal`, returning the more specific `Cat` still satisfies the `Animal` return type from the parent method.

## Compile-time polymorphism: overloading

```java
class Calculator {
    int num1 = 1;

    void add(int num1, int num2) {
        System.out.println(num1 + num2);
    }

    Integer add(long num1, long num2) {
        System.out.println(num1 + num2);
        return 1;
    }

    void add(double num1, int num2) {
        System.out.println(num1 + num2);
    }
}
```

```java
Calculator c = new Calculator();
c.add(1, 2);
c.add(1L, 2L);
c.add(1.0, 2);
```

There are three methods here all named `add`, differing only in their parameter types — this is overloading. Unlike overriding, which one runs isn't decided by any object's runtime type; it's decided purely by matching the argument types against each method's signature at compile time:

- `c.add(1, 2)` — two `int` literals match `add(int, int)`.
- `c.add(1L, 2L)` — two `long` literals match `add(long, long)`.
- `c.add(1.0, 2)` — a `double` and an `int` match `add(double, int)`.

## Running the demo

```java
public static void main(String[] args) {
    Animal cat = new Cat();
    cat.sleep();
    cat.sleep(8);

    System.out.println("-----------------");

    Calculator c = new Calculator();
    c.add(1, 2);
    c.add(1L, 2L);
    c.add(1.0, 2);
}
```

Output:

```
Cat sleep
Animal sleep for 8 hours
-----------------
3
3
3.0
```

> **Note on the source file:** `main()` in `PolymorphismDemo.java` is entirely commented out, so running the file as-is prints nothing. The code above is the same logic, uncommented and confirmed working — that's what's shown running here.
