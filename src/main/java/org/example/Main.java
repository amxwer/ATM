package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void printMenu(){
        System.out.println("Добро пожаловать в банк:" + "\n" +
                "1.Регистрация карты" + "\n" +
                "2.Вход в банк" + "\n" +
                "Выберите денежную операцию" + "\n"+
                "3.Проверить баланс" + "\n" +
                "4.Пополнить баланс" + "\n" +
                "5.Снять средства" + "\n" +
                "6.Достать карту");
    }
    public static void main(String[] args) {

        ATM atm = new ATM();
        boolean isAuthorize = false;
        List<String> listPin = new ArrayList<>();
        List<String> listNumber = new ArrayList<>();

        while(true){
            printMenu();
            CardInterfaceService cardService = new CardService();
            Scanner scannerKey = new Scanner(System.in);
            try{

            int key = scannerKey.nextInt();
            switch (key) {
                case 1 -> {
                    if(isAuthorize) {
                        System.out.println("Пожалуйста достаньте карту");
                    }else {

                        System.out.println("Зарегистрируйте карту в системе банка:\n" +
                                "Введите карту в формате xxxx xxxx xxxx xxxx");
                        Scanner scanner = new Scanner(System.in);
                        String newNumber = scanner.nextLine();
                        Pattern cardNumberPattern = Pattern.compile("([2-6]([0-9]{3}) ?)(([0-9]{4} ?){3})");
                        Matcher cardNumberMatcher = cardNumberPattern.matcher(newNumber);
                        if (cardNumberMatcher.matches()) {
                            System.out.println("Введите пароль");
                            Scanner scanner1 = new Scanner(System.in);
                            String pass = scanner1.nextLine();
                            if(pass.length() ==4) {
                                listPin.add(pass);
                                listNumber.add(newNumber);
                                Card card = new Card();
                                atm.insertCard(card);
                                card.setCardNumber(newNumber);
                                card.setPin(pass);
                                cardService.regist(card);
                                saveData(listPin);
                                saveNumber(listNumber);
                                System.out.println("Вы успешно зарегистрировались в системе");
                            }else{
                                System.out.println("Пароль должен содержать 4 символа");
                            }
                        } else {
                            System.out.println("Неверный формат данных");
                        }
                    }
                }

                case 2 -> {

                    if (isAuthorize) {
                        System.out.println("Сначала достаньте карту");
                    }else {

                        for (int i = 1; i <= 3; ++i){
                            System.out.println("Вход в систему банка");


                            System.out.println("Введите номер карты:");
                            Scanner login = new Scanner(System.in);
                            String log = login.nextLine();

                            System.out.println("Введите пароль:");
                            Scanner password = new Scanner(System.in);
                            String pass = password.nextLine();

                            boolean flag = cardService.isLogin(log, pass);
                            if (flag) {
                                isAuthorize = true;
                                System.out.println("Вы авторизованы");
                                break;
                            } else {
                                if(i<=2){
                                    System.out.println("Неверные данные ");
                                }else{
                                    System.out.println("Ваша карта заблокирована");
                                    System.exit(0);
                                }

                            }
                        }
                    }

                }

                case 3 -> {
                    if(isAuthorize) {
                        atm.showBalance();
                    }else{
                        System.out.println("Вы не выполнили вход в банк");
                    }

                }
                case 4 -> {
                    if(isAuthorize) {
                        System.out.println("Введите желаемую сумму");
                        Scanner scannerDeposit = new Scanner(System.in);
                        try {
                            int depositAmount = scannerDeposit.nextInt();
                            atm.deposit(depositAmount);
                        } catch (Exception e) {
                            System.out.println("Введите число");
                        }
                    }else{
                        System.out.println("Вы не выполнили вход в банк");
                    }
                }
                case 5 -> {
                    if(isAuthorize) {
                        System.out.println("Введите желаемую сумму");
                        Scanner scannerWithdraw = new Scanner(System.in);
                        int withdrawAmount = scannerWithdraw.nextInt();
                        atm.withdraw(withdrawAmount);
                    }else{
                        System.out.println("Вы не выполнили вход в банк");
                    }
                }
                case 6 -> {
                    atm.eject();
                    System.exit(0);
                    System.out.println("Спасибо за использование нашего банка");
                }
                default -> System.out.println("Нет такой операции");
            }
            }catch (Exception e){
                System.out.println("Введите число от 1 до 6");
            }
        }

    }

    static void saveNumber(List<String> listNumber) {
        try {
            File fileCardNumber = new File("fileCardNumber.txt");
            if(!fileCardNumber.exists()) {
                fileCardNumber.createNewFile();
            }
            FileWriter fileWriterCardNumber = new FileWriter(fileCardNumber);
            for(String cardScanner : listNumber){

                fileWriterCardNumber.write(cardScanner + System.getProperty("line.separator"));

            }
            fileWriterCardNumber.close();
        }catch (IOException e){
            System.out.println("Error" + e.getMessage());
        }
    }

    static void saveData(List<String> listPin) {
        try{

            File filePin = new File("filePinNumber.txt");
            if(!filePin.exists()){
                filePin.createNewFile();
            }
            FileWriter fileWriterPinNumber = new FileWriter(filePin);
            for(String pinScanner : listPin){
                fileWriterPinNumber.write( pinScanner + System.getProperty("line.separator"));
            }
            fileWriterPinNumber.close();
        }catch(IOException e){
            System.out.println("Error" + e.getMessage());

        }
    }
}