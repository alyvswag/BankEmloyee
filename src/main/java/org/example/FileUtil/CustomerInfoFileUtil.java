package org.example.FileUtil;

import org.example.Enum.FileAddress;
import org.example.Model.CustomerInfo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CustomerInfoFileUtil {
    public static void writeFileCustomerInfo(CustomerInfo[] customerI) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FileAddress.CUSTOMERS_LOGIN_INFORMATION.getAddress());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(customerI);//massivin texte yazilmasi
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
