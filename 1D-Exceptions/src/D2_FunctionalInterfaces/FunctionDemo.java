package D2_FunctionalInterfaces;

import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {

        Function<Integer,Integer> divideByTwo = value -> value/2;
        System.out.println(divideByTwo.apply(10));

        Function<Integer,Integer> multiplyByTwo = value -> value*2;
        Function<Integer,Integer> addByTwo = value -> value + 2;
        Function<Integer,Integer> minusByTwo = value -> value -2;

        // (value - 2) * 2
        Function<Integer,Integer> combinedFunction1 = minusByTwo.andThen(multiplyByTwo);
        System.out.println(combinedFunction1.apply(2));

        // (value * 2) - 2
        Function<Integer,Integer> combinedFunction2 = minusByTwo.compose(multiplyByTwo);
        System.out.println(combinedFunction2.apply(2));


        //Identity returns the same value as the input argument
        Function<Integer,Integer> sameValue = Function.identity();
        System.out.println(sameValue.apply(2));
    }
}
