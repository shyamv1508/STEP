import java.util.Scanner;
public class Accounts{
    public static int acc = 1001;
    int accountNumber,age ;
    double balance;
    String name,accounttype,status;
    Scanner sc = new Scanner(System.in);

    public Accounts() {
        accountNumber = acc;
        this.acc+=1;
        balance = 500;
        System.out.print("Enter Name: ");
        this.name = sc.nextLine();
        System.err.println("Enter age: ");
        age = sc.nextInt();
        accounttype = "saving";
        status = "Active";
        System.out.println("Account has been creted");
        System.err.println("Account Number: " + accountNumber + "  | Name: " + name + "  | Age:" + age + "  | AccountType: " + accounttype + "  | Account status: " + status);

    }

    boolean deposit(double amount){
        if(amount <= 0){
            return false;
        }
        this.balance += amount;
        return true;
        
    }

    boolean withdraw(double amt){
        if ( amt > (this.balance -500)){
            return false;
        }
        this.balance -= amt;
        return true;
    }

    public static void main(String[] args) {
        boolean transactionstatus_1, transactionstatus_2;
        Accounts a1 = new Accounts();
        transactionstatus_1 = a1.deposit(2000);
        transactionstatus_2 = a1.withdraw(3000);
        System.out.println("\ndeposit: " + transactionstatus_1 + "\nwithdraw: " + transactionstatus_2);
    }
    
}
