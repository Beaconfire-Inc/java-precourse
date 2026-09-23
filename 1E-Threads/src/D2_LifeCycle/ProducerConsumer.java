package D2_LifeCycle;

/**
 * A demo of wait() and notify()
 * If there's no lock there would be a race condition (in D4_ThreadProblems)
 */
public class ProducerConsumer {
    private static final Object lock = new Object();
    private static int[] buffer; // Shared buffer
    private static int count; // Number of items currently in the buffer

    /**
     * Producer puts data into the buffer
     */
    static class Producer {
        void produce() {
            synchronized (lock) { // Acquire lock before accessing shared buffer
                while (isFull(buffer)) {
                    try {
                        // wait() causes this thread to release the lock and pause until notified
                        // wait() belongs to an object, not thread
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[count++] = 1;
                // also does notify()
                lock.notify();
                // notifyAll will Notify all waiting threads
                // lock.notifyAll();
            }
        }
    }

    /**
     * Consumer takes data from the buffer
      */
    static class Consumer {
        void consume() {
            synchronized (lock) {  // Acquire lock before accessing shared buffer
                while (isEmpty(buffer)) {  // If buffer is empty, wait until notified
                    try {
                        lock.wait();  // Wait until a Producer adds data
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Buffer has at least one item; consume it and decrement count
                buffer[--count] = 0;

                // Notify one waiting thread (likely a Producer) that space is available
                lock.notify();
            } // Release lock here
        }
    }

    static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new int[10];
        count = 0;

        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runnable produceTask = () -> {
            for (int i = 0; i < 50; i++) {
                producer.produce();
            }
            System.out.println("Done producing");
        };

        Runnable consumeTask = () -> {
            for (int i = 0; i < 45; i++) {
                consumer.consume();
            }
            System.out.println("Done consuming");
        };

        Thread producerThread = new Thread(produceTask);
        Thread consumerThread = new Thread(consumeTask);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println(count + " elements in the buffer.");
    }
}

