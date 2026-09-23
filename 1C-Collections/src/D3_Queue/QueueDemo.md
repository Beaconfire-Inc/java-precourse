# Queue

`Queue` models a "line" of elements — typically first-in-first-out (FIFO), though `PriorityQueue` (shown below) breaks that rule on purpose. This file shows two different `Queue` implementations, each with different ordering behavior.

## `LinkedList` as a `Queue` — FIFO

```java
Queue<Integer> queue = new LinkedList<>(Arrays.asList(1, 2, 3));
System.out.println(queue);
System.out.println(queue.peek());
System.out.println(queue.poll());
System.out.println(queue.peek());
queue.offer(1);
System.out.println(queue);
```

Output:

```
[1, 2, 3]
1
1
2
[2, 3, 1]
```

`LinkedList` implements `Queue`, and when used that way it behaves FIFO — first in, first out:

- `peek()` looks at the front of the queue (`1`) **without** removing it.
- `poll()` removes and returns the front element (`1`).
- `peek()` again now shows `2`, since `1` is gone.
- `offer(1)` adds `1` to the **back** of the queue, giving `[2, 3, 1]`.

## `PriorityQueue` — ordered by value, not insertion order

```java
queue = new PriorityQueue<>(); // min-heap

queue.offer(100);
queue.offer(6);
queue.offer(8);
queue.offer(2);
queue.offer(100);

System.out.println(queue);
```

Output:

```
[2, 6, 8, 100, 100]
```

`queue` is reassigned from a `LinkedList` to a `PriorityQueue` — legal because both implement `Queue`, so the variable's declared type doesn't need to change. A `PriorityQueue` doesn't preserve insertion order at all; by default it's a **min-heap**, always keeping the smallest element accessible at the front (via `peek()`/`poll()`), regardless of the order elements were `offer`ed in. Note that printing the whole queue (as above) shows its *internal heap array layout*, not a fully sorted list — the smallest element (`2`) is guaranteed to be first, but the rest aren't guaranteed to print in strict ascending order. If you want a fully sorted sequence, `poll()` repeatedly instead of printing the queue directly:

```java
while (!queue.isEmpty()) {
    System.out.print(queue.poll() + " ");
}
```

Output:

```
2 6 8 100 100
```

Polling one at a time always returns elements in ascending order, since each `poll()` re-heapifies to bring the next-smallest element to the front.
