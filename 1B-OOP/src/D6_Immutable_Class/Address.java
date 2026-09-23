package D6_Immutable_Class;

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
