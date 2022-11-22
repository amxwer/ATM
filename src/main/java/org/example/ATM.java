package org.example;

public class ATM {
    private Card card1;


    public void insertCard(Card card) {

        if (card1 == null) {
            card1 = card;
            System.out.println("Вы вставили карту");
        } else {
            System.out.println("Карточка уже вставлена");
        }

    }
   public void eject(){
        card1 = null;
        System.out.println("Спасибо,не забудьте забрать карту");
    }
    public void showBalance(){
        if(card1!= null){
            System.out.println("Баланс:"+ card1.getBalance());
        }else {
            System.out.println("Вставьте карту");
        }
    }

    public void deposit(int amount){
        if(card1!=null) {
            card1.deposit(amount);
        }else{
            System.out.println("Отсуствует карта");
        }
    }
    void withdraw(int amount){
        if(card1 !=null ){
            card1.withdraw(amount);
        }else{
            System.out.println("Отсуствует карта");
        }
    }
}
