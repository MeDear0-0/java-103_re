public class CurrentAccount implements Account {
    private int accountNumber;
    private String accountName;
    private double balance;
    private double minimum = 1000;
    private final String accountType = "CurrentAccount";

    public CurrentAccount(int accountNumber, String accountName, double balance){
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    public double getMinimum(){
        return this.minimum;
    }
    public void setMinimum(double minimum){
        this.minimum = minimum;
    }

    @Override
    public void deposit(double amount){
        this.balance = this.balance + amount;
    }
    @Override
    public void withdraw(double amount){
        if ((this.balance - amount) > this.minimum){
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