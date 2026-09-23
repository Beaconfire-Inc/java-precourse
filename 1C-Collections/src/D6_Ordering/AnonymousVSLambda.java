package D6_Ordering;

import java.util.*;

public class AnonymousVSLambda {

    public static void main(String[] args) {

        //Anonymous Class
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Alice"));
        students.add(new Student(2, "Bob"));
        students.add(new Student(2, "Xay"));
        students.add(new Student(3, "Charlie"));

//        Collections.sort(students);
//        System.out.println(students);

        Comparator<Student> studentComparator1 = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId() - o2.getId();
            }
        };

        Collections.sort(students,studentComparator1);
        students.forEach(System.out::println);
//
//        System.out.println("-------------------------");

        //Lambda
        Comparator<Student> studentComparator2 = (s1,s2) -> s2.getId()-s1.getId();
        Collections.sort(students,studentComparator2);
        students.forEach(System.out::println);
    }
}
