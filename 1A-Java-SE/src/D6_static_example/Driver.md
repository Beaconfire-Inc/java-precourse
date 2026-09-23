# Static vs. Instance Members — Demo

This file drives [Sample.java](Sample.md) to show the practical difference between `static` and instance members.

## Static fields exist even before any object is created

```java
System.out.println(Sample.staticVariable);
```

`staticVariable` belongs to the class, not to any particular object, so it can be read via the class name alone — no `Sample` instance is needed yet.

## Each instance has its own copy of an instance field

```java
Sample sample = new Sample(1);
Sample sample1 = new Sample(2);

System.out.println(sample.instanceVariable);
System.out.println(sample1.instanceVariable);
```

Output:

```
1
2
```

`sample` and `sample1` were constructed with different values, and each keeps its own independent `instanceVariable`.

```java
sample.instanceVariable = 3;
System.out.println(sample.instanceVariable);
System.out.println(sample1.instanceVariable);
```

Output:

```
3
2
```

Changing `sample.instanceVariable` has no effect on `sample1.instanceVariable` — they're separate copies.

## A static field is shared by every instance

```java
Sample.staticVariable = 101;

System.out.println(sample.staticVariable);
System.out.println(sample1.staticVariable);
System.out.println(Sample.staticVariable); // recommended
```

Output:

```
101
101
101
```

Unlike `instanceVariable`, there is only one `staticVariable` — updating it through the class name affects what every object sees, because `sample.staticVariable` and `sample1.staticVariable` all refer to that same shared value. Accessing a static field through an instance reference (`sample.staticVariable`) is legal in Java, but it's misleading — it looks like each object has its own copy when it doesn't. Preferring `Sample.staticVariable` (accessing it through the class name) makes that shared nature clear, which is why it's marked `// recommended`.

## Calling the getters

```java
System.out.println(Sample.getStaticVariable());
System.out.println(sample1.getInstanceVariable());
```

`getStaticVariable()` is called on the class itself (it's a static method), while `getInstanceVariable()` is called on a specific object (it's an instance method) — matching the static/instance nature of the field each one returns.

> **Note:** the source file also has a commented-out section referencing `sample.b`, `sample1.b`, and `Sample.b`, as well as a call to `sample.foo()`. Neither `b` nor `foo()` actually exist on `Sample` — these are a preserved source variant from an earlier version of the example and are intentionally left out of this write-up.
