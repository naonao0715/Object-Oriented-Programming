import java.util.Deque;
import java.util.Random;

public class Producer implements Runnable {

    SynchronizedQueue q;

    public Producer(SynchronizedQueue q) {
        this.q = q;
    }

    public void run() {
        Random ran = new Random();
        try {
            for (int i = 0; i < 100; i++) {

                q.queueLock.lock();
                int x = ran.nextInt(1000);
                System.out.println("Producer: element is " + x);
                q.queue.add(x);

                q.queueLock.unlock();
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            System.out.println("Deposit interrupted.");
        } finally {
            q.queueLock.unlock();
        }

    }

}
