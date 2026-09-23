# Deserialization

This reads back the `student.ser` file written by [SerializationDemo](SerializationDemo.md) — run that one first, from the same working directory, or this file has nothing to read.

```java
public class DeserializationDemo {
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.ser"));
        Student s = (Student) in.readObject();
        in.close();

        System.out.println("Deserialized Student: " + s.getName());
    }
}
```

Output:

```
Person constructor called
Deserialized Student: Alice
```

`in.readObject()` reads the bytes back and reconstructs a `Student` object with `name` already set to `"Alice"` — without ever calling `Student`'s constructor. This is the whole point of the file's class-level comment:

> During deserialization:
> - `Student` constructor is **not** called (because it's `Serializable`)
> - `Person` constructor **is** called (because it's not `Serializable`)

Deserialization has to reconstruct the object's fields from raw bytes, not by calling `new Student(...)` — if it called the constructor normally, that would re-run `System.out.println("Student constructor called")`, which the output above shows does *not* happen. But since `Person` (the superclass) isn't `Serializable`, the JVM has no serialized data for *it* to restore — so it falls back to actually invoking `Person`'s ordinary no-arg constructor to initialize that part of the object, which is why `"Person constructor called"` prints even during deserialization. This is a genuinely easy thing to get backwards, and worth sitting with: the serializable class skips its constructor, while its non-serializable superclass doesn't.
