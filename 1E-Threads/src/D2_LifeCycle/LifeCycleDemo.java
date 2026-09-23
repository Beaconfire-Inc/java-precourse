package D2_LifeCycle;

public class LifeCycleDemo {
    public static void main(String[] args) throws InterruptedException {
        //Create a new Runnable and Thread
        Runnable sleepTask = () -> {
            try {
                System.out.println("Task is running in: " + Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("----Interrupted in: " + Thread.currentThread().getName() + "----");
                // throw new RuntimeException();  // doable
                // throw new Exception(); // Lambda expression are only allowed to throw unchecked exceptions
            }
        };
        Thread t = new Thread(sleepTask);

        // ----------join()-----------
//        System.out.println("Check point 1 - current thread is : " + Thread.currentThread().getName());
//        t.start();
//        t.interrupt(); // throws InterruptedException
//        t.join();
//        System.out.println("Check point 2 - current thread is : " + Thread.currentThread().getName());

//        // ---------sleep()----------
        System.out.println("Main thread sleeping for 2 seconds.");
        Thread.sleep(2000);  // Main thread sleeps
        System.out.println("Main thread wakes up.");

        // wait(), notify(), notifyAll() -> ProducerConsumer.java


    }
}
