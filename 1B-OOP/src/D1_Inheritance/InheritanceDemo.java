package D1_Inheritance;

/**
 * Demonstration of different kinds of inheritance
 *
 * Base class: Animal
 * Intermediate class: Dog
 * Derived classes: Chihuahua, Bulldog
 */
class DogPerson {

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dog getBob() {
        return Bob;
    }

    public void setBob(Dog bob) {
        Bob = bob;
    }

    private Dog Bob;
}

class Animal {
    public static void main(String[] args) {
        DogPerson dogPerson = new DogPerson();
        dogPerson.getAge();
    }

    public int age;

    public Animal() {
        // Implicit constructor call to Object class
        System.out.println("An animal is created.");
    }

    public Animal(int age) {
        this.age = age;
    }

    public void eat() {
        System.out.println("This animal eats food.");
    }
}


class Dog extends Animal {
    public String name;

    public Dog(int age, String name) {
        super(age);
        this.name = name;
    }

    public Dog() {
        // Implicit call to super() here
        System.out.println("A dog is born.");
    }

    public void bark() {
        System.out.println("The dog barks.");
    }
}

class Corgi extends Dog {
    Double tall;

    public Corgi(int age, String name, Double tall) {
        super(age, name);
        this.tall = tall;

    }

    public Corgi() {
        this(1, "", 2.0);
        System.out.println("A Corgi is born.");
    }

    public void wagTail() {
        System.out.println("The Corgi wags its tail happily.");
    }
}

class Bulldog extends Dog {
    public Bulldog() {
        // Implicit call to super()
        System.out.println("A Bulldog is born.");
    }

    public void snore() {
        System.out.println("The Bulldog snores loudly.");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {

        // System.out.println("Creating a Corgi:");
        // Corgi corgi = new Corgi();
        // corgi.eat(); // Inherited from Animal
        // corgi.bark(); // Inherited from Dog
        // corgi.wagTail();

        System.out.println("-----------------");

        System.out.println("Creating a Bulldog:");
        Bulldog bulldog = new Bulldog();
        // bulldog .eat(); // Inherited from Animal
        // bulldog.bark(); // Inherited from Dog
        // bulldog.snore();

    }
}
