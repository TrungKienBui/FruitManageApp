/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.util.ArrayList;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author
 */
public class Repository {

    private ArrayList<Fruit> store;

    public Repository() {
        store = new ArrayList<>();
        store.add(new Fruit("C001", "Cam", "VN", 141, 0.3));
        store.add(new Fruit("Q002", "Quyt", "VN", 132, 0.5));
        store.add(new Fruit("K003", "Kiwi", "US", 153, 1.2));
    }

    public Repository(ArrayList<Fruit> store) {
        this.store = store;
    }

    public ArrayList<Fruit> getStore() {
        return store;
    }

    public void setStore(ArrayList<Fruit> store) {
        this.store = store;
    }

    public void createFruit() {
        String id;
        String name;
        String origin;
        if (!store.isEmpty()) {
            if (isIDExisted(id = IO.getIDFruit()) != -1) {
                if (store.get(isIDExisted(id)).isStatus()) {
                    System.out.println("This fruit has existed in your store!");
                    if (IO.getContinue("Do you want to update this fruit? (Y/N) ", "Please press Y to update, N to cancel!")) {
                        System.out.println("1. Change price of fruit");
                        System.out.println("2. Add quantity of fruit");
                        System.out.println("3. Add quantity and change price");
                        switch (IO.getOption(1, 3)) {
                            case 1:
                                store.get(isIDExisted(id)).setPrice(IO.getPriceFruit("Price fruit you want to change: "));
                                break;
                            case 2:
                                store.get(isIDExisted(id)).setQuantity(store.get(isIDExisted(id)).getQuantity() + IO.getQuantityFruit("Quantity fruit you want to add: "));
                                break;
                            case 3:
                                store.get(isIDExisted(id)).setPrice(IO.getPriceFruit("Price fruit you want to change: "));
                                store.get(isIDExisted(id)).setQuantity(store.get(isIDExisted(id)).getQuantity() + IO.getQuantityFruit("Quantity fruit you want to add: "));
                                break;
                        }
                    }
                } else {
                    System.out.println("This fruit has existed in your store but it is disabled!");
                }
            } else if (isTypeExisted(name = IO.getNameFruit(), origin = IO.getOriginFruit()) != -1) {
                if (store.get(isTypeExisted(name, origin)).isStatus()) {
                    System.out.println("This fruit has existed in your store!");
                    if (IO.getContinue("Do you want to update this fruit? (Y/N) ", "Please press Y to update, N to cancel!")) {
                        System.out.println("1. Change price of fruit");
                        System.out.println("2. Add quantity of fruit");
                        System.out.println("3. Add quantity and change price");
                        switch (IO.getOption(1, 3)) {
                            case 1:
                                store.get(isTypeExisted(name, origin)).setPrice(IO.getPriceFruit("Price fruit you want to change: "));
                                break;
                            case 2:
                                store.get(isTypeExisted(name, origin)).setQuantity(store.get(isTypeExisted(name, origin)).getQuantity() + IO.getQuantityFruit("Quantity fruit you want to add: "));
                                break;
                            case 3:
                                store.get(isTypeExisted(name, origin)).setPrice(IO.getPriceFruit("Price fruit you want to change: "));
                                store.get(isTypeExisted(name, origin)).setQuantity(store.get(isTypeExisted(name, origin)).getQuantity() + IO.getQuantityFruit("Quantity fruit you want to add: "));
                                break;
                        }
                    }
                } else {
                    System.out.println("This fruit has existed in your store but it is disabled!");
                }
            } else {
                store.add(new Fruit(id, name, origin, IO.getQuantityFruit("Quantity fruit: "), IO.getPriceFruit("Price fruit: ")));
            }
        } else {
            store.add(new Fruit(IO.getIDFruit(), IO.getNameFruit(), IO.getOriginFruit(), IO.getQuantityFruit("Quantity fruit: "), IO.getPriceFruit("Price fruit: ")));
        }
    }

    public void setStatus() {
        String id;
        if (!store.isEmpty()) {
            while (true) {
                if (isIDExisted(id = IO.getIDFruit()) != -1) {
                    System.out.printf("You choose %s (ID: %s)\n", store.get(isIDExisted(id)).getName(), id);
                    if (IO.getContinue("Do you want to active this fruit (Y = active, N = disable) ", "Please press Y to active, N to disable!")) {
                        store.get(isIDExisted(id)).setStatus(true);
                    } else {
                        store.get(isIDExisted(id)).setStatus(false);
                    }
                    return;
                } else {
                    System.out.println("This ID is not exist");
                    continue;
                }
            }
        } else {
            System.out.println("Please create fruit!");
        }
    }

    public int isTypeExisted(String name, String origin) {
        if (!store.isEmpty()) {
            for (int i = 0; i < store.size(); i++) {
                if (store.get(i).getName().equalsIgnoreCase(name) && store.get(i).getOrigin().equalsIgnoreCase(origin)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int isIDExisted(String id) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void viewRepo() {
        if (store.isEmpty()) {
            System.out.println("Store empty");
        } else {
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+------------+");
            System.out.println("| No. |  ID  | Fruit Name         | Origin             | Quantity |   Price   |   Status   |");
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+------------+");

            int count = 1;
            for (Fruit fruit : store) {
                System.out.printf("| %3d ", count++);
                if (fruit.getQuantity() == 0) {
                    System.out.printf("| %-4s | %-19s| %-19s|%9s |%10s |", fruit.getId(), fruit.getName(), fruit.getOrigin(), "SOLD OUT", "$" + fruit.formatPrice());
                    System.out.printf("  %7s   |\n", fruit.getStatus());
                } else {
                    System.out.print(fruit.toString());
                    System.out.printf("  %7s   |\n", fruit.getStatus());
                }
                System.out.println("+-----+------+--------------------+--------------------+----------+-----------+------------+");
            }
        }
    }

    public void removeFruit(int i) {
        store.remove(i);
    }

}
