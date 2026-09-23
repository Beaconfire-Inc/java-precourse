# Exercise: Shallow vs. Deep Copy, One Level Deeper

The source file poses a question but doesn't actually answer it in code — `TwoDDemo` has no `main()` method at all, just an empty class body. It sets up two small classes:

```java
package D8_deep_copy;

class A implements Cloneable {
    B b = new B();
    int d = 10;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class B {
    int c = 10;
}

public class TwoDDemo {
}
```

And asks, in comments:

> If object `a` has member `b`, and `b` has member `c`, and we copy `a`:
> - **Shallow copy:** if `a.b.c` is modified, does `aCopy.b.c` also change?
> - **Deep copy:** if `a.b.c` is modified, does `aCopy.b.c` stay the same?
>
> Is that right?

## Try it yourself

Note that `A.clone()` here only calls `super.clone()` — it does **not** also clone `b`, unlike how [Person.clone()](Person.md) explicitly clones its `address` field. Using what you learned from `Person`/`Address` and `Dog`/`DogDeepCopy`, predict the answer, then verify it by writing your own `main` method:

```java
A a = new A();
A aCopy = (A) a.clone();

aCopy.b.c = 99;
System.out.println(a.b.c);       // does this change?

aCopy.d = 99;
System.out.println(a.d);         // does this change?
```

Compare the behavior of `b` (an object field) against `d` (a primitive field) — that contrast is the point of this exercise.
