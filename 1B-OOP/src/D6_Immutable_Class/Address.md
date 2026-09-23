# Address (Helper Class)

`Address` is a plain, fully **mutable** class — every field has both a getter and a setter. It's used as a building block by both [ImmutablePersonDemo.md](ImmutablePersonDemo.md) and [MutablePersonDemo.md](MutablePersonDemo.md), which contrast what happens when an object *containing* an `Address` is itself immutable vs. mutable.

```java
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipcode;

    public Address(String addressLine1, String addressLine2, String city, String zipcode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return addressLine1 + ", " + addressLine2 + ", " + city + ", " + zipcode;
    }
}
```

There's nothing special about this class on its own — it's a standard getter/setter class with a `toString()` for readable output. What matters is how it's *used*: because `Address` objects can be changed after creation (via the setters), any class that hands out a direct reference to one of its `Address` fields is implicitly exposing that internal state to modification from the outside. That's the exact problem the immutable/mutable comparison in the other two files is built around.
