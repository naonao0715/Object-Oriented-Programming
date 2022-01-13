public class BankDepositExample3 implements Runnable {

    BankAccount3 account;
    public BankDepositExample3(BankAccount3 b) {
        account = b;
    }

    public void run() {
        int nb;
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("Transferring from checking to savings");
                account.checkingAccountLock.lock();
                account.checkingBalance= account.checkingBalance - 100;
                account.savingsAccountLock.lock();
                account.savingsBalance = account.savingsBalance + 100;
                System.out.println("Deposit thread: balance is " + account.savingsBalance);
                account.savingsAccountLock.unlock();
                account.checkingAccountLock.unlock();
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            System.out.println("Deposit interrupted.");
        } finally {
            account.checkingAccountLock.unlock();
            account.savingsAccountLock.unlock();
        }

    }
}
