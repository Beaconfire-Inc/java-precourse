package D5_access_modifier; // folder's name

public class Demo { //public class has same name with the file
    int defaultField;
    private int privateField;
    public int publicField;
    protected int protectedField;

    int foo() {
        return this.privateField;
    }

    public static void main(String[] args) {
        System.out.println(new Demo().privateField);
    }
}
