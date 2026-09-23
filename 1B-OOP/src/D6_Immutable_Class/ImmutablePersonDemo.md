# An Immutable Person

This demo builds a class whose objects genuinely can't be changed after they're created — not just "no setters," but protected all the way down, including the nested [Address](Address.md) object it holds.

## The class

```java
final class ImmutablePerson {
    private final String name;
    private final int age;
    private final Address address;

    private ImmutablePerson(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = new Address(
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getZipcode());
    }

    static ImmutablePerson createImmutablePerson(String name, int age, Address address) {
        return new ImmutablePerson(name, age, address);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return new Address(
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getZipcode());
    }
}
```

Several things work together to make this class immutable:

1. **The class itself is `final`.** It can't be subclassed, so nobody can create a subclass that adds mutable state or overrides a getter to break the guarantees below.
2. **Every field is `private final`.** Once set in the constructor, `name`, `age`, and `address` can never be reassigned.
3. **The constructor is `private`**, and the only way to build one is the static `createImmutablePerson(...)` factory method. This isn't strictly required for immutability, but it's a common pattern alongside it.
4. **The constructor makes its own copy of `address`**, instead of storing the `Address` object it was handed directly. If it stored the caller's original `Address` reference, the caller could still mutate that same object after construction (since `Address` itself is mutable) — and that mutation would be visible through `ImmutablePerson`, defeating the whole point.
5. **`getAddress()` also returns a fresh copy**, not the internal `address` field itself. If it returned the internal object directly, callers could grab it and call `setCity(...)` on it, mutating `ImmutablePerson`'s internal state from the outside despite every field being `private final`.

Steps 4 and 5 are both examples of **defensive copying** — the class never hands out (or accepts, without copying) a reference to a mutable object it needs to protect.

## Running the demo

```java
public static void main(String[] args) {
    Address address = new Address("123 Main St", "Apt 4B", "Springfield", "12345");
    ImmutablePerson person = ImmutablePerson.createImmutablePerson("John Doe", 30, address);

    System.out.println("Name: " + person.getName());
    System.out.println("Age: " + person.getAge());
    System.out.println("Address: " + person.getAddress());
    System.out.println("Address object hashcode: " + person.getAddress().hashCode());

    // If we don't return a copy of the Address object in getAddress(),
    // the following line would modify the internal state of ImmutablePerson
    person.getAddress().setCity("New City");
    System.out.println("\nAfter attempting to modify the address city:" + person.getAddress());
    System.out.println("Address object hashcode: " + person.getAddress().hashCode());
}
```

Output:

```
Name: John Doe
Age: 30
Address: 123 Main St, Apt 4B, Springfield, 12345
Address object hashcode: 1300109446

After attempting to modify the address city:123 Main St, Apt 4B, Springfield, 12345
Address object hashcode: 1020371697
```

(The exact hash code numbers will differ every time you run it.)

The key thing to notice: `person.getAddress().setCity("New City")` runs without error, but the city is still `"Springfield"` afterward. That's because `getAddress()` handed back a brand-new `Address` copy — `setCity` mutated *that throwaway copy*, not the one stored inside `person`. The two different hash codes printed confirm this: each call to `person.getAddress()` returns a different `Address` object in memory.

> **Note:** the source file also has an earlier, commented-out version of the constructor that duplicates the defensive-copy logic — it's an earlier draft left in place for reference and isn't part of the working code path.
