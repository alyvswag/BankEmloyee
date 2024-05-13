package org.example.Util;

import org.example.Enum.Status;
import org.example.FileUtil.CardFileUtil;
import org.example.Model.Card;
import org.example.Model.Customer;
import org.example.Model.Db;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class CardUtil {
    public static Card createCustomerCard(Customer[] customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Musterinin idsin daxil edin: ");
        int index = scanner.nextInt();
        if (customers[index] != null && customers[index].getIsActive() != Status.INACTIVE) {
            System.out.println("**Karti yaradin!**");
            System.out.print("Kart nomresi: ");
            Long number = scanner.nextLong();
            System.out.print("Kartin bitme tarixi: ");
            String expDate = scanner.next();
            System.out.print("Kartin cvv kodu: ");
            int cvv = scanner.nextInt();
            System.out.print("Kartda olan ilkin meblegi daxil edin: ");
            double amount = scanner.nextDouble();
            System.out.println("Kart ugurla yaradildi.");
            return new Card((long)Db.indexCard,Status.ACTIVE,customers[index],number,expDate,cvv,amount);
        }
        else{
            System.out.println("Sistemde bele bir musteri yoxdur.");
            System.out.println("Yeniden secim edin.");
            return createCustomerCard(customers);
        }

    }
    public static void statusCard(Card[] cards,Status status){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kartin id sin daxil edin: ");
        int indexCard = scanner.nextInt();
        if(cards[indexCard].getCustomer().getIsActive() ==Status.INACTIVE){
            System.out.println("Bu kartin musterisi sistemden silindiyine gore uzerinde emeliyyat etmek qeyri mumkundur.");
       return;
        }
        cards[indexCard].setStatus(status);
        System.out.println("Musterinin kartin statusu " + status +" olaraq deyisdirildi");
        CardFileUtil.writeFileCard(cards);
    }


}
