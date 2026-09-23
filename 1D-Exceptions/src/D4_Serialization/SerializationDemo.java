package D4_Serialization;

import java.io.*;

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
public class SerializationDemo {
    public static void main(String[] args) throws Exception {
        Student s = new Student("Alice");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.ser"));
        out.writeObject(s);
        out.close();

        System.out.println("Object serialized.\n");
    }
}
