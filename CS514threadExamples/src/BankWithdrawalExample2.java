public class BankWithdrawalExample2 implements Runnable {

    BankAccount2 account;
    public BankWithdrawalExample2(BankAccount2 b) {
        account = b;
    }

    public void run() {
        int nb;
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("Withdrawal thread: balance is " + account.balance);
                account.accountLock.lock();
                nb = account.balance - 100;
                account.balance = nb;
                System.out.println("Withdrawal thread: balance is " + account.balance);
                if (account.balance != nb) {
                    System.out.println("Race condition!");
                }
                account.accountLock.unlock();
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            System.out.println("Deposit interrupted.");
        } finally {
            account.accountLock.unlock();
        }

    }
}
