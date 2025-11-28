package pack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customers_Order extends Restaurant_Owner_Menu {

    private String name;
    private String food;
    private int quantity;

    private HashMap<String, Integer> order = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public Customers_Order(String name) {
        super();
        this.name = name;
    }

    // Taking food and quantity using Scanner
    public void takeOrderInput() {
        System.out.print("Enter food name: ");
        food = sc.next();

        System.out.print("Enter quantity: ");
        quantity = sc.nextInt();
    }

    // Place order
    public void placeOrder() {
        if (!menu.containsKey(food)) {
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

    // Print bill
    public void printBill() {
        System.out.println("----- BILL (" + name + ") -----");

        if (order.isEmpty()) {
            System.out.println("No items ordered.");
            return;
        }

        int total = 0;

        for (Map.Entry<String, Integer> e : order.entrySet()) {
            String foodName = e.getKey();
            int qty = e.getValue();
            int price = menu.get(foodName);

            int lineTotal = price * qty;
            System.out.println(foodName + " x " + qty + " = " + lineTotal);

            total += lineTotal;
        }
        System.out.println("------------------------");
        System.out.println("TOTAL BILL : " + total);
        System.out.println("------------------------");
    }
}
