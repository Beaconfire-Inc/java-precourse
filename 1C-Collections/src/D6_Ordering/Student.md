# Student (Helper Class)

`Student` has no `main` of its own — it's used by [AnonymousVSLambda.md](AnonymousVSLambda.md) as the object being sorted. It's included here because implementing `Comparable<Student>` is itself the point: it shows how a **custom** class gets a natural ordering, the same way `String`/`Date`/`Integer` already do (see [Ordering](Ordering.md)).

```java
public class Student implements Comparable<Student> {
    private int id;
    private String name;

    public Student() {}
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String toString() {
        return "Student is: " + this.id + " " + this.name;
    }

    @Override
    public int compareTo(Student o) {
        int idCompare = Integer.compare(this.id, o.id);
        if (idCompare == 0) {
            return this.name.compareTo(o.name);
        }
        return idCompare;
    }
}
```

`compareTo` defines `Student`'s natural ordering as: **by `id` first**, and if two students have the same `id`, **by `name`** as a tiebreaker (reusing `String`'s own `compareTo` for that second comparison). Once a class implements `Comparable` like this, `Collections.sort(students)` — with no second argument — works automatically, sorting by whatever `compareTo` says.

`equals`/`hashCode` are also overridden, based on both `id` and `name` together — standard practice for a class used in hash-based collections (`HashSet`, `HashMap` keys, etc.), and unrelated to the `Comparable` ordering above; `equals` decides whether two students are "the same," while `compareTo` decides which one comes first.
