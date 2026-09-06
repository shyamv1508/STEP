package com.gdb.domain;
import com.gdb.exceptions.*;
public abstract class AbstractAccount {
    protected String accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status;
    protected Integer pin;


    public void deposit(double amount) throws InactiveAccountException, InvalidAmountException {
        if (!"ACTIVE".equalsIgnoreCase(this.status)) {
            throw new InactiveAccountException("Account is not active.");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }


    public void validatePin(Integer pin) throws InvalidPinException{
        if(pin == null){
            throw new InvalidPinException("Pin not Set");
        }
        if(this.pin != pin){
            throw new InvalidPinException("Invalid Pin: Enter the corect pin");
        }

    }

    public void changePin(Integer pin)throws InvalidPinException{
        if(pin < 0001 || pin >9999){
            throw new InvalidPinException("Enter a valid pin");
        }
        this.pin = pin;
    }


    public void displayAccountInfo(){
        String temp;
        if (this.pin == null){
            temp = "Not Set";

        }
        else {
            temp = "Set";
        }
        System.out.println("[" + this.accountType + "]  " + "Name"  + this.name + "  |  Age: " + this.age + " | Account Number " + this.accountNumber + "  |  Balance:" + this.balance + "  |  Status: " + this.status + "  |  Pin: " + temp);
    }

    public abstract void processDebit(double amount)throws AccountException;

    public void  withdraw(double amount, Integer pin) throws AccountException,InvalidPinException,InvalidAmountException,InactiveAccountException{
        validatePin(pin);

        if(this.status.equals("Inactive")){
            throw new InactiveAccountException("Account Inactive");
        }

        if(amount <= 0){
            throw new InvalidAmountException("Amount zero or Negative");
        }

        processDebit(amount);
    }



}
