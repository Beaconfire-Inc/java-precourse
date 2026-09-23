package D6_Immutable_Class;

public class MutablePersonDemo {
    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Apt 4B", "Springfield", "12345");
        MutablePerson person = new MutablePerson("John Doe", 30, address);
        System.out.println("--------Before modification:--------");
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Address: " + person.getAddress());

        // Modifying the mutable employee's details
        person.setName("Jane Doe");
        person.setAge(28);
        Address newAddress = new Address("456 Elm St", "Apt 5C", "Shelbyville", "67890");
        person.setAddress(newAddress);

        System.out.println("\n\n--------After modification:--------");
        System.out.println("Updated Name: " + person.getName());
        System.out.println("Updated Age: " + person.getAge());
        System.out.println("Updated Address: " + person.getAddress());
    }
}


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