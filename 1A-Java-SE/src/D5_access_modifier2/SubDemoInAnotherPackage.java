package D5_access_modifier2;

import D5_access_modifier.Demo;

public class SubDemoInAnotherPackage extends Demo {
    /**
     * Protected field
     */
    void foo(){
        Demo demo = new Demo();
        // System.out.println(demo.protectedField); // cannot do that
        System.out.println(protectedField); // can access protectedField of its parent class, but not other Demo instances
    }

    public static void main(String[] args) {
        SubDemoInAnotherPackage subDemoInAnotherPackage = new SubDemoInAnotherPackage();
        subDemoInAnotherPackage.foo();
    }
}
