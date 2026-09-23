package D1_Inheritance;

/**
 * Keyword this demonstration.
 */
public class ThisDemo {
    private int number;

    // 1. `this()` refers to constructor of current class
    public ThisDemo() {
        // calls the constructor with single parameter
        // if you use this() to refer a constructor then it needs to be placed in the first line;
        this(123);

    }

    public ThisDemo(int number) {
        // refers to the current class instance variable
        this.number = number;
        System.out.println("this reference: " + this);
    }

    // 2. `this` refers to current instance (object)
    public ThisDemo returnMe() {
        return this;
    }

    // 3. `this.FIELD_NAME` refers to the field
    public void setNumber(int number) {
        this.number = number;
    }

    // 4. `this.` can be omitted if there's no conflict on variable names
    public int getNumber() {
        return number;
    }

    // Case 2
    public void displayNumber() {
        // passes the current object as an argument to a method
        timesTwo(this);
    }

    private void timesTwo(ThisDemo demo) {
        System.out.println("displayNumber: " + demo.number * 2);
    }

    public static void main(String[] args) {
//        ThisDemo demo1 = new ThisDemo(42);
//        System.out.println("demo1 getNumber: " + demo1.getNumber());
//        System.out.println("object reference: " + demo1);

        ThisDemo demo2 = new ThisDemo();
        System.out.println("demo2 getNumber: " + demo2.getNumber());
        demo2.displayNumber();
    }
}
