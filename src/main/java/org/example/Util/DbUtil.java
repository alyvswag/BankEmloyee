package org.example.Util;

import org.example.Enum.FileAddress;
import org.example.FileUtil.CardFileUtil;
import org.example.FileUtil.CustomerFileUtil;
import org.example.Model.Card;
import org.example.Model.Customer;
import org.example.Model.Db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.example.Model.Db.*;

public class DbUtil {
    public static void addCustomer(Customer customer) {
        Db.customers[Db.index] = customer;
    }

    public static void addCard(Card card) {
        Db.cards[Db.indexCard] = card;
    }

    public static int checkFileIndex(Object[] objects) {
        int index = 1;
        for (Object object : objects) {
            if (object != null) {
                index++;
            }
        }
        return index;
    }

    public static Object checkFileOBject(FileAddress address) {
        try {
            File file = new File(address.getAddress());
            // faylin icerisinin yoxlanmasi eger faylin icerisinde hecne yoxdursa yeni data elave etmek olsun,
            // eger  varsa o zaman faylin icerisinde olan datani massivimize menimsetsin
            if (!file.exists() || file.length() == 0) {
                return null;
            }
            try (FileInputStream fileInputStream = new FileInputStream(address.getAddress());
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                try {
                    return objectInputStream.readObject();// pbject // eger faylin icinde nese varsa menimset
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
