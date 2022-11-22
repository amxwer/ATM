package org.example;

public class Card {
    private String pin;
    private String cardNumber;
    private int balance;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }



    public int getBalance() {
            return balance +0;



    }

    public void deposit(int amount) {

            if(amount >=1000000){
                System.out.println("Слишком большая сумма");
            }else {
                balance += amount;
            }


    }

    public void withdraw(int amount) {

            if(balance >=amount){
                balance -= amount;

            }else{
                System.out.println("Ваш баланс меньше желаемой суммы вывода");
            }
    }

    }
