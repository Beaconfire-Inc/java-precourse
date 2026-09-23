package D2_LifeCycle;

public class JoinWithArgumentDemo {

    public static void main(String[] args) {

        System.out.println("Main thread starts");

        // define a threadA: sleep for 5 seconds
        Thread threadA = new Thread(() -> {
            System.out.println("sleeping for 5 seconds");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleep over");
        });

        threadA.start();
        try {
//            threadA.join();
            threadA.join(4000); // main thread will wait for at most 4 seconds till threadA die.
        } catch (InterruptedException e) {
            // InterruptedException will be thrown if we call threadA.interrupt()
            e.printStackTrace();
        }
        System.out.println("Main thread ends");
    }
}