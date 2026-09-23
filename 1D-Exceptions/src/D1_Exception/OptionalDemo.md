# Optional

`Optional<T>` is a container that either holds a value or doesn't — it exists to make the *possibility of absence* explicit in a method's return type, instead of relying on a bare `null` that callers might forget to check.

## An empty `Optional`

```java
Optional<Integer> emptyOptional = Optional.empty();
System.out.println(emptyOptional);
System.out.println(emptyOptional.getClass());
```

Output:

```
Optional.empty
class java.util.Optional
```

`Optional.empty()` creates an `Optional` holding nothing. Printing it shows `Optional.empty` rather than `null` — it's a real object, not the absence of one; that's the whole point.

> **Note on the source file:** everything below this point is commented out in `OptionalDemo.java` — only the two lines above actually run. The rest is shown here uncommented, since it's real, working code that covers the rest of the `Optional` API. The sample values used below (`"Presenter A"`, `"Presenter B"`, `"Presenter C"`) are generic placeholders chosen for this write-up; the commented-out code in the source file uses different literal strings for the same lines, but the behavior and output shown here accurately reflect what the code does.

## A non-empty `Optional`

```java
Optional<Integer> nonNullOptioanl = Optional.of(1);
System.out.println(nonNullOptioanl);
System.out.println(nonNullOptioanl.get());
```

Output:

```
Optional[1]
1
```

`Optional.of(value)` wraps a value that's guaranteed non-null — it throws `NullPointerException` immediately if you pass it `null`, which makes it a way of *asserting* "this can't be null" right at the point of creation, rather than discovering the problem later. `.get()` unwraps the value back out.

## `ofNullable`, `isPresent`, and `ifPresent`

```java
Optional<String> presenter = Optional.ofNullable("Presenter A");
System.out.println(presenter);
System.out.println(presenter.isPresent());
presenter.ifPresent(value -> System.out.println(value + " is presenting today!"));
```

Output:

```
Optional[Presenter A]
true
Presenter A is presenting today!
```

Unlike `Optional.of`, `Optional.ofNullable(value)` *accepts* `null` — it just wraps whatever it's given, empty or not, which makes it the right choice when the value genuinely might be missing (e.g. the result of a database lookup that found nothing). `isPresent()` checks whether there's a value without unwrapping it. `ifPresent(consumer)` is a shortcut for "if there's a value, do something with it" — the lambda only runs when the `Optional` isn't empty, so there's no need for a separate `if (isPresent())` check first.

```java
presenter = Optional.ofNullable(null);
System.out.println(presenter);
System.out.println(presenter.isPresent());
```

Output:

```
Optional.empty
false
```

This time `ofNullable` is given `null` directly, producing an empty `Optional` — same result as `Optional.empty()`, just arrived at differently.

## Providing a fallback: `orElse` vs. `orElseGet`

```java
String backupPresenter = presenter.orElse("Presenter B");
System.out.println(backupPresenter);
```

Output:

```
Presenter B
```

`orElse(fallback)` unwraps the value if present, or returns the given fallback otherwise. Since `presenter` is empty at this point, it falls back to `"Presenter B"`.

```java
presenter = Optional.ofNullable(null);
backupPresenter = presenter.orElseGet(() -> "Announcement: Presenter C will be substituting Presenter A today");
System.out.println(backupPresenter);
```

Output:

```
Announcement: Presenter C will be substituting Presenter A today
```

`orElseGet(supplier)` does the same job as `orElse`, but takes a `Supplier` (a no-argument lambda) instead of a plain value — the fallback is only *computed* if it's actually needed. This matters when producing the fallback is expensive (a database call, a network request): `orElse("Presenter B")` would build `"Presenter B"` every single time regardless of whether `presenter` was empty, while `orElseGet(() -> ...)` only runs the lambda when there's truly nothing to fall back to.

## Filtering

```java
presenter = Optional.ofNullable("Presenter A");
presenter = presenter.filter(value -> value.equals("Presenter B"));
System.out.println(presenter);
```

Output:

```
Optional.empty
```

`filter(predicate)` keeps the value only if it passes the predicate — otherwise it turns the `Optional` empty, even though it started with a value. Here, `presenter` holds `"Presenter A"`, but the filter only keeps it if it equals `"Presenter B"`, so the result is empty. `filter` doesn't throw or complain about the mismatch; it just quietly produces `Optional.empty`, which is the same "absence" the rest of the API already knows how to handle (with `orElse`, `ifPresent`, etc.).
