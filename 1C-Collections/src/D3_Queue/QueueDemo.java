package D3_Queue;

import java.util.*;

public class QueueDemo {

    public static void main(String[] args) {

        //Queue
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(1, 2, 3));
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        queue.offer(1);
        System.out.println(queue);

        queue = new PriorityQueue<>(); // min-heap
        


        queue.offer(100);
        queue.offer(6);
        queue.offer(8);
        queue.offer(2);
        queue.offer(100);




        System.out.println(queue);









    }





}
