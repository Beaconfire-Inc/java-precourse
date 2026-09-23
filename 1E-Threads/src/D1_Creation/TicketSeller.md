# Sharing One `Runnable` Across Multiple Threads

This is the payoff for preferring `Runnable` over extending `Thread`: the *same* `Runnable` object can be handed to several different `Thread`s, letting them share state naturally — here, a pool of tickets that multiple "sales windows" compete over.

> **Note on file layout:** as with [MyRunnable](MyRunnable.md), the runnable `main` method lives in a second class in this file (`Main`), not in the file's namesake public-looking class (`TicketSeller` here is actually package-private, not `public`).

```java
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
```

Output (one possible run — see note below):

```
Window 1 sold 1 ticket. Remaining: 9
Window 3 sold 1 ticket. Remaining: 8
Window 2 sold 1 ticket. Remaining: 7
Window 3 sold 1 ticket. Remaining: 6
Window 1 sold 1 ticket. Remaining: 5
Window 2 sold 1 ticket. Remaining: 4
Window 3 sold 1 ticket. Remaining: 3
Window 1 sold 1 ticket. Remaining: 2
Window 2 sold 1 ticket. Remaining: 1
Window 3 sold 1 ticket. Remaining: 0
```

One `TicketSeller` instance (`seller`) is shared by all three `Thread`s — the `Thread(Runnable, String)` constructor's second argument just names the thread (`"Window 1"`, etc.), it doesn't create a separate task. Because they share the same `seller` object, they're also sharing its `tickets` field, which is exactly the scenario that needs protecting: without the `synchronized (this)` block, two windows could both read `tickets > 0` as true at the same time and both decrement it, potentially selling more tickets than exist (or corrupting the count). `synchronized (this)` locks on the shared `seller` object itself, so only one thread at a time can check-and-decrement `tickets` — guaranteeing exactly 10 tickets get sold, no more, no less, no matter how the threads interleave.

> **What's guaranteed vs. what isn't:** the *exact order* of which window sells which ticket is not deterministic — thread scheduling depends on the OS and can vary between runs, so your output's window sequence will likely look different from the one above. What **is** guaranteed, by the `synchronized` block, is that the tickets always count down cleanly from `9` to `0` with no duplicates or skips, and the program always sells exactly 10 tickets total.

This takes around 2 seconds to run to completion — there's a `Thread.sleep(500)` after each sale, and 10 tickets split across 3 concurrently-running windows works out to roughly 4 rounds of waiting.
