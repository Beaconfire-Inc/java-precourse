# Deep Copy of a Nested Object

`Person` holds a reference to an [Address](Address.md). A naive `clone()` (just calling `super.clone()`) would only **shallow-copy** `Person` — the cloned `Person` would still point to the *same* `Address` object as the original. To make it a true deep copy, `Person.clone()` also explicitly clones its `address` field.

```java
package D8_deep_copy;

public class Person implements Cloneable {
    public String name;
    public Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person personCpy = (Person) super.clone();
        personCpy.address = (Address) personCpy.address.clone();
        return personCpy;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("Office", "00000");
        Person person = new Person("Joey", address);

        Person person2 = (Person) person.clone();
        person2.address.addressLine = "Home";
        System.out.println(person.address);
        System.out.println(person2.address);
    }
}
```

Output:

```
Address: Office, Zip:00000
Address: Home, Zip:00000
```

After cloning, `person2.address` is modified — but because `clone()` also cloned the nested `Address` object (rather than just copying the reference), `person.address` is completely unaffected. If `clone()` had only done `super.clone()` without the extra `personCpy.address = (Address) personCpy.address.clone();` line, both `person` and `person2` would share the same `Address` object, and modifying one would affect the other.
