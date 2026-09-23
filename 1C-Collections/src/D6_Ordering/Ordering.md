# Natural Ordering — `Comparable`

Sorting needs a way to answer "which of these two things comes first?" For many built-in types, Java already has an answer baked in: `String`, `Date`, and the numeric wrapper classes (`Integer`, `Double`, ...) all implement `Comparable`, which defines a single method, `compareTo(other)`, returning:

- a **negative** number if `this` comes before `other`
- **zero** if they're equal (for ordering purposes)
- a **positive** number if `this` comes after `other`

This built-in ordering is called the type's **natural ordering**.

## `String`

```java
String s1 = "abc";
String s2 = "def";
System.out.println(s1.compareTo(s2));
```

Output:

```
-3
```

`String.compareTo` compares character by character until it finds a difference, then returns the numeric difference between those two characters' Unicode values. `'a'` is `97` and `'d'` is `100`, so `s1.compareTo(s2)` returns `97 - 100 = -3` — negative, meaning `s1` ("abc") sorts before `s2` ("def"). (The exact magnitude isn't meaningful, just the sign — a *more* negative number doesn't mean "more before".)

## `Date`

```java
Date d1 = new Date(2024, 5,28);
Date d2 = new Date(2023,5,28);
System.out.println(d1.compareTo(d2));
```

Output:

```
1
```

`Date.compareTo` returns positive here, meaning `d1` is *after* `d2`. That checks out — but notice the constructor being used: `Date(int year, int month, int day)` is **deprecated**, precisely because of how confusing its parameters are. `year` isn't the actual calendar year, it's `(actual year - 1900)`, so `new Date(2024, 5, 28)` doesn't mean the year 2024 — it means year `2024 + 1900 = 3924`. `month` is also 0-based (`0` = January, ..., `11` = December), so `5` here means **June**, not May. Both `d1` and `d2` end up absurdly far in the future and one month later than the literal `5` suggests, but `d1`'s year (3924) is still after `d2`'s (3923), so the comparison result happens to be correct anyway. This is exactly why the constructor is deprecated: it's easy to write code that "works" while being wrong about what date it's actually representing. Modern code should use `java.time.LocalDate` instead, which has neither offset.

## `Integer`

```java
Integer i1 = 1;
Integer i2 = 0;
System.out.println(i1.compareTo(i2));
```

Output:

```
1
```

`Integer.compareTo` is the simplest case: positive because `1 > 0`.

Natural ordering is what powers `Collections.sort(list)` with no second argument, and what [`Student`](Student.md) implements next, to give a custom class the same kind of built-in "default" ordering.
