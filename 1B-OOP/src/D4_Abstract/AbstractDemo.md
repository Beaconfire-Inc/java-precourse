# Abstract Classes & Interfaces

This demo contrasts two ways to define a shared contract in Java: an **abstract class** (`Animal`), which can hold real, shared implementation alongside methods its subclasses must fill in, and an **interface** (`Flyable`), which here declares a contract with no implementation of its own. (That's true of `Flyable` specifically, not of interfaces in general — since Java 8, an interface can also provide real method bodies via `default`/`static` methods, as seen with the functional interfaces later in this program.)

## An interface: a pure contract

```java
interface Flyable {
    void fly();
}
```

`Flyable` doesn't say *how* to fly — it just says that any class implementing it must provide a `fly()` method.

## An abstract class: shared implementation plus required methods

```java
abstract class Animal {
    public Animal() {
        System.out.println("Constructor of abstract class Animal");
    }

    public void eat() {
        System.out.println("Animal is eating");
    }

    abstract void makeSound();
}
```

Unlike an interface, `Animal` can have a real constructor and a fully-implemented method (`eat()`) that every subclass inherits as-is. It also declares `makeSound()` as `abstract`, meaning it has no body here — every concrete subclass is required to provide its own implementation. You can't do `new Animal()` directly, since it's abstract, but its constructor still runs whenever a subclass is instantiated.

## Two unrelated classes sharing the `Flyable` contract

```java
class Bird extends Animal implements Flyable {
    public Bird() {
        System.out.println("Constructor of Bird");
    }

    @Override
    public void fly() {
        System.out.println("The bird flaps its wings and soars into the sky.");
    }

    @Override
    public void makeSound() {
        System.out.println("Bird chirps");
    }
}

class Airplane implements Flyable {
    public Airplane() {
        System.out.println("Constructor of Airplane");
    }

    @Override
    public void fly() {
        System.out.println("The airplane starts its engines and lifts off the runway.");
    }

    public void startEngine() {
        System.out.println("Airplane engine started");
    }
}
```

`Bird` extends `Animal` (it *is an* animal, so it inherits `eat()` and must implement `makeSound()`) and separately implements `Flyable`. `Airplane` isn't an `Animal` at all — it has nothing to do with the `Animal` hierarchy — but it can still implement `Flyable`, because an interface isn't tied to any particular class hierarchy. Both classes fulfill the same `fly()` contract without being related to each other.

## Running the demo

```java
public static void main(String[] args) {
    Bird bird = new Bird();
    bird.eat();
    bird.fly();
    bird.makeSound();

    Airplane plane = new Airplane();
    plane.fly();
    plane.startEngine();

    // Both Bird and Plane can fly
    Flyable[] flyers = { bird, plane };
    for (Flyable f : flyers) {
        f.fly();
    }

    Animal animal = new Bird();
}
```

Output:

```
Constructor of abstract class Animal
Constructor of Bird
Animal is eating
The bird flaps its wings and soars into the sky.
Bird chirps
Constructor of Airplane
The airplane starts its engines and lifts off the runway.
Airplane engine started
The bird flaps its wings and soars into the sky.
The airplane starts its engines and lifts off the runway.
Constructor of abstract class Animal
Constructor of Bird
```

Two things worth noticing in the output:

- Creating a `Bird` prints **both** `"Constructor of abstract class Animal"` and `"Constructor of Bird"` — even though `Animal` is abstract and can never be instantiated on its own, its constructor still runs as part of building any subclass instance.
- `Flyable[] flyers = { bird, plane }` groups a `Bird` and an `Airplane` together purely because they both implement `Flyable`, even though one is an `Animal` and the other isn't. Calling `f.fly()` in the loop runs each object's own `fly()` implementation — this is the same runtime polymorphism idea covered in the Polymorphism topic, just driven by an interface type instead of a class type.
