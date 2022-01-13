public class Greeter implements Runnable {
    String message;
    public Greeter(String s) {
        this.message = s;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(message);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    public static void main(String[] args) {

        Greeter g1 = new Greeter("Hello!");
        Greeter g2 = new Greeter("goodbye!");
        Thread t1 = new Thread(g1);
        Thread t2 = new Thread(g2);
        t1.start();
        t2.start();
    }



}
