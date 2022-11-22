package org.example;

import java.util.ArrayList;

public class CardService implements CardInterfaceService {


    private static ArrayList<Card> listCards = new ArrayList<>();
    public boolean isLogin(String cardNumber,String password){
        boolean flag =false;

        for(Card card : listCards){
            if(card.getCardNumber().equals(cardNumber) && card.getPin().equals(password)){
                flag =true;
                break;
            }

        }
        return flag;
    }

    public void regist(Card card){
        listCards.add(card);
    }

}
