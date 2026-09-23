package D3_StreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
public class StreamAPIDemo {
    public static void main(String[] args) {
        List<Person> l = new ArrayList<>();
        l.add(new Person("Bob", 24));
        l.add(new Person("Alice", 22));
        l.add(new Person("Carson", 17));

        System.out.println(
                l.stream().filter(p -> p.age >= 18)
                        .map(p -> p.name)
                        .map(String::toUpperCase)
                        .sorted()
                        .collect(Collectors.toList())
        );
    }
}
