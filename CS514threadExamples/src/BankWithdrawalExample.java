public class BankWithdrawalExample implements Runnable {
    BankAccount account;

    public BankWithdrawalExample(BankAccount b) {
        this.account = b;
    }

    public void run() {
        int nb;
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("Withdrawal thread: balance is " + account.balance);
                nb = account.balance - 100;
                account.balance = nb;
                System.out.println("Withdrawal thread: balance is " + account.balance);
                if (account.balance != nb) {
                    System.out.println("Race condition!");
                }
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            System.out.println("Deposit interrupted.");
        }

    }
}
