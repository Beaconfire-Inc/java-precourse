package D4_ThreadProblems;

import java.util.*;

/**
 * A demo of race condition
 */
public class Counter{
    private volatile int c = 0; // the counter
    private final Random random = new Random(); // a utility class to generate randomized sleep time

    public void increment() throws InterruptedException {
        synchronized (this) {
        for(int i = 0; i < 10000; i++){
            //Thread.sleep(random.nextInt(5));
            c++;
        }
        }
    }

    public void decrement() throws InterruptedException {
        synchronized (this) {
        for(int i = 0; i < 10000; i++) {
            //Thread.sleep(random.nextInt(5));
            c--;
        }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(() ->{
            try {
                counter.increment();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        Thread t2 = new Thread(() ->{
            try {
                counter.decrement();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();

        //The joins are here so that the main will wait for the referenced threads to finish before it prints the result.
        //Otherwise, the main thread might print out c before t1 and t2 finishes execution.
        t1.join();
        t2.join();

        System.out.println(counter.c);

    }

    public static class driver{
        public static void main(String[] args){
            Object key1 = new Object();
            Object key2 = new Object();
            Thread t8 = new Thread( () -> {
                synchronized (key1) {
                    System.out.println("t8 has key 1.");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (key2) {
                        System.out.println("t8 has key 2");
                    }
                }
            });
            Thread t9 = new Thread( () -> {
                synchronized (key2) {
                    System.out.println("t9 has key 2.");
                    synchronized (key1) {
                        System.out.println("t9 has key 1");
                    }
                }
            });
            t8.start();
            t9.start();
        }
    }
}