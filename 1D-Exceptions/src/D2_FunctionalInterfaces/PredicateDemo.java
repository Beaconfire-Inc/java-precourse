package D2_FunctionalInterfaces;

import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(String[] args) {
        Predicate<Integer> isEven = value -> value % 2 == 0;
        Predicate<Integer> isPositive = value -> value > 0;
        //If this is even return true
        System.out.println(isEven.test(2));

        Predicate<Integer> positiveAndEven = isEven.and(isPositive);
        Predicate<Integer> positiveOrEven = isEven.or(isPositive);
        //negation of isEven
        Predicate<Integer> notEven = isEven.negate();

        //If input is both even and positive
        System.out.println(positiveAndEven.test(2));
        //If input is positive or even
        System.out.println(positiveOrEven.test(1));
        //If input is not even
        System.out.println(notEven.test(2));

    }
}
