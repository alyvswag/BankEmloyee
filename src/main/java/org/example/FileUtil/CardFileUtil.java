package org.example.FileUtil;

import org.example.Enum.FileAddress;
import org.example.Enum.Status;
import org.example.Model.Card;
import org.example.Model.Customer;
import org.example.Model.Db;

import java.io.*;
import java.util.Arrays;


public class CardFileUtil {
    public static void writeFileCard(Card[] cards) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FileAddress.CARDS_ADDRESS.getAddress());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(cards);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFileCard() {
        try {
            File file = new File(FileAddress.CARDS_ADDRESS.getAddress());
            if (!file.exists() || file.length() == 0) {
                System.out.println("File da kart melumati yoxdur!");
                return;
            }

            try (FileInputStream fileInputStream = new FileInputStream(FileAddress.CARDS_ADDRESS.getAddress());
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Card[] cards;
                try {
                    cards = (Card[]) objectInputStream.readObject();
                    printArrayCards(cards);// filedan gelen card arrayin capa verilmesi
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printArrayCards(Card[] cards) {
        int index = 1;
        while (true) {
            if (cards[index] != null) {
                if (cards[index].getStatus() == Status.INACTIVE) {
                    System.out.println("!BLOCKED KART " + cards[index]);
                } else {
                    System.out.println(cards[index]);
                }
                index++;
            } else {
                break;
            }
        }
    }
    public static Card[] returnFileCard(){
        try {
            File file = new File(FileAddress.CARDS_ADDRESS.getAddress());
            if (!file.exists() || file.length() == 0) {
                System.out.println("File da kard melumati yoxdur!");
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(FileAddress.CARDS_ADDRESS.getAddress());
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                return (Card[]) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
