import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedQueue2 {

    public Deque<Integer> queue;
    Lock queueLock;
    Condition emptyQueue;

    public SynchronizedQueue2() {
        this.queue = new LinkedList<>();
        queueLock = new ReentrantLock();
        emptyQueue = queueLock.newCondition();
    }

    public void enqueue(Integer i) {
        try {
            queueLock.lock();
            queue.add(i);
            emptyQueue.signal();
        } finally {
            queueLock.unlock();
        }
    }

    public Integer dequeue() {
        Integer r = 0;
        queueLock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("waiting");
                emptyQueue.await();
            }
            r = queue.pop();
        } catch (InterruptedException e) {
            System.out.println("interrputed");
        } finally {
            queueLock.unlock();
        }
        return r;
    }

    public static void main(String[] args) {

        SynchronizedQueue2 q = new SynchronizedQueue2();
        Consumer2 c = new Consumer2(q);
        Producer2 p = new Producer2(q);

        Thread t1 = new Thread(c);
        Thread t2 = new Thread(p);
        t1.start();
        t2.start();


    }

}
