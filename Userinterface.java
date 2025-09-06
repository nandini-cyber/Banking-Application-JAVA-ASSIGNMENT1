
import java.util.Scanner;   // to take input from user

public class Userinterface {
    
    private static Account[] accounts = new Account[100];
    private static int accountCount = 0;          // how many accounts created so far
    private static int nextAccountNumber = 1001;  // first account number
    private static Scanner sc = new Scanner(System.in);

    
    public static void main(String[] args) {
        System.out.println("Welcome to the Banking Application!");
        showMenu();   // call the menu
    }

    // Method to show menu repeatedly
    private static void showMenu() {
        while (true) {   // loop until user chooses exit
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();   // read user’s choice
            sc.nextLine();               // clear leftover newline

            
            if (choice == 1) {
                createAccount();
            } else if (choice == 2) {
                depositMoney();
            } else if (choice == 3) {
                withdrawMoney();
            } else if (choice == 4) {
                viewAccount();
            } else if (choice == 5) {
                updateContact();
            } else if (choice == 6) {
                System.out.println("Thank you for using the app. Goodbye!");
                break; // exit loop → program ends
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Create a new account
    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit: ");
        double deposit = sc.nextDouble();
        sc.nextLine();   // clear newline

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        // create new account object
        Account acc = new Account(nextAccountNumber, name, deposit, email, phone);

        // add to array
        accounts[accountCount] = acc;
        accountCount++;
        nextAccountNumber++;

        System.out.println("Account created successfully! Your Account Number is: " + acc.getAccountNumber());
    }

    // Deposit money
    private static void depositMoney() {
        Account acc = findAccount();  // search account
        if (acc == null) return;

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        acc.deposit(amount);  // call deposit method from Account class
    }

    // Withdraw money
    private static void withdrawMoney() {
        Account acc = findAccount();
        if (acc == null) return;

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        acc.withdraw(amount);
    }

    // View account details
    private static void viewAccount() {
        Account acc = findAccount();
        if (acc != null) {
            acc.displayAccountDetails();
        }
    }

    // Update contact details
    private static void updateContact() {
        Account acc = findAccount();
        if (acc == null) return;

        System.out.print("Enter new email: ");
        String email = sc.nextLine();

        System.out.print("Enter new phone number: ");
        String phone = sc.nextLine();

        acc.updateContactDetails(email, phone);
    }

    // Search account by account number
    private static Account findAccount() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        // check each account in array
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];   // return account if found
            }
        }

        System.out.println("Account not found!");
        return null;   // not found
    }
}
