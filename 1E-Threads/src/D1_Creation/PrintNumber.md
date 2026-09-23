# A Minimal `Thread` Subclass

A stripped-down version of the "extend `Thread`" pattern from [MyThread](MyThread.md), with nothing else going on — useful as the simplest possible reference for the shape of the pattern.

```java
class Printer extends Thread {
    @Override
    public void run(){
        for(int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}
public class PrintNumber {
    public static void main(String[] args) {
        Printer p = new Printer();
        p.start();
    }
}
```

Output:

```
1
2
3
4
5
6
7
8
9
10
```

`Printer extends Thread` and overrides `run()` to count from 1 to 10. `p.start()` runs that loop on a separate thread from `main`. Since nothing else happens concurrently here, and the loop itself doesn't yield control anywhere, the output prints in order exactly as you'd expect from a single-threaded loop — the concurrency is real, but it isn't *visible* here the way it is in [TicketSeller](TicketSeller.md), where multiple threads actually contend with each other.
