package pack;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Restaurant_Owner_Menu r = new Restaurant_Owner_Menu();

        System.out.println("Enter Customer Name: ");
        String cname = sc.nextLine();
        Customers_Order c1 = new Customers_Order(cname);

        int choice;

        do {
            System.out.println("\n==== FOOD DELIVERY APP ====");
            System.out.println("1. Display Menu");
            System.out.println("2. Add Item (Restaurant)");
            System.out.println("3. Update Item (Restaurant)");
            System.out.println("4. Delete Item (Restaurant)");
            System.out.println("5. Place Order (Customer)");
            System.out.println("6. Show Order");
            System.out.println("7. Print Bill");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    r.displayMenu();
                    break;

                case 2:
                    r.addItem();
                    break;

                case 3:
                    r.updateItem();
                    break;

                case 4:
                    r.deleteItem();
                    break;

                case 5:
                    c1.displayMenu();
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
}
