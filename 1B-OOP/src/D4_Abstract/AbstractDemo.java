package D4_Abstract;

public class AbstractDemo {
    public static void main(String[] args) {
        Bird bird = new Bird();
        bird.eat();
        bird.fly();
        bird.makeSound();

        Airplane plane = new Airplane();
        plane.fly();
        plane.startEngine();

        // Both Bird and Plane can fly
        Flyable[] flyers = { bird, plane };
        for (Flyable f : flyers) {
            f.fly();
        }

        Animal animal = new Bird();
    }
}

// a contract that requires the implementing classes implement the fly()
interface Flyable {
   void fly();
}

// abstract class used as an ultimate parent class
abstract class Animal {
    public Animal() {
        System.out.println("Constructor of abstract class Animal");
    }

    public void eat() {
        System.out.println("Animal is eating");
    }

    abstract void makeSound();
}

// bird is an animal and can fly
class Bird extends Animal implements Flyable {
    public Bird() {
        System.out.println("Constructor of Bird");
    }

    @Override
    public void fly() {
        System.out.println("The bird flaps its wings and soars into the sky.");
    }

    @Override
    public void makeSound() {
        System.out.println("Bird chirps");
    }
}

// airplane can fly
class Airplane implements Flyable {
    public Airplane() {
        System.out.println("Constructor of Airplane");
    }

    @Override
    public void fly() {
        System.out.println("The airplane starts its engines and lifts off the runway.");
    }

    public void startEngine() {
        System.out.println("Airplane engine started");
    }
}
