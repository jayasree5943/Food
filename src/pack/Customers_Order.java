package pack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customers_Order {

    private String name;
    private String food;
    private int quantity;

    private HashMap<String, Integer> order = new HashMap<>();
    private Restaurant_Owner_Menu restaurant; // composition: holds reference to restaurant
    private Scanner sc;
    private final String ORDERS_FILE = "D:\\\\orders.txt";

    // constructor now receives the restaurant reference and single Scanner
    public Customers_Order(String name, Restaurant_Owner_Menu restaurant, Scanner sc) {
        this.name = name;
        this.restaurant = restaurant;
        this.sc = sc;
    }

    // Taking food and quantity using Scanner
    public void takeOrderInput() {
        System.out.print("Enter food name: ");
        food = sc.nextLine().trim();

        System.out.print("Enter quantity: ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid integer quantity.");
            sc.next();
        }
        quantity = sc.nextInt();
        sc.nextLine(); // consume newline
    }

    // Place order - uses restaurant's public methods (cannot modify menu)
    public void placeOrder() {
        if (!restaurant.isAvailable(food)) {
            System.out.println("Food not available!");
            return;
        }

        order.put(food, order.getOrDefault(food, 0) + quantity);
        System.out.println("Item added to order.");
    }

    // Show order
    public void showOrder() {
        System.out.println("Order for " + name + ":");

        if (order.isEmpty()) {
            System.out.println("No items ordered.");
            return;
        }

        System.out.println("FOOD\tQUANTITY");
        for (Map.Entry<String, Integer> e : order.entrySet()) {
            System.out.println(e.getKey() + "\t" + e.getValue());
        }
    }

    // Print bill - uses restaurant.getPrice(...) (customer cannot change price)
    // Also append this order to orders.txt
    public void printBill() {
        System.out.println("----- BILL (" + name + ") -----");

        if (order.isEmpty()) {
            System.out.println("No items ordered.");
            return;
        }

        int total = 0;
        StringBuilder orderSummary = new StringBuilder();

        for (Map.Entry<String, Integer> e : order.entrySet()) {
            String foodName = e.getKey();
            int qty = e.getValue();
            int price = restaurant.getPrice(foodName);

            int lineTotal = price * qty;
            System.out.println(foodName + " x " + qty + " = " + lineTotal);

            total += lineTotal;

            orderSummary.append(foodName)
                        .append(" x ")
                        .append(qty)
                        .append(" = ")
                        .append(lineTotal)
                        .append("; ");
        }
        System.out.println("------------------------");
        System.out.println("TOTAL BILL : " + total);
        System.out.println("------------------------");

        // Append to orders file
        appendOrderToFile(orderSummary.toString(), total);
    }

    // Append a human-readable order record to ORDERS_FILE
    private void appendOrderToFile(String orderSummary, int total) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(fmt);
        String record = String.format("[%s] Customer: %s | Items: %s Total: %d", time, name, orderSummary, total);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ORDERS_FILE, true))) {
            bw.write(record);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving order to file: " + e.getMessage());
        }
    }
}

