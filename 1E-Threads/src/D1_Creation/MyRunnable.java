package D1_Creation;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Current thread name: " + Thread.currentThread().getName());
        System.out.println("run() from implementing Thread class");
    }
}

class Demo {
    public static void main(String[] args) {

//        MyRunnable myRunnable = new MyRunnable(); // task
//        Thread thread = new Thread(myRunnable); // pass the task to a thread
//        thread.start();

        // Use lambda expression
        // anonymous implementation of Runnable
        Runnable lambdaRunnable = () -> {
            System.out.println("Current thread name: " + Thread.currentThread().getName());
            System.out.println("Runnable with Lambda Expression");
        };
        new Thread(lambdaRunnable).start();
    }
}
