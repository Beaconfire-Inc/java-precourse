# Inheritance

This demo shows a small inheritance chain: `Animal` is the base class, `Dog` extends it, and `Corgi` and `Bulldog` both extend `Dog`.

> **Note:** the source file's class-level comment lists "Chihuahua" as one of the derived classes, but the actual class is named `Corgi` — a naming difference preserved from an earlier version of the example. This write-up uses `Corgi`, matching the code.

## The class hierarchy

```java
class Animal {
    public int age;

    public Animal() {
        // Implicit constructor call to Object class
        System.out.println("An animal is created.");
    }

    public Animal(int age) {
        this.age = age;
    }

    public void eat() {
        System.out.println("This animal eats food.");
    }
}

class Dog extends Animal {
    public String name;

    public Dog(int age, String name) {
        super(age);
        this.name = name;
    }

    public Dog() {
        // Implicit call to super() here
        System.out.println("A dog is born.");
    }

    public void bark() {
        System.out.println("The dog barks.");
    }
}

class Corgi extends Dog {
    Double tall;

    public Corgi(int age, String name, Double tall) {
        super(age, name);
        this.tall = tall;
    }

    public Corgi() {
        this(1, "", 2.0);
        System.out.println("A Corgi is born.");
    }

    public void wagTail() {
        System.out.println("The Corgi wags its tail happily.");
    }
}

class Bulldog extends Dog {
    public Bulldog() {
        // Implicit call to super()
        System.out.println("A Bulldog is born.");
    }

    public void snore() {
        System.out.println("The Bulldog snores loudly.");
    }
}
```

A few things to notice:

- `Animal` has two constructors: a no-args one that prints `"An animal is created."`, and a 1-argument one that just sets `age` without printing. Every constructor in `Dog`, `Corgi`, and `Bulldog` eventually calls one of these two, directly or through `super(...)`.
- `Dog`'s no-args constructor doesn't explicitly call `super(...)`, so Java inserts an implicit call to `Animal()` — that's why building a `Bulldog` (which uses `Dog`'s no-args constructor) still prints `"An animal is created."` before `"A dog is born."`.
- `Corgi`'s no-args constructor calls `this(1, "", 2.0)` instead, which chains to `Corgi(int, String, Double)` → `super(age, name)` → `Dog(int, String)` → `super(age)` → `Animal(int age)`. That path uses `Animal`'s 1-argument constructor, which does **not** print anything — so creating a `Corgi` this way skips `"An animal is created."` entirely.
- `Corgi` and `Bulldog` each add their own behavior (`wagTail()`, `snore()`) on top of what they inherit from `Dog` (`bark()`) and `Animal` (`eat()`).

## Running the demo

```java
public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("Creating a Corgi:");
        Corgi corgi = new Corgi();
        corgi.eat();      // Inherited from Animal
        corgi.bark();     // Inherited from Dog
        corgi.wagTail();

        System.out.println("-----------------");

        System.out.println("Creating a Bulldog:");
        Bulldog bulldog = new Bulldog();
        bulldog.eat();     // Inherited from Animal
        bulldog.bark();    // Inherited from Dog
        bulldog.snore();
    }
}
```

Output:

```
Creating a Corgi:
A Corgi is born.
This animal eats food.
The dog barks.
The Corgi wags its tail happily.
-----------------
Creating a Bulldog:
An animal is created.
A dog is born.
A Bulldog is born.
This animal eats food.
The dog barks.
The Bulldog snores loudly.
```

Notice that `corgi.eat()` and `bulldog.eat()` both work, and both print the same message — `eat()` is defined once on `Animal` and inherited unchanged by every subclass. Same idea for `bark()`, defined once on `Dog` and inherited by both `Corgi` and `Bulldog`.

> **Note on the source file:** `InheritanceDemo.java`'s actual `main()` only creates a `Bulldog` and doesn't call `eat()`/`bark()`/`snore()` on it — those lines, along with the entire `Corgi` walkthrough shown above, are commented out. The source also contains a separate class, `DogPerson` (with a private field named `Bob` instead of `bob`), and an additional `public static void main` inside the `Animal` class itself that just creates a `DogPerson` and calls `getAge()` on it. Neither of these is part of the inheritance demo — they appear to be exploratory code from an earlier version of the file — so they're left out of this write-up, and the version shown above is an expanded runnable version of the inheritance chain.
