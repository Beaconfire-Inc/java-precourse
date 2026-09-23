# The Diamond Problem

The "diamond problem" happens when a class could inherit the same method from two different parent classes, and the compiler can't tell which one it should use. Java sidesteps this entirely by **not allowing multiple inheritance for classes** — a class can only `extends` one other class. This file shows why that restriction exists, and the two common ways Java code still gets diamond-shaped behavior safely: interfaces and aggregation.

## The classes involved

```java
abstract class BaseA {
    public abstract void test();
}

class IntermediateB extends BaseA {
    public void test() {
        System.out.println("Hello from Intermediate B");
    }
}

class IntermediateC extends BaseA {
    public void test() {
        System.out.println("Hello from Intermediate C");
    }
}
```

`IntermediateB` and `IntermediateC` both extend `BaseA` and both implement `test()` differently.

## Why you can't just inherit from both

```java
class SubClassD extends IntermediateB, IntermediateC {
    public void test() {
        super.test();
        System.out.println("Hello July!");
    }
}
```

This does not compile. If a class could extend both `IntermediateB` and `IntermediateC`, then `super.test()` inside `SubClassD` would be ambiguous — should it call `IntermediateB`'s version or `IntermediateC`'s version? Java avoids the question by simply not allowing a class to appear twice in an `extends` clause (Java only supports single inheritance for classes).

## Solution 1: interfaces

```java
interface DemoInterface1 {
    void test();
}

interface DemoInterface2 {
    void test();
}

class SubClassD implements DemoInterface1, DemoInterface2 {
    public void test() {
        System.out.println("Hello May!");
    }

    public void func() {
        System.out.println("Function New");
    }
}
```

A class *can* implement multiple interfaces, even if they declare the same method signature — because interfaces (before Java 8's default methods) only declare *what* a method looks like, not *how* it behaves. There's no ambiguity about which implementation to inherit, because neither interface provides one; `SubClassD` has to supply its own `test()` body.

```java
SubClassD d = new SubClassD();
d.test(); // Works with interfaces, but it will cause ambiguity here if multiple inheritance is used
```

## Solution 2: aggregation

```java
class AnotherSubClass {
    IntermediateB b;
    IntermediateC c;

    AnotherSubClass(IntermediateB b, IntermediateC c) {
        this.b = b;
        this.c = c;
    }

    AnotherSubClass() {
        this.b = new IntermediateB();
        this.c = new IntermediateC();
    }

    void testBoth() {
        System.out.println("Testing both:");
        b.test();
        c.test();
    }
}
```

Instead of trying to *become* both an `IntermediateB` and an `IntermediateC` through inheritance, `AnotherSubClass` simply *holds a reference* to one of each (this is called aggregation, a "has-a" relationship instead of an "is-a" relationship). There's no ambiguity, because `b.test()` and `c.test()` are two clearly separate method calls on two separate objects.

## Running the demo

```java
public static void main(String[] args) {
    IntermediateB b = new IntermediateB();
    IntermediateC c = new IntermediateC();
    AnotherSubClass aSub = new AnotherSubClass(b, c);
    aSub.testBoth();
}
```

Output:

```
Testing both:
Hello from Intermediate B
Hello from Intermediate C
```
