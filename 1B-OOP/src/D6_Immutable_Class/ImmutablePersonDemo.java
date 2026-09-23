package D6_Immutable_Class;

public class ImmutablePersonDemo {

    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Apt 4B", "Springfield", "12345");
        ImmutablePerson person = ImmutablePerson.createImmutablePerson("John Doe", 30, address);

        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Address: " + person.getAddress());
        System.out.println("Address object hashcode: " + person.getAddress().hashCode());
        

        // If we dont return a copy of Address object in getAddress() method,
        // the following line would modify the internal state of ImmutablePerson
        person.getAddress().setCity("New City"); // This line would cause a compilation
        System.out.println("\nAfter attempting to modify the address city:" + person.getAddress());
        System.out.println("Address object hashcode: " + person.getAddress().hashCode());


    }
}

final class ImmutablePerson {
    private final String name;
    private final int age;
    private final Address address;

    // public ImmutablePerson(String name, int age, Address address) {
    // this.name = name;
    // this.age = age;
    // this.address = new Address(
    //         address.getAddressLine1(),
    //         address.getAddressLine2(),
    //         address.getCity(),
    //         address.getZipcode());
    // }

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
        // return address;
        return new Address(
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getZipcode());
    }

}

// This will cause a compilation error because ImmutablePerson is final
// and cannot be extended.
// class ImmutablePersonChild extends ImmutablePerson {

//     public ImmutablePersonChild(String name, int age, Address address) {
//         super(name, age, address);
//     }
// }