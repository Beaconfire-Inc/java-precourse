package D2_FunctionalInterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {


    public static void main(String[] args) {
        // takes in a String and print it out in upper case form
        Consumer<String> toUpperCase = value -> System.out.println(value.toUpperCase());
        List<String> characters = new ArrayList<>(Arrays.asList("Tom","Jerry","Sponge Bob"));
        characters.forEach(toUpperCase);

        //use andThen to combine both consumer into one
        //first print out lower case form then print out upper case form
        Consumer<String> toLowerCase = value -> System.out.println(value.toLowerCase());
        Consumer<String> combined = toLowerCase.andThen(toUpperCase);
        characters.forEach(combined);
    }




}
