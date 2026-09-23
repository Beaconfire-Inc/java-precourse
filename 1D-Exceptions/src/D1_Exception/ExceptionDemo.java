package D1_Exception;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ExceptionDemo {


    public static void main (String[] args) throws IOException{
        withoutTryCatch();
//        withTryCatch();
    }

    static void withTryCatch() {
        System.out.println("IN Method 1, Calling Method 2");
        try {
            withoutTryCatch();
        } catch (ArithmeticException ae) { // catching unchecked exception, not recommended
            System.out.println("Arithmetic Exception Handled: " + ae);
        } catch (Exception e) { // catching all the other checked exception
            System.out.println("Exception Handled");
        }
        System.out.println("Successfully return");
    }

    /**
     * Risky code
     */
    static void withoutTryCatch() throws IOException {
        // This will cause an ArithmeticException (division by zero)
//        int result = 10 / 0;

        // This will cause an ArrayIndexOutOfBoundsException
//        int[] array = new int[10];
//        Arrays.fill(array,1);
//        System.out.println(array[11]);

        // This might cause an IOException (file doesn't exist, etc.)
        FileReader r = new FileReader("file2.txt");

    }





}
