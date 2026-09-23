package D3_flow_control;

public class BreakExample {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i == 5)
                break;
            System.out.println("i: " + i);
        }
        System.out.println("Loop complete.");
    }
}
