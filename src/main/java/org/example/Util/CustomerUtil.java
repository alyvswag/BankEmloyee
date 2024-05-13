package org.example.Util;

import org.example.Enum.Status;
import org.example.FileUtil.CardFileUtil;
import org.example.FileUtil.CustomerFileUtil;
import org.example.FileUtil.CustomerInfoFileUtil;
import org.example.Model.Card;
import org.example.Model.Customer;
import org.example.Model.CustomerInfo;
import org.example.Model.Db;

import java.util.Scanner;

public class CustomerUtil {
    public static Customer createCustomer() {
//        CustomerFileUtil.checkFileCustomer();
        // metod niye burda cagirilir?
        // cunki musteri arraya elave edilmezden once ilk olaraq onun indexi mueyyen olunmalidi ve
        // faylin icerisined data varmi ya yox mueyyen edilmelidi
        Scanner scanner = new Scanner(System.in);
        System.out.print("Adi: ");
        String name = scanner.next();
        System.out.print("Soyadi: ");
        String surname = scanner.next();
        System.out.print("Fin daxil edin: ");
        String fin = scanner.next();
        System.out.print("Telefon nomresi: ");
        String phoneNumber = scanner.next();
        System.out.println("Musteri ugurla elave edildi.");
        return new Customer( (long) Db.index ,Status.ACTIVE,   name, surname, fin, phoneNumber);
    }
    public static void deleteCustomer() throws NullPointerException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Silmek istediyiniz musterinin id sin daxil edin: ");
        int index = scanner.nextInt();
        Customer[] customers = CustomerFileUtil.returnFileArray();
        String fin = customers[index].getFin();
        customers[index].setIsActive(Status.INACTIVE);
        for(CustomerInfo customerInfos : Db.customerInfos){
            if(customerInfos!=null && customerInfos.getFin().equals(fin)){
                customerInfos = null;
                CustomerInfoFileUtil.writeFileCustomerInfo(Db.customerInfos);
            }
        }
        CustomerFileUtil.writeCustomerFile(customers);
        System.out.println("Musteri ugurla silindi.");
        deletedCustomersCard(customers,index);
    }
    public static void deletedCustomersCard(Customer[] customer, int index){

        for(Card card : Db.cards){
            if(card!=null && card.getStatus()!=Status.INACTIVE && card.getCustomer().getId() == index){
                card.setStatus(Status.INACTIVE);
                card.getCustomer().setIsActive(Status.INACTIVE);
            }
        }
        System.out.println("Musterinin sistemde olan butun kartlari bloklasdirildi.");
        CardFileUtil.writeFileCard(Db.cards);
    }


}
