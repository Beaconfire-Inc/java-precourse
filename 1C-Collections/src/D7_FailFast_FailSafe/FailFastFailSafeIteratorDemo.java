package D7_FailFast_FailSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FailFastFailSafeIteratorDemo {
    public static void main(String[] args) {
        //1.ArrayList use fail-fast iterator
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer i : list) {
            if (i == 2) {
                list.add(4);   // Structural modification
            }
            System.out.println(i);
        }




        //2.ConcurrentHashMap uses fail-safe iterator
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        for (Integer key : map.keySet()) {
            if (key == 2) {
                map.put(4, "D");   // Safe modification
            }
            System.out.println(key);
        }

        System.out.println(map);
    }

}
