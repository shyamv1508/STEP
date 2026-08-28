public class Accounts{
    int accountNumber,age ;
    double balance;
    String name,accounttype,status;

    public Accounts() {
        accountNumber = 1001;
        balance = 500;
        age = 21;
        name = "Abcd";
        accounttype = "saving";
        status = "Active";
        System.out.println("Account has been creted");

    }

    boolean deposit(double amount){
        if(amount <= 0){
            return false;
        }
        this.balance += amount;
        return true;

    }

    public static void main(String[] args) {
        Accounts a1 = new Accounts();
    }

}