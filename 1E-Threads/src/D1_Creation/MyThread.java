package D1_Creation;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("Current thread is " + Thread.currentThread().getName());
        System.out.println("run() from extending Thread class");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getName());
        Thread thread = new MyThread();
        thread.start();
        Thread.sleep(1000);
        thread.run();
        thread.run();
         (new MyThread()).start();
    }
}

