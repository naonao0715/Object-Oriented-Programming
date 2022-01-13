import java.util.Deque;
import java.util.Random;

public class Consumer implements Runnable {

    SynchronizedQueue q;

    public Consumer(SynchronizedQueue q) {
        this.q = q;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                q.queueLock.lock();
                if (!q.queue.isEmpty()) {
                    System.out.println("Consumer pop element " + q.queue.pop());
                }

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
