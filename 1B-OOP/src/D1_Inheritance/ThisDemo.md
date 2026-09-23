# The `this` Keyword

`this` shows up in a few different roles in Java. This file walks through each one.

```java
public class ThisDemo {
    private int number;

    public ThisDemo() {
        this(123);
    }

    public ThisDemo(int number) {
        this.number = number;
        System.out.println("this reference: " + this);
    }

    public ThisDemo returnMe() {
        return this;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void displayNumber() {
        timesTwo(this);
    }

    private void timesTwo(ThisDemo demo) {
        System.out.println("displayNumber: " + demo.number * 2);
    }
}
```

## 1. `this(...)` — calling another constructor of the same class

```java
public ThisDemo() {
    // calls the constructor with a single parameter
    // if you use this() to refer to a constructor, it needs to be the first line in the body
    this(123);
}
```

`this(123)` hands off construction to `ThisDemo(int number)` instead of duplicating its logic. Java requires this call to be the very first statement in the constructor.

## 2. `this` — referring to the current object

```java
public ThisDemo returnMe() {
    return this;
}
```

Inside an instance method, `this` refers to the object the method was called on — so `returnMe()` just hands back a reference to itself.

## 3. `this.fieldName` — referring to a field

```java
public void setNumber(int number) {
    this.number = number;
}
```

The parameter `number` and the field `number` share a name. `this.number` disambiguates: it explicitly means "the field on this object," not the parameter.

## 4. `this.` can be omitted when there's no naming conflict

```java
public int getNumber() {
    return number;
}
```

Here there's no parameter named `number`, so `number` unambiguously refers to the field — `this.number` and `number` mean the same thing in this context.

## Passing the current object as an argument

```java
public void displayNumber() {
    timesTwo(this);
}

private void timesTwo(ThisDemo demo) {
    System.out.println("displayNumber: " + demo.number * 2);
}
```

`this` isn't only for referring to fields — it's a normal object reference, so it can be passed into another method just like any other argument.

## Running the demo

```java
public static void main(String[] args) {
    ThisDemo demo2 = new ThisDemo();
    System.out.println("demo2 getNumber: " + demo2.getNumber());
    demo2.displayNumber();
}
```

Output:

```
this reference: D1_Inheritance.ThisDemo@<hashcode>
demo2 getNumber: 123
displayNumber: 246
```

(The exact hash code after `@` will differ every time you run it — it's derived from the object's identity in memory, not something fixed.)

`demo2 = new ThisDemo()` triggers the chain from section 1: the no-args constructor calls `this(123)`, which sets `number` to `123` and prints the `this` reference. `demo2.getNumber()` then confirms `number` is `123`, and `demo2.displayNumber()` passes `demo2` itself into `timesTwo(...)`, which doubles its `number` field.

> **Note on the source file:** `main()` also has a commented-out block that constructs a `ThisDemo` directly via `new ThisDemo(42)` and reads back `getNumber()`. It's worth uncommenting and running yourself to see the constructor overload in section 1 used from the other direction — going straight to `ThisDemo(int number)` instead of through the no-args constructor.
