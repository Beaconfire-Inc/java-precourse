package D6_Ordering;


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class Student implements Comparable<Student>{
    private int id;
    private String name;

    public Student () {}
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String toString(){
        return "Student is: " + this.id + " " + this.name;
    }

    @Override
    public int compareTo(Student o) {
        int idCompare = Integer.compare(this.id,o.id); // this.id - o.id
        if(idCompare == 0){
            return this.name.compareTo(o.name);
        }
        return idCompare;
    }

}

