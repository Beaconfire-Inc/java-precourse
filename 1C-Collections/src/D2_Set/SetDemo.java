package D2_Set;

import java.util.*;

public class SetDemo {
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        set.add("New York");
        set.add("New Jersey");
        set.add("Washington DC");
        System.out.println(set);

        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("New York");
        linkedSet.add("New Jersey");
        linkedSet.add("Washington DC");
        System.out.println(linkedSet);



    }
}
