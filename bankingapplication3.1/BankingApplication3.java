/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankingapplication3;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class BankingApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bank bank = new Bank("XYZ");
        int option = 0, accountNumber;
        String accountName, accountType;
        double balance, amount;
        Account account = null;
        
        while(option != 6){
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
            System.out.println();
            
            switch(option){
                case 1:
                    bank.listAccounts();
                    break;
                case 2:
                    accountNumber = generateAccountNumber();
                    System.out.print("Enter Account Name: ");
                    accountName = scan.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    balance = scan.nextDouble();
                    scan.nextLine();
                    System.out.print("Enter Account Type (s -> Savings Account or c -> Current Account): ");
                    accountType = scan.nextLine();
                    if (accountType.toLowerCase().equals("s")){
                        account = new SavingsAccount(accountNumber, accountName, balance);
                    }
                    else if(accountType.toLowerCase().equals("c")){
                        account = new CurrentAccount(accountNumber, accountName, balance);
                    }
                    bank.openAccount(account);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Account Type (s -> Savings Account or c -> Current Account): ");
                    accountType = scan.nextLine();
                    if (accountType.toLowerCase().equals("s")){
                        account = bank.getAccount(accountNumber, "SavingsAccount");
                    }
                    else if(accountType.toLowerCase().equals("c")){
                        account = bank.getAccount(accountNumber, "CurrentAccount");
                    }
                    bank.closeAccount(account);
                    System.out.println("Account is Deleted");
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Account Type (s -> Savings Account or c -> Current Account): ");
                    accountType = scan.nextLine();
                    if (accountType.toLowerCase().equals("s"))
                        accountType = "SavingsAccount";
                    else if(accountType.toLowerCase().equals("c"))
                        accountType = "CurrentAccount";
                    account = bank.getAccount(accountNumber, accountType);
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    bank.depositMoney(account, amount);
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Account Type (s -> Savings Account or c -> Current Account): ");
                    accountType = scan.nextLine();
                    if (accountType.toLowerCase().equals("s"))
                        accountType = "SavingsAccount";
                    else if(accountType.toLowerCase().equals("c"))
                        accountType = "CurrentAccount";
                    account = bank.getAccount(accountNumber, accountType);
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    bank.withdrawMoney(account, amount);
                    break;
            }
            System.out.println();
        }
    }
    
    public static int generateAccountNumber(){
        Random random = new Random();
        int accNumber = 100000 + random.nextInt(900000);
        return accNumber;
    }
    
}
