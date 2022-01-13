public class BankAccount {
    Integer balance;
    public BankAccount(Integer balance) {
        this.balance = balance;
    }

    public static void main(String[] args) {

     BankAccount b = new BankAccount(0);
     BankDepositExample dep = new BankDepositExample(b);
     BankWithdrawalExample w = new BankWithdrawalExample(b);

     Thread t1 = new Thr ead(dep);
     Thread t2 = new Thread(w);
     t1.start();
     t2.start();



    }

}
