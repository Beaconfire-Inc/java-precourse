package D4_ThreadProblems;

public class DeadLockDemo2 {

    static Thread threadA;
    static Thread threadB;

    public static void main(String[] args) throws InterruptedException {

        threadA = new Thread(() -> {
            System.out.println("Thread A started");
            try {
                threadB.join(4000);  // A waits for B to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread A finished");
        });

        threadB = new Thread(() -> {
            System.out.println("Thread B started");
            try {
                threadA.join(10000);  // B waits for A to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread B finished");
        });

        // Start both threads
        threadA.start();
        threadB.start();
    }
}