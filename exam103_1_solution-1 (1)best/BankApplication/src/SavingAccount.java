public class SavingAccount implements Account{
    private int accountNumber;
    private String accountName;
    private double balance;
    private final String accountType = "SavingAccount";

    public SavingAccount(int accountNumber, String accountName, double balance){
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    @Override
    public void deposit(double amount){
        this.balance = this.balance + amount;
    }
    @Override
    public void withdraw(double amount){
        if (this.balance - amount >= 0){
            this.balance = this.balance - amount;
        } else {
            System.out.println("Not enough money!");
        }
    }
    @Override
    public double getBalance(){
        return this.balance;
    }
    @Override
    public String getAccountType(){
        return this.accountType;
    }
    @Override
    public int getAccountNumber(){
        return this.accountNumber;
    }
    @Override
    public String getAccountName(){
        return this.accountName;
    }
}