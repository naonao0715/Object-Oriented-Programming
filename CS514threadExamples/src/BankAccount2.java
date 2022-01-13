import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount2 {
    Integer balance;
    Lock accountLock;

    public BankAccount2(Int eger balance) {
        this.balance = balance;
        accountLock = new ReentrantLock();
    }

    public static void main(String[] args) {

        BankAccount2 b = new BankAccount2(0);
        BankDepositExample2 dep = new BankDepositExample2(b);
        BankWithdrawalExample2 w = new BankWithdrawalExample2(b);

        Thread t1 = new Thread(dep);
        Thread t2 = new Thread(w);
        t1.start();
        t2.start();



    }
}
