package D2_FunctionalInterfaces;

import java.util.function.Supplier;

public class SupplierDemo {


    public static void main(String[] args) {
        //Takes in nothing and produce an result
        Supplier<String> helloSupplier = () -> "Hello World";
        System.out.println(helloSupplier.get());
    }
}
