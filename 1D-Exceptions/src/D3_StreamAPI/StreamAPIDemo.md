# The Stream API

Streams let you describe a pipeline of operations over a collection — filter, transform, sort, collect — as a chain of method calls, instead of writing loops by hand.

```java
class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class StreamAPIDemo {
    public static void main(String[] args) {
        List<Person> l = new ArrayList<>();
        l.add(new Person("Bob", 24));
        l.add(new Person("Alice", 22));
        l.add(new Person("Carson", 17));

        System.out.println(
                l.stream().filter(p -> p.age >= 18)
                        .map(p -> p.name)
                        .map(String::toUpperCase)
                        .sorted()
                        .collect(Collectors.toList())
        );
    }
}
```

Output:

```
[ALICE, BOB]
```

This is exactly the same [`Consumer`](../D2_FunctionalInterfaces/ConsumerDemo.md)/[`Function`](../D2_FunctionalInterfaces/FunctionDemo.md)/[`Predicate`](../D2_FunctionalInterfaces/PredicateDemo.md) machinery from the previous topic, chained together into one pipeline:

1. **`.stream()`** turns the `List<Person>` into a stream you can chain operations on.
2. **`.filter(p -> p.age >= 18)`** keeps only people 18 or older — a `Predicate<Person>` — which drops `Carson` (age 17).
3. **`.map(p -> p.name)`** transforms each remaining `Person` into just their `name` (a `Function<Person, String>`) — from this point on, the stream holds `String`s, not `Person`s.
4. **`.map(String::toUpperCase)`** is a second transformation, converting each name to uppercase. `String::toUpperCase` is a *method reference* — shorthand for the lambda `name -> name.toUpperCase()`, since `toUpperCase` already takes no extra arguments beyond the `String` itself.
5. **`.sorted()`** sorts the stream using natural ordering — alphabetical, for `String`s.
6. **`.collect(Collectors.toList())`** ends the pipeline, gathering the results back into a `List`.

Streams are evaluated **lazily**: none of `filter`/`map`/`sorted` actually do any work by themselves — they just describe the pipeline. Nothing runs until a *terminal* operation like `collect` (or `forEach`, `count`, etc.) is called. For a pipeline of only `filter`/`map`-style *stateless* operations, each element can flow through the whole chain one at a time; but `sorted()` here is a **stateful** operation — it has to see every element before it can produce the first one, so it necessarily buffers the entire stream at that point before anything downstream of it can run. So the full pipeline actually runs in two phases: `filter`/`map` process elements one at a time as they're pulled, `sorted()` collects all of them and sorts, and only then does `collect` gather the (now sorted) results.

Also notice `Person` here is declared right in this file, with package-private visibility (no `public` on the class) — it's a different class from any other `Person` that might exist elsewhere in this repo, since each is scoped to its own package. Java doesn't care that the names match, as long as they're in different packages.
