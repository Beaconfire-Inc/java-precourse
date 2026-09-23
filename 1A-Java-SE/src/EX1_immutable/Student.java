package EX1_immutable;

import java.util.ArrayList;
import java.util.List;

// no setter exposed
// An Immutable class in Java is declared as final
public final class Student {

    // All variables in the class is final and private
    private final String name;
    private final List<String> courses; //memory address won't change

    // The constructor should use deep copy to initialize all the fields
    public Student(String name, List<String> courses) {
        this.name = name;
        this.courses = new ArrayList<>();
        for (String course : courses) {
            this.courses.add(course);
        }
    }

    /**
     * In getter method, deep copy should be performed to return value rather than reference
    */
    public List<String> getCourses() {
        List<String> temp = new ArrayList<>();
        for (String c : courses) {
            temp.add(c);
        }
        return temp;
    }

    @Override
    public String toString(){
        return "Student Name: " + name + ", " +
                "Courses" + courses;
    }

    public static void main(String[] args) {
        List<String> courses = new ArrayList(){
            {add("Spring Boot");}
            {add("SQL");}
        };

        Student s1 = new Student("Mark", courses);
        System.out.println(s1);

        // try to modify the courses object
        List<String> existCourses = s1.getCourses();
        existCourses.add("Hibernate");
        existCourses.add("Maven");
        System.out.println(s1);
        System.out.println(s1.getCourses());

        System.out.println(existCourses);
    }
}
