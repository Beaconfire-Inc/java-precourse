package D4_bitwise_operator;

public class BitOperationExample {
    public static void main(String[] arg) {
        int num1 = 10;
        // returns the string representation of the unsigned int value
        // represented by the argument in binary (base 2)
//        System.out.println("Binary is " + Integer.toBinaryString(num1));
//
//        int num2 = 9;
//        System.out.println("Binary is " + Integer.toBinaryString(num2));
//
//        int num3 = num1 & num2;
//        System.out.println(num3);
//        System.out.println(Integer.toBinaryString(num3));

        int num4 = 10;  // [0000 0000 0000 0000 0000 0000 0000 1010]
        int num5 = -10;  // [1111 1111 1111 1111 1111 1111 1111 0110]

        // arithmetic shift right
        System.out.println(num4 >> 1);  // 10 -> [1010] -> [101] -> 5
        System.out.println(num5 >> 1);  // -10 -> [1111 1111 1111 1111 1111 1111 1111 0110] -> [1111 1111 1111 1111 1111 1111 1111 1011] -> -5

        // logical shift right
        System.out.println(num4 >>> 1);  // 10 -> [1010] -> [101] -> 5
        System.out.println(num5 >>> 1);  // -10 -> [1111 1111 1111 1111 1111 1111 1111 0110] -> [0111 1111 1111 1111 1111 1111 1111 1011] -> 2147483643 (DOES NOT preserve the sign bit.)
    }
}
