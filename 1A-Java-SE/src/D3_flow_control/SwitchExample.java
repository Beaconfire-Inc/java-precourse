package D3_flow_control;

public class SwitchExample {
    public static void main(String[] args) {

        int i =2;

        switch (i) {
            case 0:
                System.out.println("Find 0");
                break; // If we don't use 'break' statement, will keep run case 1
            case 1:
                System.out.println("Find 1");
                break;
            default:
                System.out.println("default");
        }
    }
}
