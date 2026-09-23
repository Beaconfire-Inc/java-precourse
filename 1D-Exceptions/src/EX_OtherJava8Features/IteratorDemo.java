package EX_OtherJava8Features;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Iterator<Integer> i = list.iterator();

        while (i.hasNext()) {
            Integer num = i.next();
            System.out.println(num);
        }
    }
}
