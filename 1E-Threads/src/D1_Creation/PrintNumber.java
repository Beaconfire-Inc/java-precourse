package D1_Creation;

class Printer extends Thread {
    @Override
    public void run(){
        for(int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}
public class PrintNumber {
    public static void main(String[] args) {
        Printer p = new Printer();
        p.start();
    }
}
