package D4_ThreadProblems;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSolution {

        static Thread threadA;
        static Thread threadB;

        static final ReentrantLock joinLock = new ReentrantLock();

        public static void main(String[] args) {
            threadA = new Thread(() -> {
                System.out.println("Thread A started");
                if (joinLock.tryLock()) {                 // try to be the one that joins
                    try {
                        threadB.join();                   // A waits for B
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        joinLock.unlock();
                    }
                } else {
                    System.out.println("Thread A: skip threadB.join() to avoid deadlock");
                }
                System.out.println("Thread A finished");
            });

            threadB = new Thread(() -> {
                System.out.println("Thread B started");
                if (joinLock.tryLock()) {                 // only one of A/B will enter
                    try {
                        threadA.join();                   // B waits for A
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        joinLock.unlock();
                    }
                } else {
                    System.out.println("Thread B: skip threadA.join() to avoid deadlock");
                }
                System.out.println("Thread B finished");
            });

            threadA.start();
            threadB.start();
        }

}
