# Serialization

Serialization converts an object's state into a byte stream that can be saved to disk (or sent over a network) and reconstructed later. This topic is split across two files — this one writes an object out, and [DeserializationDemo](DeserializationDemo.md) reads it back — so it's worth running them in that order (see the note at the bottom).

## The classes

```java
// Superclass does NOT implement Serializable
class Person {
    public Person() {
        System.out.println("Person constructor called");
    }
}

// Subclass DOES implement Serializable
class Student extends Person implements Serializable {
    private String name;

    public Student(String name) {
        System.out.println("Student constructor called");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Address {
    String description;
}
```

The key detail is right there in the comments: `Student implements Serializable`, but its superclass `Person` does **not**. `Serializable` is a *marker interface* — it has no methods to implement, it just flags a class as eligible to be serialized. `Address` also isn't `Serializable`, but it isn't used in this file at all; it's a leftover from exploring what happens when a `Serializable` class holds a non-serializable field (not exercised here).

## Writing the object

```java
public static void main(String[] args) throws Exception {
    Student s = new Student("Alice");

    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.ser"));
    out.writeObject(s);
    out.close();

    System.out.println("Object serialized.\n");
}
```

Output:

```
Person constructor called
Student constructor called
Object serialized.

```

Creating `new Student("Alice")` runs both constructors normally — `Person`'s implicit no-arg constructor first (every constructor chain ends at `Object` by way of each superclass), then `Student`'s own — which is why both print statements show up before serialization even starts. `ObjectOutputStream.writeObject(s)` then walks `s`'s fields and writes their values to `student.ser` as bytes. This step doesn't call any constructor — it's just reading the already-constructed object's state.

> **Where `student.ser` ends up:** it's written to whatever directory the JVM's current working directory is when you run this — with the `cd src && java D4_Serialization.SerializationDemo` pattern used throughout this repo, that means it appears inside `src/`. Run [DeserializationDemo](DeserializationDemo.md) from that same directory afterward so it can find the file. (`student.ser` is a generated artifact, not source — it's excluded from this repo's git history, so you'll need to run this file yourself to produce it before `DeserializationDemo` has anything to read.)
