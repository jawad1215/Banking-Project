import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BankProgramme
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        int numberOfCustomers=0;
        Bank bank=new Bank();
        Customer[] c=bank.getcustomer();
       while(true)
       {
        System.out.println(" Please Enter Your Choice ");
        System.out.println(" 1: Add Customer ");
        System.out.println(" 2: Deposit Money ");
        System.out.println(" 3: WithDraw Money ");
        System.out.println(" 4: Check Balance ");
        System.out.println(" 5: Calculate Interest ");
        System.out.println(" 6: Exit ");
        int choice=Integer.parseInt(bufferedReader.readLine());
        switch(choice)
        {
            case 1:
            System.out.println(" Creating an account for new customer ");
            System.out.println(" Please Enter Initial amount In your account ");
            double bal=Double.parseDouble(bufferedReader.readLine());
            System.out.println(" Please Enter Your Accoun Number ");
            String acc=bufferedReader.readLine();
            Account account=new Account(bal,acc);
            System.out.println(" Enter Your Name ");
            String name=bufferedReader.readLine();
            Customer customer=new Customer(name,account);
            c[numberOfCustomers]=customer;
            numberOfCustomers++;
            //System.err.println(" Number of Customers " + numberOfCustomers);
            /*for(int i=0;i<numberOfCustomers;i++)
            {
                System.err.println(" Name "+c[i].getName());
            }*/
        
            break;
            case 2:
            System.out.println(" Enter Account Number ");
            acc=bufferedReader.readLine();
            if(numberOfCustomers==0)
            {
                System.out.println(" Account Number Not Found ");
            }
            else
            {
                boolean found=false;
                for(int i=0;i<numberOfCustomers;i++)
                {
                    Account temp=c[i].getAccount();
                    String accTemp=temp.getAccountNumber();
                    if(accTemp.equals(acc))
                    {
                        System.out.println(" Please Enter the Amount to Deposit: ");
                        double money=Double.parseDouble(bufferedReader.readLine());
                        temp.deposit(money);
                        found=true;
                    }
                }
                if(found==false)
                {
                    System.err.println(" Account Number Not Fount ");
                }
            }
            break;
            case 3:
             System.out.println(" Enter Account Number ");
            acc=bufferedReader.readLine();
            if(numberOfCustomers==0)
            {
                System.out.println(" Account Number Not Found ");
            }
            else
            {
                boolean found=false;
                for(int i=0;i<numberOfCustomers;i++)
                {
                    Account temp=c[i].getAccount();
                    String accTemp=temp.getAccountNumber();
                    if(accTemp.equals(acc))
                    {
                        System.out.println(" Please Enter the Amount to WithDraw: ");
                        double money=Double.parseDouble(bufferedReader.readLine());
                        temp.withdraw(money);
                        found=true;
                    }
                }
                if(found==false)
                {
                    System.err.println(" Account Number Not Fount ");
                }
            }
            break;
            case 4:
             System.out.println(" Enter Account Number ");
            acc=bufferedReader.readLine();
            if(numberOfCustomers==0)
            {
                System.out.println(" Account Number Not Found ");
            }
            else
            {
                boolean found=false;
                for(int i=0;i<numberOfCustomers;i++)
                {
                    Account temp=c[i].getAccount();
                    String accTemp=temp.getAccountNumber();
                    if(accTemp.equals(acc))
                    {
                        
                        System.out.println(" Balance is : " +temp.getBalance());
                        found=true;
                    }
                }
                if(found==false)
                {
                    System.err.println(" Account Number Not Fount ");
                }
            }
            
            break;
            case 5:
            System.out.println(" Enter Account Number ");
            acc=bufferedReader.readLine();
            if(numberOfCustomers==0)
            {
                System.out.println(" Account Number Not Found ");
            }
            else
            {
                boolean found=false;
                for(int i=0;i<numberOfCustomers;i++)
                {
                    Account temp=c[i].getAccount();
                    String accTemp=temp.getAccountNumber();
                    if(accTemp.equals(acc))
                    {
                        
                        bank.calculateInterest(c[i]);
                        found=true;
                    }
                }
                if(found==false)
                {
                    System.err.println(" Account Number Not Fount ");
                }
            }
            break;
            case 6:
            System.exit(0);
            
            break;
            default:
            break;
            
        }
       }

       
    }
}
class Bank
{
    private double interestRate=8.5;
    private double transactionFees=10;
    private Customer[] customers=new Customer[1000];
    public void calculateInterest(Customer customer)
    {
        Account a=customer.getAccount();
        double bal=a.getBalance();
        double interestAmount=bal*interestRate/100;
        double totalBalance=bal+interestAmount;
        System.out.println(" Interest amount is "+interestAmount+" Total Money after adding interest "+totalBalance);
    }
    public double getInterestRate()
    {
        return  interestRate;
    }
    public double getTransactionFees()
    {
        return transactionFees;
    }
    public Customer[] getcustomer()
    {
        return customers;
    }
}
class Account
{
    private double balance=100;
    private String accountNumber;
    private boolean firstTime=true;
    public Account(String acc)
    {
        accountNumber=acc;
        
    }
     public Account(double bal,String acc)
    {
        if(bal>=100)
        {
            balance=bal;
        }
        else
        {
            balance=100;
        }
        accountNumber=acc;
    }
    public void deposit(double howMuch)
    {
        if(howMuch>0)
        {
            balance=balance+howMuch;
            System.err.println(howMuch+" Balance was successfully deposited on your account.  " + " The new balance is " +balance);
        }
        else
        {
            System.err.println(" Please enter only positive amount ");
        }
    }
     public void withdraw(double howMuch)
    {
        if(howMuch>=0)
        {
            if(firstTime==true)
            {
                double tempBalance=balance;
                tempBalance=tempBalance-howMuch;
                if(tempBalance>=100)
                {
                    balance=balance-howMuch;
                    System.err.println(howMuch+" Balance was successfully WithDrawn from your account.  " + " The new balance is " +balance);
                }
                else
                {
                     System.err.println(" Insufficient balance to make transaction "+howMuch);
                }
                firstTime=false;
            }
            else
            {
                Bank bank=new Bank();
                double tempBalance=balance;
                tempBalance=tempBalance-howMuch-bank.getTransactionFees();
                if(tempBalance>=100)
                {
                    balance=balance-howMuch-bank.getTransactionFees();
                    System.err.println(howMuch+" Balance was successfully WithDrawn from your account.  " + " The new balance is " +balance);
                }
                else
                {
                     System.err.println(" Insufficient balance to make transaction "+howMuch);
                }
                
            }
        }
        else
        {
             System.err.println(" Please enter only positive amount ");
        }
    }
     public double getBalance()
    { 
        return balance;
    }
    public String getAccountNumber()
    {
        return accountNumber;
    }
}
class Customer
{
    private String name;
    private Account account;
    Customer(String n,Account a)
    {
        name=n;
        account=a;
        System.out.println(" Name :"+name+",Account Number :"+account.getAccountNumber()+",Balance: "+account.getBalance());
    }
    public void display()
    {
        System.out.println(" Name :"+name+ "Account Number :"+account.getAccountNumber()+", Balance" +account.getBalance());
    }
     public String getName()
    {
        return name;
    }
     public Account getAccount()
    {
        return account;
    }
    
}