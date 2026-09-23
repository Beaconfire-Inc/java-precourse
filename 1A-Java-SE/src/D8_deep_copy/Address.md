# `Address` — a `Cloneable` helper class

`Address` is a small supporting class used by [Person.java](Person.md) to demonstrate deep copying of nested objects.

```java
package D8_deep_copy;

public class Address implements Cloneable {
    public String addressLine;
    public String zipCode;

    public Address(String addressLine, String zipCode) {
        this.addressLine = addressLine;
        this.zipCode = zipCode;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Address: " + addressLine + ", Zip:" + zipCode;
    }
}
```

Implementing `Cloneable` and overriding `clone()` (even just to call `super.clone()`) is what allows an `Address` object to be cloned by another class that holds a reference to it — see how `Person` uses this in its own `clone()` method.
