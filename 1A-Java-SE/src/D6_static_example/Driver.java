package D6_static_example;

public class Driver {

    public static void main(String[] args) {
//        System.out.println(Sample.staticVariable);

        Sample sample = new Sample(1);
        Sample sample1 = new Sample(2);

        System.out.println(sample.instanceVariable);
        //sample.foo()
        System.out.println(sample1.instanceVariable);

        sample.instanceVariable = 3;
        System.out.println(sample.instanceVariable);
        System.out.println(sample1.instanceVariable);

        // not good practice
//        System.out.println(sample.b);
//        System.out.println(sample1.b);
//        //popa-yoke
//        System.out.println(Sample.b);

        Sample.staticVariable = 101;
////
        System.out.println(sample.staticVariable);
        System.out.println(sample1.staticVariable);
        System.out.println(Sample.staticVariable); // recommended

//        System.out.println(Sample.getStaticVariable());
//        System.out.println(sample1.getInstanceVariable());

    }
}