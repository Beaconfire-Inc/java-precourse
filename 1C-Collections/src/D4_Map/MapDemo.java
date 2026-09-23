package D4_Map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {


    public static void main(String[] args) {

        //Map
        Map<String,String> stores = new HashMap();
        stores.put("Store 1","Costco");
        stores.put("Store 2","Walmart");
        stores.put("Store 3","CVS");
        stores.put("Store 4","WholeFoods");

        System.out.println(stores.get("Store 1"));
        stores.remove("Store 1");
        System.out.println(stores);

        System.out.println(stores.containsKey("Store 1"));
        System.out.println(stores.containsValue("Costco"));

        System.out.println(stores.keySet());
        System.out.println(stores.values());
        System.out.println(stores.entrySet());

        System.out.println("--------------------------------");
        System.out.println("--------------------------------");

        //LinkedHashMap and TreeMap
        Map<Integer,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1,"one");
        linkedHashMap.put(3,"three");
        linkedHashMap.put(2,"two");

        System.out.println("LinkedHashMap is: " + linkedHashMap);


        Map<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(1,"one");
        treeMap.put(3,"three");
        treeMap.put(2,"two");

        System.out.println("TreeMap is: " + treeMap);




    }
}
