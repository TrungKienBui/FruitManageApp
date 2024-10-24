/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author
 */
public class Fruit {

    private String id;
    private String name;
    private double price;
    private int quantity;
    private String origin;
    private boolean status = true; //--> đổi tên status thành active 
    private static DecimalFormat numberFormatPrice = new DecimalFormat("#,##0.00");
    private static DecimalFormat numberFormatQuantity = new DecimalFormat("#,###");

    public Fruit(String id, String name, String origin, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isStatus() { //--> đổi tên status thành active 
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatus() {
        if (status) {
            return "Active";
        }
        return "Disable";
    }

    public String formatPrice() {
        return numberFormatPrice.format(price);
    }

    @Override
    public String toString() {
        return String.format("| %-4s | %-19s| %-19s|%9s |%10s |", id, name, origin, numberFormatQuantity.format(quantity), "$" + numberFormatPrice.format(price));
    }
}
