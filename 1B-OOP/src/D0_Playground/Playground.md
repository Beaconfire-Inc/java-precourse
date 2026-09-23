# Playground

This file isn't a typical "run it and read the output" demo — as written, it doesn't print anything at all. It was originally meant to be explored by setting a breakpoint on the last line and stepping through it in a debugger, watching each variable get created and inspecting its fields in the IDE's variables panel.

```java
package D0_Playground;

public class Playground {
    public static void main(String[] args) {
        Person alice = new Person("Alice", 25, "Female");
        Person bob = new Person("Bob", 30, "Male");
        Person carl = new Person("Carl", 22, "Non-binary");
        Person diana = new Person("Diana", 40, "Female");
        Person anotherGuy = new Person();
        int val = 10;
    }
}

class Person {
    String name;
    int age;
    String gender;

    public Person() {
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void sayHello() {
        System.out.println("Hello!");
    }
}
```

A few things worth noticing just from reading the code:

- `Person` has two constructors: a no-args one and a 3-argument one. `anotherGuy` is built with the no-args constructor, so its fields (`name`, `age`, `gender`) are left at their default values (`null`, `0`, `null`) instead of the values `alice`/`bob`/`carl`/`diana` were given.
- `val` is declared but never used for anything — it's just there so there's a plain local variable to inspect alongside the objects.

## Turning it into a self-check demo

Since this material is meant to be worked through without a live debugger session, here's the same file with `System.out.println` calls added, so you can see the values without stepping through it — this is not what the source file contains, just an easy way to check yourself:

```java
Person alice = new Person("Alice", 25, "Female");
Person bob = new Person("Bob", 30, "Male");
Person carl = new Person("Carl", 22, "Non-binary");
Person diana = new Person("Diana", 40, "Female");
Person anotherGuy = new Person();

System.out.println(alice.name + ", age " + alice.age + ", " + alice.gender);
System.out.println(bob.name + ", age " + bob.age + ", " + bob.gender);
System.out.println(carl.name + ", age " + carl.age + ", " + carl.gender);
System.out.println(diana.name + ", age " + diana.age + ", " + diana.gender);
System.out.println("anotherGuy: name=" + anotherGuy.name + ", age=" + anotherGuy.age + ", gender=" + anotherGuy.gender);

alice.sayHello();
```

Output:

```
Alice, age 25, Female
Bob, age 30, Male
Carl, age 22, Non-binary
Diana, age 40, Female
anotherGuy: name=null, age=0, gender=null
Hello!
```

**Try it yourself:** open `Playground.java`, set a breakpoint on the closing `}` of `main`, and run it in debug mode. Step through line by line and watch `alice`, `bob`, `carl`, `diana`, and `anotherGuy` appear in your IDE's variables panel — it's a good way to build intuition for what a constructor actually does to an object in memory.
