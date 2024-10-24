/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author 
 */
public class CustomerOrders {

    private Map<LocalDateTime, Shopping> listOrder;
    private List<Map.Entry<LocalDateTime, Shopping>> sortedEntries;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'\nDate: 'MM-dd-yyyy'\nTime: 'HH:mm:ss.SSS");

    public CustomerOrders() {
        listOrder = new Hashtable<>();
    }

    public CustomerOrders(Map<LocalDateTime, Shopping> listOrder, List<Map.Entry<LocalDateTime, Shopping>> sortedEntries) {
        this.listOrder = listOrder;
        this.sortedEntries = sortedEntries;
    }

    public Map<LocalDateTime, Shopping> getListOrder() {
        return listOrder;
    }

    public void setListOrder(Map<LocalDateTime, Shopping> listOrder) {
        this.listOrder = listOrder;
    }

    public List<Map.Entry<LocalDateTime, Shopping>> getSortedEntries() {
        return sortedEntries;
    }

    public void setSortedEntries(List<Map.Entry<LocalDateTime, Shopping>> sortedEntries) {
        this.sortedEntries = sortedEntries;
    }

    public void addBill(LocalDateTime time, Shopping customer) {
        listOrder.put(time, customer);
    }

    public void viewOrders() {
        if (listOrder.isEmpty()) {
            System.out.println("No one order yet");
        } else {
            this.sortedEntries = sortOrdersByTime();
            displaySortedOrders();
        }
    }

    private List<Map.Entry<LocalDateTime, Shopping>> sortOrdersByTime() {
        sortedEntries = new ArrayList<>(listOrder.entrySet());
        sortedEntries.sort(Collections.reverseOrder(Map.Entry.comparingByKey()));
        return sortedEntries;
    }

    private void displaySortedOrders() {
        for (Map.Entry<LocalDateTime, Shopping> entry : sortedEntries) {
            LocalDateTime orderTime = entry.getKey();
            Shopping shopping = entry.getValue();

            System.out.println("ORDER TIME:" + orderTime.format(formatter));
            System.out.println("CUSTOMER: " + shopping.getName());
            shopping.viewCart();
            System.out.println();
        }
    }
}
