import java.util.Random;
import java.util.Scanner;


public class BankingApplication2 {


    public static void main(String[] args) {
        int option = 0, accountNumber;
        String accountName, accountType;
        double balance, amount;
        Bank bank = new Bank();
        Account account = null;
        Scanner scan = new Scanner(System.in);

        while (option != 6){
            System.out.println("Main Menu");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");
            System.out.println();

            System.out.print("Enter your choice: ");
            option = scan.nextInt();
            scan.nextLine();

            switch(option){
                case 1:
                    bank.listAccounts();
                    break;
                case 2:
                    System.out.print("Enter Account Name: ");
                    accountName = scan.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    balance = scan.nextDouble();
                    scan.nextLine();
                    accountNumber = generateAccountNumber();
                    System.out.print("Enter Account Type: (s -> Savings Account or c -> Current Account): ");
                    accountType = scan.nextLine();
                    if (accountType.toLowerCase().equals("s")){
                        account = new SavingAccount(accountNumber, accountName, balance);
                    }
                    else if (accountType.toLowerCase().equals("c")){
                        account = new CurrentAccount(accountNumber, accountName, balance);
                    }
                    bank.openAccount(account);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    account = bank.getAccount(accountNumber);
                    bank.closeAccount(account);
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    account = bank.getAccount(accountNumber);
                    bank.depositMoney(account, amount);
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    account = bank.getAccount(accountNumber);
                    bank.withdrawMoney(account, amount);
                    break;
            }
            System.out.println();
        }
    }

    public static int generateAccountNumber(){
        Random rand = new Random();
        int number = 100000 + rand.nextInt(900000);
        return number;
    }

}