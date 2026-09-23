package D3_DaemonThread;

/**
 * A daemon thread demo.
 *
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after sleeping");
        });

        daemonThread.setDaemon(true); // Is false by default
        daemonThread.start();
        System.out.println("From main");
    }
}
