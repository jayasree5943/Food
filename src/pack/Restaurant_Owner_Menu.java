package pack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Restaurant_Owner_Menu {

    // Encapsulated menu
    private HashMap<String, Integer> menu = new HashMap<>();
    private final String MENU_FILE = "D:\\\\menu.txt";

    // Simple hardcoded credentials (for demo). Replace with secure storage in real apps.
    private final String OWNER_USERNAME = "admin";
    private final String OWNER_PASSWORD = "admin123";

    public Restaurant_Owner_Menu() {
        // Load menu from file; if file doesn't exist, start with empty menu
        loadMenuFromFile();
    }

    // Load menu from menu.txt (format: FoodName|Price per line)
    private void loadMenuFromFile() {
        File f = new File(MENU_FILE);
        if (!f.exists()) {
            // no file — start with default items if you want, or empty menu
            // If you want initial defaults uncomment below lines:
            // menu.put("Pizza", 250);
            // menu.put("Burger", 120);
            // menu.put("Pasta", 180);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length != 2) continue;
                String food = parts[0].trim();
                try {
                    int price = Integer.parseInt(parts[1].trim());
                    menu.put(food, price);
                } catch (NumberFormatException nfe) {
                    // skip malformed price line
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading menu file: " + e.getMessage());
        }
    }

    // Save menu to menu.txt (overwrite)
    private void saveMenuToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (Map.Entry<String, Integer> e : menu.entrySet()) {
                bw.write(e.getKey() + "|" + e.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving menu file: " + e.getMessage());
        }
    }

    // Authentication method
    public boolean authenticateOwner(String username, String password) {
        return OWNER_USERNAME.equals(username) && OWNER_PASSWORD.equals(password);
    }

    // Owner-only operations now accept Scanner from main (single Scanner usage)
    public void addItem(Scanner sc) {
        System.out.print("Enter food name to add: ");
        String food = sc.nextLine().trim();

        if (menu.containsKey(food)) {
            System.out.println("Item already exists!");
        } else {
            System.out.print("Enter price: ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid integer price.");
                sc.next();
            }
            int price = sc.nextInt();
            sc.nextLine(); // consume newline
            menu.put(food, price);
            saveMenuToFile(); // persist change
            System.out.println("Item added successfully.");
        }
    }

    public void updateItem(Scanner sc) {
        System.out.print("Enter food name to update: ");
        String food = sc.nextLine().trim();

        if (!menu.containsKey(food)) {
            System.out.println("Food not found!");
        } else {
            System.out.print("Enter new price: ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid integer price.");
                sc.next();
            }
            int newPrice = sc.nextInt();
            sc.nextLine();
            menu.put(food, newPrice);
            saveMenuToFile(); // persist change
            System.out.println("Item updated successfully.");
        }
    }

    public void deleteItem(Scanner sc) {
        System.out.print("Enter food name to delete: ");
        String food = sc.nextLine().trim();

        if (!menu.containsKey(food)) {
            System.out.println("Food not found!");
        } else {
            menu.remove(food);
            saveMenuToFile(); // persist change
            System.out.println("Item deleted successfully.");
        }
    }

    // Display menu (safe read-only)
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

    // Safe query methods for customers
    public boolean isAvailable(String food) {
        return menu.containsKey(food);
    }

    // returns -1 if not found (caller should check isAvailable first)
    public int getPrice(String food) {
        Integer p = menu.get(food);
        return p == null ? -1 : p.intValue();
    }

    // read-only view if needed
    public Map<String, Integer> getMenuView() {
        return Collections.unmodifiableMap(menu);
    }
}

