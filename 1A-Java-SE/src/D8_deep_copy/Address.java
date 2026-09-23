package D8_deep_copy;

public class Address implements Cloneable{
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
