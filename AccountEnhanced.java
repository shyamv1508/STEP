public class AccountEnhanced {
    private int accountNumber, age, pin;
    private double balance,minimum ;
    private String name,accountType,status;

    public AccountEnhanced(int accountNumber, String name, int age, double initialBalance, String accountType)
    {
        if(age < 18){
            age = 18;
        }
        if(accountType != "Savings" || accountType != "Current"){
            accountType = "Savings";
        }


        if (accountType == "Savings" ) {
            minimum = 500;
        }
        else{
            minimum = 1000;
        }

        if(initialBalance < minimum){
            initialBalance = minimum;
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.age = age;
        this.name = name;
        this.accountType = accountType;
        status = "Active";
        System.out.println("Account has been creted");

    }

    boolean deposit(double amount){
        if(amount <= 0 || status == "Inactive"){
            return false;
        }
        this.balance += amount;
        return true;

    }
    boolean withdraw(double amount,int pin){
        if (amount > (this.balance - minimum) || status == "Inactive" || pin != this.pin){
            return false;
        }
        this.balance -= amount;
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

    String getAccountType(){
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

    boolean closeAccount(){
        if(this.status == "Inactive"){
            return false;
        }
        this.status = "Inactive";
        return true;
    }

    boolean reopenAccount(){
        if(this.status == "Active"){
            return false;
        }

        this.status = "Active";
        return true;
    }

    boolean setPin(int pin){
        if (pin <= 0 && pin <= 9999){
            return false;
        }
        this.pin = pin;
        return true;
    }

    boolean verifyPin(int pin){
        if(this.pin == pin){
            return true;
        }
        return false;
    }

    boolean hasPin(){
        if(this.pin > 0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
    }

}

