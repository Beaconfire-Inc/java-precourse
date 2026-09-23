package D1_List;
import java.util.*;


public class ListDemo {
    public static void main(String[] args) {

        //List is a type of Collection and have extra methods
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));
        list.add(1,100);

        System.out.println(list);
        System.out.println(list.get(1));
        list.set(2,1000);
        System.out.println(list);
        list.remove(1);
        list.remove(Integer.valueOf(2));
        System.out.println(list);

        list = new LinkedList<>();



//        List<Integer> myList = new ArrayList<>(Arrays.asList(1,23,4,5));
//        myList.add(10);
//        System.out.println(myList);


    }



}
