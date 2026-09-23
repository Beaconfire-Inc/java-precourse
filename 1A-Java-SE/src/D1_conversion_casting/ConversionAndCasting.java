package D1_conversion_casting;

public class ConversionAndCasting {
    public static void main(String[] args) {
    // ============================================================
    // 1. Conversion
        // int -> double
        double a = 123;
        System.out.println("a=" + a);  // 123.0

    // ============================================================
    // 2. Casting
    // will lose precision
        // double -> int
        int b = (int)123.8;
        System.out.println("b=" + b);

        // ============================================================
        double c = 5.0/2.0; // 2.5
        int d = (int)c;
        // compile error, operator precedence
//        int e = (int)5.0/2.0;
        System.out.println(d);  // 2

        // ============================================================
        // int -> char
        char e = (char) 75;
        System.out.println("e=" + e);

    // ============================================================
    // 3. Other examples
        // int -> float: conversion, but loses precision sometimes
//        int largeInt = 1_111_111_111;
//        float f2 = largeInt;
//        System.out.println("f2="+ f2);
        // ============================================================
        // float/double -> int:
        float overflowed = 2147483648.0f;  // this number is bigger than Integer.MAX_VALUE = 2147483647
        int j = (int)overflowed;
        System.out.println(j);  // 2147483647, integer's largest value

        // ============================================================
        // overflow
//        int maxInt = Integer.MAX_VALUE;
//        int minInt = Integer.MIN_VALUE;
//        System.out.println(maxInt);
//        System.out.println(minInt);
//
//        System.out.println(maxInt + 1);  // -2147483648
//        System.out.println(Math.addExact(maxInt, 1));  // ArithmeticException: integer overflow

    }
}
