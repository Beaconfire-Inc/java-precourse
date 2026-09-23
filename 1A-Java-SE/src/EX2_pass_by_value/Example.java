package EX2_pass_by_value;

class Cat {
    String name;
    Cat(String name) {
        this.name = name;
    }
}

public class Example {

    /**
     * this method is for testing
     * @param value inputing an integer value
     */
    public static void test1(int value) {
        value = 1;
    }

    public static void foo(Cat c) {
        c = new Cat("Oliver");
    }

    public static void foo2(Cat c) {
        c.name = "Jessie";
    }

    public static void main(String[] args) {

        int a = 0;
        test1(a);
        System.out.println(a);  // 0 ? 1

        // aCat points to address of Cat("Max") in heap memory
        Cat aCat = new Cat("Keane");

        // copy the value of the address of object aCat to foo
        foo(aCat);
        // aCat variable is still pointing to the "Keane" cat when foo(...) returns
        System.out.println(aCat.name);  // Keane

        foo2(aCat);
        System.out.println(aCat.name);  // Jessie


    }
}
