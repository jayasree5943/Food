package pack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Restaurant_Owner_Menu {

    protected HashMap<String, Integer> menu = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public Restaurant_Owner_Menu() {
        // Hard-coded menu
        menu.put("Pizza", 250);
        menu.put("Burger", 120);
        menu.put("Pasta", 180);
    }

    // Add item by user input
    public void addItem() {
        System.out.print("Enter food name to add: ");
        String food = sc.next();

        if (menu.containsKey(food)) {
            System.out.println("Item already exists!");
        } else {
            System.out.print("Enter price: ");
            int price = sc.nextInt();
            menu.put(food, price);
            System.out.println("Item added successfully.");
        }
    }

    // Update price
    public void updateItem() {
        System.out.print("Enter food name to update: ");
        String food = sc.next();

        if (!menu.containsKey(food)) {
            System.out.println("Food not found!");
        } else {
            System.out.print("Enter new price: ");
            int newPrice = sc.nextInt();
            menu.put(food, newPrice);
            System.out.println("Item updated successfully.");
        }
    }

    // Delete item
    public void deleteItem() {
        System.out.print("Enter food name to delete: ");
        String food = sc.next();

        if (!menu.containsKey(food)) {
            System.out.println("Food not found!");
        } else {
            menu.remove(food);
            System.out.println("Item deleted successfully.");
        }
    }

    // Display menu
    public void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu is Empty");
            return;
        }

        System.out.println("----- MENU -----");
        System.out.println("FOOD\tPRICE");

        for (Map.Entry<String, Integer> e : menu.entrySet()) {
            System.out.println(e.getKey() + "\t" + e.getValue());
        }
        System.out.println("----------------");
    }
}
