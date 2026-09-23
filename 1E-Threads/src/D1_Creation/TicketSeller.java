package D1_Creation;

/**
 * Advantage of using Runnable interface
 */
class TicketSeller implements Runnable {
    private int tickets = 10; // Shared resource

    @Override
    public void run() {
        while (true) {
            synchronized (this) { // Lock on the shared object
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName()
                            + " sold 1 ticket. Remaining: " + (--tickets));

                } else {
                    break; // Stop if no tickets left
                }
            }
            try {
                Thread.sleep(500); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        TicketSeller seller = new TicketSeller(); // One shared instance

        Thread t1 = new Thread(seller, "Window 1");
        Thread t2 = new Thread(seller, "Window 2");
        Thread t3 = new Thread(seller, "Window 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

