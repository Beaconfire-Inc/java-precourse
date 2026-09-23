package D6_Ordering;

import java.util.Calendar;
import java.util.Date;

public class Ordering {

    public static void main(String[] args) {

        //Default Implementation

        //The difference in Unicode values between the first characters that differ ('a' and 'd')
        String s1 = "abc";
        String s2 = "def";
        System.out.println(s1.compareTo(s2));

        Date d1 = new Date(2024, 5,28);
        Date d2 = new Date(2023,5,28);
        System.out.println(d1.compareTo(d2));

        Integer i1 = 1;
        Integer i2 = 0;
        System.out.println(i1.compareTo(i2));

    }
}
