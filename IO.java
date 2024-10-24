/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.util.Scanner;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author 
 */
public class IO {

    private static Scanner sc = new Scanner(System.in);

    public static int getOption(int min, int max) {
        int n;
        while (true) {
            try {
                System.out.print("Please choose: ");
                n = Integer.parseInt(sc.nextLine().trim());
                if (n < min || n > max) {
                    throw new NumberFormatException();
                }
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number from " + min + " - " + max);
            }
        }
    }

    public static String getIDFruit() {
        String s;
        while (true) {
            try {
                System.out.print("ID fruit: ");
                s = sc.nextLine().trim();
                if (s.isEmpty()) {
                    throw new Exception("ID of fruit cannot be empty!");
                } else if (s.length() != 4) {
                    throw new Exception("ID of fruit must be 4 characters!");
                }
                return s;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getNameFruit() {
        String s;
        while (true) {
            try {
                System.out.print("Name fruit: ");
                s = sc.nextLine().trim();
                if (s.isEmpty()) {
                    throw new Exception();
                }
                return s;
            } catch (Exception e) {
                System.out.println("Name cannot be empty");
            }
        }
    }

    public static double getPriceFruit(String msg) {
        double n;
        while (true) {
            try {
                System.out.print(msg);
                n = Double.parseDouble(sc.nextLine().trim());
                if (n <= 0) {
                    throw new NumberFormatException();
                }
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Price of fruit must be an integer number greater than 0");
            }
        }
    }

    public static int getQuantityFruit(String msg) {
        int n;
        while (true) {
            try {
                System.out.print(msg);
                n = Integer.parseInt(sc.nextLine().trim());
                if (n <= 0) {
                    throw new NumberFormatException();
                }
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Quantity of fruit must be an integer number greater than 0");
            }
        }
    }

    public static String getOriginFruit() {
        String s;
        while (true) {
            try {
                System.out.print("Origin fruit: ");
                s = sc.nextLine().trim();
                if (s.isEmpty()) {
                    throw new Exception("Name cannot be empty");
                } else {
                    char c[] = s.toCharArray();
                    for (char d : c) {
                        if (!Character.isLetter(d)) {
                            throw new Exception("Name of country only contain letters");
                        }
                    }
                }
                return s;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean getContinue(String msg, String err) {
        String s;
        while (true) {
            try {
                System.out.print(msg);
                s = sc.nextLine().trim();
                if (s.isEmpty()) {
                    throw new Exception();
                } else if (!s.equalsIgnoreCase("Y") && !s.equalsIgnoreCase("N")) {
                    throw new Exception();
                } else {
                    if (s.equalsIgnoreCase("Y")) {
                        return true;
                    } else if (s.equalsIgnoreCase("N")) {
                        return false;
                    }
                }
            } catch (Exception e) {
                System.out.println(err);
            }
        }
    }

    public static String getName() {
        String s;
        while (true) {
            try {
                System.out.print("Input your name: ");
                s = sc.nextLine().trim();
                char c[] = s.toCharArray();

                if (s.isEmpty()) {
                    throw new Exception("Name cannot be empty");
                } else {
                    for (char d : c) {
                        if (!Character.isLetter(d) && !Character.isWhitespace(d)) {
                            throw new Exception("Name must include letters and may include spaces");
                        }
                    }
                }
                return s;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getOrder(int bound) {
        int n;
        while (true) {
            try {
                System.out.print("Choose product number in the table: ");
                n = Integer.parseInt(sc.nextLine().trim());
                if (n <= 0 || n > bound) {
                    throw new NumberFormatException();
                }
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Please choose a numerical order in the table");
            }
        }
    }
}
