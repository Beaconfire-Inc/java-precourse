package D3_Polymorphism;

/**
 * Polymorphism demonstration.
 */
public class PolymorphismDemo {
    public static void main(String[] args) {
        // Animal cat = new Cat();
        // cat.sleep();
        // Calculator c = new Calculator();
        // c.add(1.0 ,1);

    }
}

class Animal {
    public Animal sleep() {
        System.out.println("Animal sleep");
        return new Animal();
    }

    public Animal sleep(int hours) {
        System.out.println("Animal sleep for " + hours + " hours"); return new Animal();
    }
}

class Cat extends Animal{
    @Override
    public Cat sleep() {
        System.out.println("Cat sleep");
        return new Cat();
    }

    public String someThing() {return "";}

}


class Calculator {
    int num1 = 1;
    void add(int num1, int num2){
        System.out.println(num1+num2);
    }


    Integer add(long num1, long num2){
        System.out.println(num1+num2);
        return 1;
    }
    void add(double num1, int num2){
        System.out.println(num1+num2);
    }
}
