package pack;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Restaurant_Owner_Menu r = new Restaurant_Owner_Menu(); // now loads menu from file (menu.txt)
        
        System.out.print("Enter Customer Name: ");
        String cname = sc.nextLine();
        Customers_Order c1 = new Customers_Order(cname, r, sc);

        int choice = 0;

        do {
            System.out.println("\n==== FOOD DELIVERY APP ====");
            System.out.println("1. Display Menu");
            System.out.println("2. Add Item (Restaurant)  <-- owner only");
            System.out.println("3. Update Item (Restaurant)  <-- owner only");
            System.out.println("4. Delete Item (Restaurant)  <-- owner only");
            System.out.println("5. Place Order (Customer)");
            System.out.println("6. Show Order");
            System.out.println("7. Print Bill");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number.");
                sc.next(); // consume invalid token
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    r.displayMenu();
                    break;

                case 2:
                    // owner authentication required
                    if (authenticateAndProceed(r, sc)) {
                        r.addItem(sc);
                    } else {
                        System.out.println("Authentication failed. Cannot add item.");
                    }
                    break;

                case 3:
                    if (authenticateAndProceed(r, sc)) {
                        r.updateItem(sc);
                    } else {
                        System.out.println("Authentication failed. Cannot update item.");
                    }
                    break;

                case 4:
                    if (authenticateAndProceed(r, sc)) {
                        r.deleteItem(sc);
                    } else {
                        System.out.println("Authentication failed. Cannot delete item.");
                    }
                    break;

                case 5:
                    r.displayMenu();      // show current menu to customer
                    c1.takeOrderInput();
                    c1.placeOrder();
                    break;

                case 6:
                    c1.showOrder();
                    break;

                case 7:
                    c1.printBill();
                    break;

                case 8:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 8);

        sc.close();
    }

    // Ask credentials and call restaurant authentication method
    private static boolean authenticateAndProceed(Restaurant_Owner_Menu r, Scanner sc) {
        System.out.print("Owner username: ");
        String user = sc.nextLine();
        System.out.print("Owner password: ");
        String pass = sc.nextLine();
        return r.authenticateOwner(user, pass);
    }
}

