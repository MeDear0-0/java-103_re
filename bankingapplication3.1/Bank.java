/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapplication3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Student
 */
public class Bank {
    private String bankName;
    
    public Bank(){
    }
    
    public Bank(String bankName){
        this.bankName = bankName;
    }
    
    public String getBankName(){
        return this.bankName;
    }
    
    public void listAccounts(){
        Connection con = BankConnection.connect();
        try {
            Statement statement = con.createStatement();
            String sql = "select * from account";
            ResultSet results = statement.executeQuery(sql);
            
            while(results.next()){
                System.out.println(results.getString(1)+" "+results.getString(2)+" "
                        +results.getString(3)+" "+results.getString(4));
            }
            System.out.println();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openAccount(Account account){
        Connection con = BankConnection.connect();
        String sql = "insert into account(accID,accName,accBalance,accType) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,account.getAccountNumber());
            preparedStatement.setString(2,account.getAccountName());
            preparedStatement.setDouble(3,account.getBalance());
            preparedStatement.setString(4,account.getAccountType());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeAccount(Account account){
        Connection con = BankConnection.connect();
        String sql = "DELETE FROM account WHERE accID = ? AND accType = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,account.getAccountNumber());
            preparedStatement.setString(2, account.getAccountType());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void depositMoney(Account account, double amount){
        account.deposit(amount);
        Connection con = BankConnection.connect();
        String sql = "UPDATE account set accBalance = ? where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withdrawMoney(Account account, double amount){
        account.withdraw(amount);
        Connection con = BankConnection.connect();
        String sql = "UPDATE account set accBalance = ? where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Account getAccount(int accountNumber, String accountType){
        Connection con = BankConnection.connect();
        Account account=null;
        String sql = "SELECT * FROM account WHERE accID = ? AND accType = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setString(2, accountType);
            ResultSet result = preparedStatement.executeQuery();
            
            result.next();
            String accountName = result.getString(2);
            double balance = result.getDouble(3);
            
            if (accountType.equals("SavingsAccount")){
                account = new SavingsAccount(accountNumber, accountName, balance);
            }
            else if(accountType.equals("CurrentAccount")){
                account = new CurrentAccount(accountNumber, accountName, balance);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
}
