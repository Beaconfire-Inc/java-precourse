package D0_Collection_Overview;

import java.util.*;

public class CollectionDemo {


    public static void main(String[] args) {

        //Collection<Object> is not recommended because it is not type-safe
        Collection<Object> collection = new ArrayList<>();
        collection.add(1);
        collection.add("new");
        collection.add(22.2);
        collection.add(false);
        System.out.println(collection);

        
//        System.out.println("Collection contains 1: " + collection.contains(1));
//        System.out.println("Collection is empty: " + collection.isEmpty());
//        collection.remove(22.2);
//        System.out.println("The size of the collection is: " + collection.size());
//        System.out.println(collection);



        System.out.println("----------------------------------");

        //Collection vs Collections
        //Collection is an interface, Collections is a final utility class
        Collections.sort(Arrays.asList(3,2,1));
        Collections.reverse(Arrays.asList(1,2,3));


        System.out.println("----------------------------------");

        //Collections
        List<Integer> serachList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        System.out.println("The min value is: " + Collections.min(serachList));
        System.out.println("The max value is: " + Collections.max(serachList));
        System.out.println("The position of the element 3 is at: " + Collections.binarySearch(serachList,3));
        Collections.reverse(serachList);
        System.out.println("The position of the element 3 after reverse is at: " +Collections.binarySearch(serachList,3));

    }






}
