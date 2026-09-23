package D1_Exception;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
        Optional<Integer> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);
        System.out.println(emptyOptional.getClass());

//        Optional<Integer> nonNullOptioanl = Optional.of(1);
//        System.out.println(nonNullOptioanl);
//        System.out.println(nonNullOptioanl.get());
//
//
//        Optional<String> trainer = Optional.ofNullable("Leo");
//        System.out.println(trainer);
//        System.out.println(trainer.isPresent());
//        trainer.ifPresent(value -> System.out.println(value + " is giving lectures today!"));
//
//        trainer = Optional.ofNullable(null);
//        System.out.println(trainer);
//        System.out.println(trainer.isPresent());
//        //If the value is not null, it will return the value.
//        //If the value is null then it returns the value specified in orElse()
//        String substituteTrainer = trainer.orElse("Kevin");
//        System.out.println(substituteTrainer);
//
//        trainer = Optional.ofNullable(null);
//        substituteTrainer = trainer.orElseGet(() -> "Announcement: Simon will be substituting Leo today");
//        System.out.println(substituteTrainer);
//
//        trainer = Optional.ofNullable("Leo");
//        trainer = trainer.filter(value -> value.equals("Kevin"));
//        System.out.println(trainer);

    }


}
