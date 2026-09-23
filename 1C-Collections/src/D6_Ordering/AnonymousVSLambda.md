# Custom Ordering — `Comparator`: Anonymous Class vs. Lambda

[Student](Student.md) already has a natural ordering, defined once inside the class itself via `Comparable`. But sometimes you want to sort the *same* objects a *different* way without touching the class — that's what `Comparator` is for: an ordering rule defined **externally**, passed in wherever you need it.

## Natural ordering, for comparison

```java
Collections.sort(students);
System.out.println(students);
```

Output:

```
Student is: 1 Alice
Student is: 2 Bob
Student is: 2 Xay
Student is: 3 Charlie
```

`Collections.sort(students)` with no second argument uses `Student`'s own `compareTo` — the natural ordering (by `id`, then `name`) covered in [Student.md](Student.md).

## `Comparator` as an anonymous class

```java
Comparator<Student> studentComparator1 = new Comparator<Student>() {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getId() - o2.getId();
    }
};

Collections.sort(students, studentComparator1);
students.forEach(System.out::println);
```

Output:

```
Student is: 1 Alice
Student is: 2 Bob
Student is: 2 Xay
Student is: 3 Charlie
```

`Comparator<Student>` is a functional interface — one abstract method, `compare(o1, o2)` — so it can be implemented on the spot with an anonymous class: a nameless class definition, written inline, that provides its own `compare` logic. Here it sorts purely by `id` ascending, ignoring `name` as a tiebreaker entirely (unlike `Student`'s own `compareTo`). This particular ordering happens to print the same as the natural-order result above, since `Bob` was already added before `Xay` in the original list and this comparator's ties are broken by whatever order they already were in.

> **Note:** `o1.getId() - o2.getId()` is a common way to write a comparator, but it has a well-known pitfall: subtracting two `int`s can silently overflow for values far enough apart (e.g. comparing something near `Integer.MIN_VALUE` against something near `Integer.MAX_VALUE`), which can produce a wrong sign and a broken ordering. `Student.id` values here are small, so it's not an issue in this demo, but the safer, idiomatic way to write an `int`-based comparator is `Integer.compare(o1.getId(), o2.getId())`, which can't overflow.

## `Comparator` as a lambda

```java
Comparator<Student> studentComparator2 = (s1,s2) -> s2.getId()-s1.getId();
Collections.sort(students, studentComparator2);
students.forEach(System.out::println);
```

Output:

```
Student is: 3 Charlie
Student is: 2 Bob
Student is: 2 Xay
Student is: 1 Alice
```

Because `Comparator` has exactly one abstract method, it's a *functional interface* — which means the entire anonymous-class boilerplate from `studentComparator1` (the `new Comparator<Student>() { ... }` wrapper, the `@Override`, the method signature) can be replaced by a lambda expression: `(s1, s2) -> s2.getId() - s1.getId()`. Same mechanism, far less ceremony. This one sorts by `id` **descending** (note `s2.getId() - s1.getId()`, reversed from the previous comparator), which is why `Charlie` (`id=3`) now comes first.

The three orderings side by side make the distinction concrete: `Student`'s own `compareTo` is fixed, built into the class, and used automatically by `Collections.sort(list)`. A `Comparator` — anonymous class or lambda, functionally identical — is supplied from the outside and can define a completely different ordering (here, by `id` descending) without changing `Student` at all.
