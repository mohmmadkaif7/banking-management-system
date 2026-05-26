import java.util.Scanner;

public class BankingApp {
    static Bank bank = new Bank("KaifBank");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        seedAccounts();
        boolean running = true;
        while (running) {
            System.out.println("\n1.Create Savings  2.Create Current  3.Deposit  4.Withdraw  5.Transfer  6.Statement  7.All Accounts  8.Apply Interest  9.Exit");
            System.out.print("Choice: ");
            switch (sc.nextLine().trim()) {
                case "1" -> createSavings();
                case "2" -> createCurrent();
                case "3" -> deposit();
                case "4" -> withdraw();
                case "5" -> transfer();
                case "6" -> statement();
                case "7" -> bank.printAllAccounts();
                case "8" -> bank.applyMonthlyInterestAll();
                case "9" -> { running = false; System.out.println("Thank you for using " + bank.getBankName()); }
                default  -> System.out.println("Invalid choice.");
            }
        }
    }

    static void seedAccounts() {
        SavingsAccount s1 = bank.createSavings("Mohammad Kaif", 50000);
        SavingsAccount s2 = bank.createSavings("Alice Johnson", 25000);
        CurrentAccount c1 = bank.createCurrent("TechCorp Ltd", 100000, 50000);
        s1.deposit(10000);
        s2.deposit(5000);
        System.out.println("Welcome to " + bank.getBankName() + "! Demo accounts created.");
        bank.printAllAccounts();
    }

    static void createSavings() {
        System.out.print("Name: "); String name = sc.nextLine().trim();
        System.out.print("Initial deposit: "); 
        try { double amt = Double.parseDouble(sc.nextLine().trim());
            SavingsAccount a = bank.createSavings(name, amt);
            System.out.println("Savings account created: " + a.getAccountNumber()); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void createCurrent() {
        System.out.print("Name: "); String name = sc.nextLine().trim();
        System.out.print("Initial deposit: ");
        try { double dep = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Overdraft limit: "); double od = Double.parseDouble(sc.nextLine().trim());
            CurrentAccount a = bank.createCurrent(name, dep, od);
            System.out.println("Current account created: " + a.getAccountNumber()); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void deposit() {
        Account a = getAccount(); if (a == null) return;
        System.out.print("Amount: ");
        try { a.deposit(Double.parseDouble(sc.nextLine().trim())); System.out.println("Done. Balance: ₹" + a.getBalance()); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void withdraw() {
        Account a = getAccount(); if (a == null) return;
        System.out.print("Amount: ");
        try { a.withdraw(Double.parseDouble(sc.nextLine().trim())); System.out.println("Done. Balance: ₹" + a.getBalance()); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void transfer() {
        System.out.print("From account: "); Account from = bank.find(sc.nextLine().trim().toUpperCase()).orElse(null);
        System.out.print("To account: ");   Account to   = bank.find(sc.nextLine().trim().toUpperCase()).orElse(null);
        if (from == null || to == null) { System.out.println("Account(s) not found."); return; }
        System.out.print("Amount: ");
        try { from.transfer(to, Double.parseDouble(sc.nextLine().trim())); System.out.println("Transfer successful."); }
        catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void statement() { Account a = getAccount(); if (a != null) a.printStatement(); }

    static Account getAccount() {
        System.out.print("Account number: ");
        Account a = bank.find(sc.nextLine().trim().toUpperCase()).orElse(null);
        if (a == null) System.out.println("Account not found.");
        return a;
    }
}
