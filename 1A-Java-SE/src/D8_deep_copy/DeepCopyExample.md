# Shallow "Copy" vs. `Cloneable` Deep Copy

This file contrasts two different things that both look like "copying" an object.

## Just assigning a reference is not copying at all

```java
class Dog {
    public String name;

    Dog(String name) {
        this.name = name;
    }
}
```

```java
Dog dog = new Dog("Joey");
Dog dogCpy = dog;  // shallow copy
dogCpy.name = "Jason";
System.out.println(dog.name);
```

Output:

```
Jason
```

`Dog dogCpy = dog;` doesn't create a new object at all — `dogCpy` is just another variable pointing at the *same* `Dog` object as `dog`. Changing `dogCpy.name` changes the only `Dog` object that exists, so `dog.name` reflects the change too.

## `clone()` via `Cloneable` — a shallow copy that happens to look deep here

```java
class DogDeepCopy implements Cloneable {
    public String name;

    DogDeepCopy(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

```java
DogDeepCopy dogDeepCopy = new DogDeepCopy("Joey");
DogDeepCopy dogDeepCopyCpy = (DogDeepCopy) dogDeepCopy.clone();
dogDeepCopyCpy.name = "Jason";
System.out.println(dogDeepCopy.name);
```

Output:

```
Joey
```

`clone()` creates a second, independent `DogDeepCopy` object — but `Object`'s default `clone()` (what `super.clone()` calls here) always performs a **shallow copy**: it copies each field's value as-is, including reference fields, rather than recursively cloning whatever they point to. This demo happens to only have one `String name` field, and `String` is immutable — reassigning `dogDeepCopyCpy.name` doesn't mutate a shared string in place, it just points `dogDeepCopyCpy` at a brand-new `String` object, leaving `dogDeepCopy`'s own reference untouched. That's why the two objects appear fully independent here, but it isn't evidence that `clone()` deep-copies anything: if `DogDeepCopy` held a *mutable* reference field instead, a plain `super.clone()` would leave both copies pointing at the very same nested object. See [Person.md](Person.md) for that exact scenario (a `Person` holding a mutable `Address`) and how `Person.clone()` fixes it by explicitly cloning the nested object too — that's what a genuine deep copy requires.
