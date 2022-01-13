public class Consumer2 implements Runnable {

    SynchronizedQueue2 q;

    public Consumer2(SynchronizedQueue2 q) {
        this.q = q;
    }

    public void run() {
        Integer nextInt;
        try {
            for (int i = 0; i < 100; i++) {
                nextInt = q.dequeue();
                System.out.println("Consuming" + nextInt);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }

    }
}
