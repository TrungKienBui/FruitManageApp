/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author 
 */
public class L03 {

    public static void main(String[] args) {
        int option;
        Repository store = new Repository();
        Shopping customer = new Shopping();
        CustomerOrders orders = new CustomerOrders();

        do {
            menu();
            option = IO.getOption(1, 6);

            switch (option) {
                case 1:
                    store.viewRepo();
                    break;
                case 2:
                    store.viewRepo();
                    boolean check = true;
                    while (check) {
                        store.createFruit();
                        store.viewRepo();
                        check = IO.getContinue("Do you want to order now (Y/N) ", "Please press Y to continue, N to stop");
                    }
                    break;

                case 3:
                    if (orders.getListOrder().isEmpty()) {
                        System.out.println("No one bought anything");
                    } else {
                        orders.viewOrders();
                    }
                    break;

                case 4:
                    if (store.getStore().isEmpty()) {
                        System.out.println("There are currently no items in the store. See you later!");
                    } else {
                        customer.setShop(store);
                        customer.buy();
                        if (!customer.getCart().isEmpty()) {
                            orders.addBill(customer.getTime(), new Shopping(customer.getName(), customer.getCart()));
                        }
                    }
                    break;

                case 5:
                    store.viewRepo();
                    boolean check1 = true;
                    while (check1) {
                        store.setStatus();
                        store.viewRepo();
                        check1 = IO.getContinue("Do you want to set status for other fruit (Y/N) ", "Please press Y to continue, N to stop");
                    }
                    break;
                    
                case 6:
                    System.out.println("Thanks for your shopping");
                    break;
            }
        } while (option != 6);
    }

    public static void menu() {
        System.out.println("   FRUIT SHOP SYSTEM");
        System.out.println("1. View Store");
        System.out.println("2. Create Fruit");
        System.out.println("3. View orders");
        System.out.println("4. Shopping (for buyer)");
        System.out.println("5. Set status");
        System.out.println("6. Exit");
    }
}
