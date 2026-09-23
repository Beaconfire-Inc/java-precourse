# Bitwise Operators

## Binary representation and bitwise AND

`Integer.toBinaryString(...)` returns the binary (base 2) representation of an int as a string.

```java
int num1 = 10;
System.out.println("Binary is " + Integer.toBinaryString(num1));

int num2 = 9;
System.out.println("Binary is " + Integer.toBinaryString(num2));

int num3 = num1 & num2;
System.out.println(num3);
System.out.println(Integer.toBinaryString(num3));
```

`&` is the bitwise AND operator — it compares each bit position of both operands, and the result bit is `1` only when both input bits are `1`.

## Shift operators

```java
int num4 = 10;  // [0000 0000 0000 0000 0000 0000 0000 1010]
int num5 = -10;  // [1111 1111 1111 1111 1111 1111 1111 0110]
```

### Arithmetic shift right (`>>`)

Shifts bits to the right, filling the vacated leftmost bits with the **sign bit** (0 for positive numbers, 1 for negative numbers) — this preserves the sign of the number.

```java
System.out.println(num4 >> 1);
System.out.println(num5 >> 1);
```

Output:

```
5
-5
```

- `10 >> 1`: `[1010] → [101]` = `5`
- `-10 >> 1`: `[1111 ... 1111 0110] → [1111 ... 1111 1011]` = `-5`

### Logical shift right (`>>>`)

Shifts bits to the right, always filling the vacated leftmost bits with `0`, regardless of sign — this does **not** preserve the sign bit.

```java
System.out.println(num4 >>> 1);
System.out.println(num5 >>> 1);
```

Output:

```
5
2147483643
```

- `10 >>> 1`: `[1010] → [101]` = `5` (same as arithmetic shift, since `10` is positive)
- `-10 >>> 1`: `[1111 ... 1111 0110] → [0111 ... 1111 1011]` = `2147483643` — the sign bit is not preserved, so a negative number becomes a large positive one.
