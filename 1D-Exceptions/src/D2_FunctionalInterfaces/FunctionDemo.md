# `Function<T, R>`

`Function<T, R>` is a functional interface that takes an argument of type `T` and returns a result of type `R` — a transformation.

```java
Function<Integer,Integer> divideByTwo = value -> value/2;
System.out.println(divideByTwo.apply(10));
```

Output:

```
5
```

`divideByTwo` matches `Function<Integer, Integer>`'s single abstract method, `apply(T t)`. `divideByTwo.apply(10)` runs the lambda with `10`, returning `5`.

## Composing functions: `andThen` vs. `compose`

```java
Function<Integer,Integer> multiplyByTwo = value -> value*2;
Function<Integer,Integer> minusByTwo = value -> value -2;

// (value - 2) * 2
Function<Integer,Integer> combinedFunction1 = minusByTwo.andThen(multiplyByTwo);
System.out.println(combinedFunction1.apply(2));

// (value * 2) - 2
Function<Integer,Integer> combinedFunction2 = minusByTwo.compose(multiplyByTwo);
System.out.println(combinedFunction2.apply(2));
```

Output:

```
0
2
```

Both `andThen` and `compose` combine two functions into one, but in opposite orders:

- `minusByTwo.andThen(multiplyByTwo)` means "run `minusByTwo` **first**, then feed its result into `multiplyByTwo`": `(2 - 2) * 2 = 0`.
- `minusByTwo.compose(multiplyByTwo)` means "run `multiplyByTwo` **first**, then feed its result into `minusByTwo`": `(2 * 2) - 2 = 2`.

The rule of thumb: `a.andThen(b)` reads left-to-right (`a`, then `b`); `a.compose(b)` reads right-to-left (`b`, then `a`) — `compose` runs the argument *before* the function it's called on.

## `Function.identity()`

```java
Function<Integer,Integer> sameValue = Function.identity();
System.out.println(sameValue.apply(2));
```

Output:

```
2
```

`Function.identity()` returns a function that just hands back whatever it's given, unchanged. It's mainly useful as a placeholder in APIs that require a `Function` — for example, `Collectors.toMap(keyFn, Function.identity())` when you want the map's values to be the original elements themselves, not a transformation of them.
