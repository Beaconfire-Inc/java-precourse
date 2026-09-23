package D2_string_pool;

public class StringPoolExample {
    public static void main(String[] args) {
        String s1 = "dog";
        String s2 = "dog";
        String s3 = new String("dog");  // create new object in heap memory
        System.out.println(s1 == s2);   // true, both pointing to the "dog" in String pool
        System.out.println(s1 == s3);   // false, s3 points to "dog" in heap memory

        // intern() means moving "dog" into the String pool if it's not there
        s3 = s3.intern();
        System.out.println(s1 == s3);   // true, s3 points to "dog" in String pool
    }
}
