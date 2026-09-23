# `Predicate<T>`

`Predicate<T>` is a functional interface that takes an argument and returns a `boolean` — a yes/no test.

```java
Predicate<Integer> isEven = value -> value % 2 == 0;
Predicate<Integer> isPositive = value -> value > 0;

System.out.println(isEven.test(2));
```

Output:

```
true
```

`isEven` matches `Predicate<Integer>`'s single abstract method, `test(T t)`. `isEven.test(2)` runs the lambda with `2`, returning `true`.

## Combining predicates: `and`, `or`, `negate`

```java
Predicate<Integer> positiveAndEven = isEven.and(isPositive);
Predicate<Integer> positiveOrEven = isEven.or(isPositive);
Predicate<Integer> notEven = isEven.negate();

System.out.println(positiveAndEven.test(2));
System.out.println(positiveOrEven.test(1));
System.out.println(notEven.test(2));
```

Output:

```
true
true
false
```

`Predicate` provides the same boolean logic you'd write by hand, but as reusable, composable objects instead of one-off `if` conditions:

- `isEven.and(isPositive)` is true only if **both** tests pass. `positiveAndEven.test(2)` → `2` is even *and* positive → `true`.
- `isEven.or(isPositive)` is true if **either** test passes. `positiveOrEven.test(1)` → `1` is odd but positive → `true` (only one needs to hold).
- `isEven.negate()` flips the result. `notEven.test(2)` → `2` *is* even, so its negation is `false`.

Because `and`/`or`/`negate` return new `Predicate`s rather than plain booleans, they can be chained and reused anywhere a `Predicate<Integer>` is expected — passed to `Stream.filter`, `Collection.removeIf`, and so on — without ever writing out the underlying `if` logic by hand at the call site.
