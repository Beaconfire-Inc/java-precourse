package D8_deep_copy;

public class Person implements Cloneable{
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
        System.out.println(person.address);  // Address: Office, Zip:00000
        System.out.println(person2.address);  // Address: Home, Zip:00000
    }
}
