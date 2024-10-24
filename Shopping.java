/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author 
 */
public class Shopping {

    private String name;
    private LocalDateTime time;
    private Repository shop;
    private ArrayList<Integer> transIndex;
    private ArrayList<Fruit> cart;
    private static final DecimalFormat numberFormat = new DecimalFormat("#,##0.00");

    public Shopping() {
    }

    public Shopping(String name, ArrayList<Fruit> cart) {
        this.name = name;
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Repository getShop() {
        return shop;
    }

    public void setShop(Repository shop) {
        this.shop = shop;
    }

    public ArrayList<Integer> getTransIndex() {
        return transIndex;
    }

    public void setTransIndex(ArrayList<Integer> transIndex) {
        this.transIndex = transIndex;
    }

    public ArrayList<Fruit> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Fruit> cart) {
        this.cart = cart;
    }

    private String formatPrice(double n) {
        return numberFormat.format(n);
    }

    public void buy() {
        cart = new ArrayList<>();
        transIndex = new ArrayList<>();
        int countItem = 0;
        int number;
        int quantity;
        translateIndex();
        boolean isCannotBuyContinue;

        do {
            if (transIndex.isEmpty()) {
                System.out.println("We sold out. See you again!");
                break;
            }

            while (true) {
                viewShop();
                number = IO.getOrder(transIndex.size());
                System.out.println("You selected: " + shop.getStore().get(transIndex.get(number - 1)).getName() + " (ID: " + shop.getStore().get(transIndex.get(number - 1)).getId() + ")");

                quantity = IO.getQuantityFruit("Please input quantity: ");

                if (quantity > shop.getStore().get(transIndex.get(number - 1)).getQuantity()) {
                    if (IO.getContinue("The remaining quantity of this product is " + shop.getStore().get(transIndex.get(number - 1)).getQuantity() + ". Do you want to buy? (Y/N)", "Please press Y to buy, N to do not buy")) {
                        System.out.println("Thank you for purchasing all " + shop.getStore().get(transIndex.get(number - 1)).getName() + " from the store! (Quantity you bought: " + shop.getStore().get(transIndex.get(number - 1)).getQuantity() + ")");
                        boolean isContains = false;
                        for (int i = 0; i < cart.size(); i++) {
                            if (cart.get(i).getId().equalsIgnoreCase(shop.getStore().get(transIndex.get(number - 1)).getId())) {
                                cart.get(i).setQuantity(cart.get(i).getQuantity() + shop.getStore().get(transIndex.get(number - 1)).getQuantity());
                                isContains = true;
                                break;
                            }
                        }

                        if (!isContains) {
                            cart.add(new Fruit(shop.getStore().get(transIndex.get(number - 1)).getId(), shop.getStore().get(transIndex.get(number - 1)).getName(), shop.getStore().get(transIndex.get(number - 1)).getOrigin(), shop.getStore().get(transIndex.get(number - 1)).getQuantity(), shop.getStore().get(transIndex.get(number - 1)).getPrice()));
                            cart.get(countItem).setQuantity(shop.getStore().get(transIndex.get(number - 1)).getQuantity());
                            countItem++;
                        }

                        shop.getStore().get(transIndex.get(number - 1)).setQuantity(0);
                        transIndex.clear();
                        translateIndex();
                        break;
                    }
                } else {
                    boolean isContains = false;
                    for (int i = 0; i < cart.size(); i++) {
                        if (cart.get(i).getId().equalsIgnoreCase(shop.getStore().get(transIndex.get(number - 1)).getId())) {
                            cart.get(i).setQuantity(cart.get(i).getQuantity() + quantity);
                            isContains = true;
                            break;
                        }
                    }

                    if (!isContains) {
                        cart.add(new Fruit(shop.getStore().get(transIndex.get(number - 1)).getId(), shop.getStore().get(transIndex.get(number - 1)).getName(), shop.getStore().get(transIndex.get(number - 1)).getOrigin(), quantity, shop.getStore().get(transIndex.get(number - 1)).getPrice()));
                        countItem++;
                    }

                    if (quantity == shop.getStore().get(transIndex.get(number - 1)).getQuantity()) {
                        shop.getStore().get(transIndex.get(number - 1)).setQuantity(0);
                        transIndex.clear();
                        translateIndex();
                    } else {
                        shop.getStore().get(transIndex.get(number - 1)).setQuantity(shop.getStore().get(transIndex.get(number - 1)).getQuantity() - quantity);
                    }
                    break;
                }
            }
            if (transIndex.isEmpty()) {
                System.out.println("We sold out. See you again!");
                isCannotBuyContinue = true;
            } else {
                isCannotBuyContinue = IO.getContinue("Do you want to order now (Y/N) ", "Please press Y to continue, N to stop");
            }
        } while (!isCannotBuyContinue);
        if (!cart.isEmpty()) {
            viewCart();
            this.name = IO.getName();
            time = LocalDateTime.now();
        }
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty");
        } else {
            double sum = 0;
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------------+");
            System.out.println("| No. |  ID  | Fruit Name         | Origin             | Quantity |   Price   |    Amount    |");
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------------+");

            int count = 1;
            for (Fruit fruit : cart) {
                sum += fruit.getQuantity() * fruit.getPrice();
                System.out.printf("| %3d ", count++);
                System.out.print(fruit.toString());
                System.out.printf("%13s |\n", "$" + formatPrice(fruit.getQuantity() * fruit.getPrice()));
                System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------------+");
            }
            System.out.printf("|                                                                       TOTAL |%13s |\n", "$" + formatPrice(sum));
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------------+");
        }
    }

    public void viewShop() {
        if (shop.getStore().isEmpty()) {
            System.out.println("Store empty");
        } else {
            System.out.println("List of Fruit:");
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+");
            System.out.println("| No. |  ID  | Fruit Name         | Origin             | Quantity |   Price   |");
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+");

            int count = 0;
            for (int i = 0; i < shop.getStore().size(); i++) {
                if (shop.getStore().get(i).getQuantity() == 0) {
                    continue;
                } else if (shop.getStore().get(i).isStatus()){
                    System.out.printf("| %3d ", ++count);
                    System.out.println(shop.getStore().get(i).toString());
                    System.out.println("+-----+------+--------------------+--------------------+----------+-----------+");
                }
            }
        }
    }

    public void translateIndex() {
        for (int i = 0; i < shop.getStore().size(); i++) {
            if (shop.getStore().get(i).getQuantity() != 0 && shop.getStore().get(i).isStatus()) {
                transIndex.add(i);
            }
        }
    }
}
