package D6_static_example;

public class Sample {

    public static int staticVariable = 100;
    private static int psv;

    public int instanceVariable;

    public Sample(int instanceVariable) {
        this.instanceVariable = instanceVariable;
    }

    public int getInstanceVariable(){
        return instanceVariable;
    }

    public static int getStaticVariable(){
        int i = staticVariable;
        return staticVariable;
    }

}

