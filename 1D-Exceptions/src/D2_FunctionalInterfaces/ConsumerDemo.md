# `Consumer<T>`

`Consumer<T>` is a functional interface that takes an argument and returns nothing — it *consumes* a value, typically to do something with a side effect (print it, save it, mutate something).

```java
Consumer<String> toUpperCase = value -> System.out.println(value.toUpperCase());
List<String> characters = new ArrayList<>(Arrays.asList("Tom","Jerry","Sponge Bob"));
characters.forEach(toUpperCase);
```

Output:

```
TOM
JERRY
SPONGE BOB
```

`toUpperCase` is a lambda matching `Consumer<String>`'s single abstract method, `accept(T t)` — it takes a `String` and prints its uppercase form, returning nothing. `List.forEach` takes a `Consumer` and calls it once per element, which is exactly what happens here.

## Chaining consumers with `andThen`

```java
Consumer<String> toLowerCase = value -> System.out.println(value.toLowerCase());
Consumer<String> combined = toLowerCase.andThen(toUpperCase);
characters.forEach(combined);
```

Output:

```
tom
TOM
jerry
JERRY
sponge bob
SPONGE BOB
```

`andThen` returns a new `Consumer` that runs the first one, then the second, on the same input — so `combined` prints the lowercase form (`toLowerCase`), then the uppercase form (`toUpperCase`), for each name. That's why each name shows up as two lines (lowercase, then uppercase) instead of one.
