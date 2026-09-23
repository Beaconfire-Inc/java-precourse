package D2_LifeCycle;

/**
 * start() vs. run()
 */
class MyThread_BA extends Thread {
    public void run() {
        System.out.println("Current running in thread: " + Thread.currentThread().getName());
        System.out.println("run() method called");
    }
}
public class StartVSRunDemo {
    public static void main(String[] args) {
        MyThread_BA t = new MyThread_BA();
        t.start();
//        t.run();
        t.start();
//        System.out.println("Current running in thread: " + Thread.currentThread().getName());
    }
}
