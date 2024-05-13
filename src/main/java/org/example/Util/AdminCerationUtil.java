package org.example.Util;

import org.example.Enum.FileAddress;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AdminCerationUtil {

    public static String[] readFileAdmin() {
        try (InputStream inputStream = new FileInputStream(FileAddress.ADMIN_ADDRESS.getAddress());
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String[] information = new String[2];
            int index = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null && index < 2) {
                information[index] = line;
                index++;
            }
            return information;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void adminLogin(String[] information) {//user ve password gonder icerisine
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisteme Giris!");
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        if (information[0].equalsIgnoreCase(username) && information[1].equalsIgnoreCase(password)) {
            System.out.println("Giris ugurludur.");
        } else {
            System.out.println("Username ve yaxud parol yanlisdir.");
            adminLogin(information);
        }
    }

}
