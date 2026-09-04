public class Accounts{
    private int accountNumber,age ;
    private double balance;
    private String name,accountType,status;

    public Accounts(int accountNumber, String name, int age, double initialBalance, String accountType
                    ) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.age = age;
        this.name = name;
        this.accountType = accountType;
        status = "Active";
        System.out.println("Account has been creted");

    }

    boolean deposit(double amount){
        if(amount <= 0){
            return false;
        }
        this.balance += amount;
        System.out.println("Balance : " + this.balance);
        return true;

    }
    boolean withdraw(double amount){
        if (amount > this.balance){
            return false;
        }
        this.balance -= amount;
        System.out.println("Balance : " + this.balance);
        return true;
    }

    int getAccountNumber(){
        return this.accountNumber;
    }

    String getName(){
        return this.name;
    }

    int getAge(){
        return this.age;
    }

    double getBalance(){
        return this.balance;
    }

    String gtAccountType(){
        return this.accountType;
    }

    String getStatus(){
        return this.status;
    }

    void setName(String name){
        this.name = name;
        return;
    }

    void setAge(int age){
        this.age = age;
        return;
    }

    public static void main(String[] args) {
    }

}