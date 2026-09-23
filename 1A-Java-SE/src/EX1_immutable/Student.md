# Exercise: Immutable Class

An immutable object's state can never change after construction. In Java, the standard recipe for an immutable class is:

- Declare the class `final` (so it can't be subclassed into a mutable version)
- Make every field `private` and `final`, with no setters
- Have the constructor **deep-copy** any mutable input (like a `List`), instead of storing the caller's reference directly
- Have any getter that returns a mutable field also return a **copy**, not the internal reference itself

```java
package EX1_immutable;

import java.util.ArrayList;
import java.util.List;

public final class Student {

    private final String name;
    private final List<String> courses; // memory address won't change

    // The constructor uses a deep copy to initialize the list field
    public Student(String name, List<String> courses) {
        this.name = name;
        this.courses = new ArrayList<>();
        for (String course : courses) {
            this.courses.add(course);
        }
    }

    // The getter also performs a deep copy, rather than returning the internal reference
    public List<String> getCourses() {
        List<String> temp = new ArrayList<>();
        for (String c : courses) {
            temp.add(c);
        }
        return temp;
    }

    @Override
    public String toString(){
        return "Student Name: " + name + ", " +
                "Courses" + courses;
    }
}
```

## Why this matters

```java
List<String> courses = new ArrayList<>();
courses.add("Spring Boot");
courses.add("SQL");

Student s1 = new Student("Mark", courses);
System.out.println(s1);

// try to modify the object returned by the getter
List<String> existCourses = s1.getCourses();
existCourses.add("Hibernate");
existCourses.add("Maven");
System.out.println(s1);
System.out.println(s1.getCourses());

System.out.println(existCourses);
```

Output:

```
Student Name: Mark, Courses[Spring Boot, SQL]
Student Name: Mark, Courses[Spring Boot, SQL]
[Spring Boot, SQL]
[Spring Boot, SQL, Hibernate, Maven]
```

Even after adding `"Hibernate"` and `"Maven"` to `existCourses`, `s1`'s internal course list is unaffected — printing `s1` still shows only the original two courses. That's because `getCourses()` handed back a brand-new `List` (a copy), not a reference to `Student`'s actual internal list. If the getter had returned `courses` directly, modifying `existCourses` would have silently mutated `s1`'s "immutable" state from the outside — defeating the whole point of the class.
