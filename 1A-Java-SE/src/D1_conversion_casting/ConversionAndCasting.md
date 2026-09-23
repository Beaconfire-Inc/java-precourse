# Conversion & Casting

## 1. Conversion (Widening Conversion)

Conversion happens automatically (implicitly). A smaller type can be placed into a bigger type without any special syntax — for example, an `int` can be assigned directly to a `double`.

```java
double a = 123;
System.out.println("a=" + a);
```

Output:

```
a=123.0
```

## 2. Casting (Narrowing Conversion)

Casting is the opposite direction: a bigger type has to be placed into a smaller type. Casting is **not implicit** — you must explicitly write `(type)` in front of the value — and it **will lose precision**.

### double → int

```java
int b = (int)123.8;
System.out.println("b=" + b);
```

Output:

```
b=123
```

The decimal part is simply truncated (not rounded).

### Operator precedence pitfall

```java
double c = 5.0/2.0; // 2.5
int d = (int)c;
System.out.println(d);
```

Output:

```
2
```

It's tempting to combine the cast and the division into one line: `int e = (int)5.0/2.0;`. This does **not** compile.

- The cast `(int)` only applies to `5.0` (cast has higher precedence than `/`), so it becomes `5`.
- Then `5 / 2.0` evaluates to `2.5`, which is a `double`.
- A `double` cannot be implicitly assigned to an `int`, so the line fails to compile.

```java
int e = (int)5.0/2.0;  // compile error, operator precedence
```

### int → char

A `char` in Java is a 16-bit UTF-16 *code unit* (values `0`–`65535`) — not an arbitrary Unicode code point; characters outside the Basic Multilingual Plane need a pair of `char`s (a surrogate pair) to represent them. Casting an `int` to `char` is a narrowing conversion that keeps only the low 16 bits of the `int` and discards the rest, then treats that as a code unit. `75` fits comfortably in that range and is `'K'` in ASCII.

```java
char e = (char) 75;
System.out.println("e=" + e);
```

Output:

```
e=K
```

## 3. Other Examples

### int → float (precision loss)

Even though `int → float` is technically a *widening* conversion, it can still lose precision, because `float` cannot represent every large integer exactly.

```java
int largeInt = 1_111_111_111;
float f2 = largeInt;
System.out.println("f2=" + f2);
```

### float/double → int overflow

`Integer.MAX_VALUE` is `2147483647`. If you cast a floating-point value that's larger than that into an `int`, Java doesn't wrap around — it clamps to `Integer.MAX_VALUE`.

```java
float overflowed = 2147483648.0f;  // this number is bigger than Integer.MAX_VALUE = 2147483647
int j = (int)overflowed;
System.out.println(j);
```

Output:

```
2147483647
```

### Integer arithmetic overflow

This is a different kind of overflow — it happens during arithmetic on `int` values themselves, not during casting. Plain arithmetic (`+`) silently wraps around, while `Math.addExact(...)` detects the overflow and throws an exception instead.

```java
int maxInt = Integer.MAX_VALUE;
int minInt = Integer.MIN_VALUE;
System.out.println(maxInt);
System.out.println(minInt);

System.out.println(maxInt + 1);  // -2147483648, silently wraps around

System.out.println(Math.addExact(maxInt, 1));  // throws ArithmeticException: integer overflow
```

This last block is left commented out in the source file, because the `Math.addExact(...)` call throws an uncaught exception and stops the program. To run it safely, wrap the throwing call in a `try/catch`:

```java
try {
    System.out.println(Math.addExact(maxInt, 1));
} catch (ArithmeticException ex) {
    System.out.println("ArithmeticException: " + ex.getMessage());
}
```
