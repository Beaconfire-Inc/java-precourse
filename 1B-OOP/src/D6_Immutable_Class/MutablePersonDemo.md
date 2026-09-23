# A Mutable Person (Baseline)

Before looking at [ImmutablePersonDemo.md](ImmutablePersonDemo.md), it helps to see the ordinary, fully mutable version of the same idea — a `Person`-like class with a name, age, and address, where every field can be freely changed after construction.

```java
class MutablePerson {
    private String name;
    private int age;
    private Address address;

    public MutablePerson(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
```

This is a plain getter/setter class, same shape as [Address](Address.md). `setName`, `setAge`, and `setAddress` all let you change the object's state after it's been created — there's no protection against it, and none is intended here.

## Running the demo

```java
public static void main(String[] args) {
    Address address = new Address("123 Main St", "Apt 4B", "Springfield", "12345");
    MutablePerson person = new MutablePerson("John Doe", 30, address);
    System.out.println("--------Before modification:--------");
    System.out.println("Name: " + person.getName());
    System.out.println("Age: " + person.getAge());
    System.out.println("Address: " + person.getAddress());

    // Modifying the mutable person's details
    person.setName("Jane Doe");
    person.setAge(28);
    Address newAddress = new Address("456 Elm St", "Apt 5C", "Shelbyville", "67890");
    person.setAddress(newAddress);

    System.out.println("\n\n--------After modification:--------");
    System.out.println("Updated Name: " + person.getName());
    System.out.println("Updated Age: " + person.getAge());
    System.out.println("Updated Address: " + person.getAddress());
}
```

Output:

```
--------Before modification:--------
Name: John Doe
Age: 30
Address: 123 Main St, Apt 4B, Springfield, 12345


--------After modification:--------
Updated Name: Jane Doe
Updated Age: 28
Updated Address: 456 Elm St, Apt 5C, Shelbyville, 67890
```

Every field changes exactly as you'd expect — `setName`, `setAge`, and `setAddress` all take effect immediately, because `MutablePerson` was never designed to prevent that. Keep this behavior in mind while reading [ImmutablePersonDemo.md](ImmutablePersonDemo.md), which builds a class that deliberately refuses to let this kind of external modification happen.
