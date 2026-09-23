package D1_Exception;

public class CustomCheckedExceptionDemo {

    public static void main(String[] args) {

        try{
            validateAge(14);
        } catch (CustomCheckedException e){
            System.out.println(e.getMessage());
            // e.printStackTrace();
        }

    }

    public static void validateAge(int age) throws CustomCheckedException{
        if(age < 18){
            throw new CustomCheckedException("The user must be at least 18 years old");
        }
    }

}
