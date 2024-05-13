package org.example.FileUtil;

import org.example.Enum.FileAddress;
import org.example.Enum.Status;
import org.example.Model.Customer;
import org.example.Model.Db;

import java.io.*;

public class CustomerFileUtil {
    public static void writeCustomerFile(Customer[] customers) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FileAddress.CUSTOMERS_ADDRESS.getAddress());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(customers);//massivin texte yazilmasi
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCustomerFile() { // faylin oxunmasi prosesi
        try {
            File file = new File(FileAddress.CUSTOMERS_ADDRESS.getAddress());
            if (!file.exists() || file.length() == 0) {
                System.out.println("File da musteri melumati yoxdur!");
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(FileAddress.CUSTOMERS_ADDRESS.getAddress());
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Customer[] customer;
                try {
                    customer = (Customer[]) objectInputStream.readObject();
                    printArrayCustomer(customer); // filedan gelen arrayin capa verilmesi
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
    public static void printArrayCustomer(Customer[] customers){
        int index = 1;
        while (true) {
            if (customers[index] != null ) {
                if(customers[index].getIsActive()==Status.INACTIVE){
                    System.out.println("!DELETED CUSTOMER " + customers[index]);
                }else{
                    System.out.println(customers[index]);
                }
                index++;
            } else {
                break;
            }
        }
    }
    public static Customer[] returnFileArray(){
        try {
            File file = new File(FileAddress.CUSTOMERS_ADDRESS.getAddress());
            if (!file.exists() || file.length() == 0) {
                System.out.println("File da musteri melumati yoxdur!");
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(FileAddress.CUSTOMERS_ADDRESS.getAddress());
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                   return (Customer[]) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
