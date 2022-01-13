import java.util.Random;

public class Producer2 implements Runnable {

    SynchronizedQueue2 q;

    public Producer2(SynchronizedQueue2 q) {
        this.q = q;
    }

    public void run() {
        Integer nextInt;
        Random r = new Random();
        try {
            for (int i = 0; i < 100; i++) {

                System.out.println("Producing");
                q.enqueue(r.nextInt());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }

    }


}

