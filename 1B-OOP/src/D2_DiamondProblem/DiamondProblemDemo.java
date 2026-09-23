package D2_DiamondProblem;

/**
 * A Demonstration to the diamond problem.
 * Java avoids this problem by prohibiting multiple-inheritance for classes.
 * Solutions include using interfaces and aggregation.
 */
public class DiamondProblemDemo {
    public static void main(String[] args) {
        // SubClassD d = new SubClassD();
        // d.test(); // Works with interfaces, but it will cause ambiguity here if multiple inheritance is used

        IntermediateB b = new IntermediateB();
        IntermediateC c = new IntermediateC();
        AnotherSubClass aSub = new AnotherSubClass(b, c);
        aSub.testBoth();
    }
}

abstract class BaseA {
    public abstract void test();
}

class IntermediateB extends BaseA {
    public void test() {
        System.out.println("Hello from Intermediate B");
    }
}

class IntermediateC extends BaseA {
    public void test() {
        System.out.println("Hello from Intermediate C");
    }
}

// Java doesn't support multiple inheritance among classes

// class SubClassD extends IntermediateB, IntermediateC {
//    public void test() {
//        super.test();
//        System.out.println("Hello July!");
//    }
// }

// Solution 1: use interface to resolve ambiguity
interface DemoInterface1  {
     void test();
}

interface DemoInterface2 {
     void test();
}

class SubClassD implements DemoInterface1, DemoInterface2 {
    public void test() {
        System.out.println("Hello May!");
    }

    public void func() {
        System.out.println("Function New");
    }
}

// AGGREGATION SOLUTION:
class AnotherSubClass {
    IntermediateB b;
    IntermediateC c;

    AnotherSubClass(IntermediateB b, IntermediateC c) {
        this.b = b;
        this.c = c;
    }

    AnotherSubClass() {
        this.b = new IntermediateB();
        this.c = new IntermediateC();
    }   

    void testBoth() {
        System.out.println("Testing both:");
        b.test();
        c.test();
    }
}



