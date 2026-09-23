package D0_Playground;

public class Playground {
    public static void main(String[] args) {
        Person alice = new Person("Alice", 25, "Female");
        Person bob = new Person("Bob", 30, "Male");
        Person carl = new Person("Carl", 22, "Non-binary");
        Person diana = new Person("Diana", 40, "Female");
        Person anotherGuy = new Person();
        int val = 10;
    }
}

class Person {
    String name;
    int age;
    String gender;

    public Person() {
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void sayHello() {
        System.out.println("Hello!");
    }

}
