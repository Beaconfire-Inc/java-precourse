package EX3_WrapperClassRequirement;

import java.util.ArrayList;
import java.util.List;

public class WrapperClass {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        int[] arr = new int[20];
        list.add(1); // auto-boxing: primitive -> object
        System.out.println(list.get(0).getClass().getName());  // class java.lang.Integer


        //built-in
        System.out.println(Integer.valueOf("12"));  // 12
        System.out.println(Integer.MAX_VALUE);  // 2147483647
    }
}
