package org.example;

import org.example.Enum.Status;
import org.example.FileUtil.CardFileUtil;
import org.example.FileUtil.CustomerFileUtil;
import org.example.FileUtil.TransactionFileUtil;
import org.example.Menu.AdminPanel;
import org.example.Util.AdminCerationUtil;
import org.example.Util.CardUtil;
import org.example.Util.CustomerUtil;
import org.example.Model.Db;
import org.example.Util.DbUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Class.forName("org.example.Model.Db");
        AdminCerationUtil.adminLogin(AdminCerationUtil.readFileAdmin());
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(AdminPanel.menu);
            System.out.print("Secim edin: ");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    DbUtil.addCustomer(CustomerUtil.createCustomer());
                    CustomerFileUtil.writeCustomerFile(Db.customers);
                    break;
                case 2:
                    CustomerFileUtil.readCustomerFile();
                    break;
                case 3:
                    CustomerUtil.deleteCustomer();
                    break;
                case 4:
                    DbUtil.addCard(CardUtil.createCustomerCard(CustomerFileUtil.returnFileArray()));
                    CardFileUtil.writeFileCard(Db.cards);
                    break;
                case 5:
                    CardFileUtil.readFileCard();
                    break;
                case 6:
                    CardUtil.statusCard(CardFileUtil.returnFileCard(),Status.INACTIVE);
                    break;
                case 7:
                    CardUtil.statusCard(CardFileUtil.returnFileCard(),Status.ACTIVE);
                    break;
                case 8:
                    TransactionFileUtil.readFileTransactionInfo();
                    break;
                default:
                    System.out.println("Qardas duzgun secim edin!");
            }
        }
    }
}