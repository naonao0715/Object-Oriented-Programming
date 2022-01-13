/* deadlock example */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount3 {

    Integer checkingBalance;
    Integer savingsBalance;
    Lock checkingAccountLock;
    Lock savingsAccountLock;

    public BankAccount3(Integer balance, Integer sbalance) {
        this.checkingBalance = balance;
        this.savingsBalance = sbalance;
        checkingAccountLock = new ReentrantLock();
        savingsAccountLock = new ReentrantLock();
    }

    public static void main(String[] args) {

        BankAccount3 b = new BankAccount3(0, 0);
        BankDepositExample3 dep = new BankDepositExample3(b);
        BankWithdrawalExample3 w = new BankWithdrawalExample3(b);

        Thread t1 = new Thread(dep);
        Thread t2 = new Thread(w);
        t1.start();
        t2.start();


    }
}



