package org.example.FileUtil;

import org.example.Enum.FileAddress;
import org.example.Model.Customer;
import org.example.Model.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;



public class TransactionFileUtil {
    public static void readFileTransactionInfo() { // faylin oxunmasi prosesi
        try {
            File file = new File(FileAddress.TRANSACTION_ADDRESS.getAddress());
            if (!file.exists() || file.length() == 0) {
                System.out.println("File da kocurme haqqinda melumat yoxdur!");
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(FileAddress.TRANSACTION_ADDRESS.getAddress());
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
               Transaction[] transactionInfo;
                try {
                    transactionInfo = (Transaction[]) objectInputStream.readObject();
                    printTransactionInfo(transactionInfo); // filedan gelen arrayin capa verilmesi
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
    public static void printTransactionInfo(Transaction[] transactionInfo){
        for(Transaction info: transactionInfo){
            if(info!=null){
                System.out.println(info);
            }
        }
    }

}
