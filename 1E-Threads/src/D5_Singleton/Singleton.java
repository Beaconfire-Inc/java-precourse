package D5_Singleton;

/**
 * A singleton demo.
 * Singleton can be eagerly or lazily initialized.
 * If it is lazily initialized, we need extra care to ensure its thread-safety.
 */
public class Singleton {
    // 5 components
    private volatile static Singleton instance;
//     private volatile static Singleton instance = new Singleton(); // eager-initialization

    private Singleton() {
        System.out.println("Constructor called by " + Thread.currentThread().getName());
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    // not thread-safe
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }

//    /**
//     * thread-safe reached by lazy initialization (Solution 1)
//      */
//    public synchronized static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }

    //    /**
//     * thread-safe reached by lazy initialization (Solution 2)
//      */
    public static Singleton getInstance() {
        // more efficient
        if(instance == null) {
            synchronized (Singleton.class) {
                // might be another thread has created an object already
                if (instance == null) { // double check
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println(Singleton.getInstance()));
        Thread t2 = new Thread(() -> System.out.println(Singleton.getInstance()));
        Thread t3 = new Thread(() -> System.out.println(Singleton.getInstance()));
        t1.start();
        t2.start();
        t3.start();
    }

}
