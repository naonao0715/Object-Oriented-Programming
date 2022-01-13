
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedQueue {

    public Deque<Integer> queue;
    Lock queueLock;

    public SynchronizedQueue() {
        this.queue = new LinkedList<>();
        queueLock = new ReentrantLock();
    }

    public static void main(String[] args) {

        SynchronizedQueue q = new SynchronizedQueue();
        Consumer c = new Consumer(q);
        Producer p = new Producer(q);

        Thread t1 = new Thread(c);
        Thread t2 = new Thread(p);
        t1.start();
        t2.start();

    }
}