package D4_Serialization;

import java.io.*;

/**
 * 	During deserialization:
	•	Student constructor is not called (because it’s Serializable)
	•	Person constructor is called (because it’s not Serializable)
 */
public class DeserializationDemo {
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.ser"));
        Student s = (Student) in.readObject();
        in.close();

        System.out.println("Deserialized Student: " + s.getName());
    }
}
