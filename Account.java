import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountNumber;
    protected String holderName;
    protected double balance;
    protected List<String> transactionHistory;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Account(String accountNumber, String holderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        log("Account opened | Initial deposit: ₹" + initialDeposit);
    }

    public synchronized void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");
        balance += amount;
        log("Deposit        | ₹" + amount + " | Balance: ₹" + balance);
    }

    public synchronized void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        if (amount > balance) throw new IllegalStateException("Insufficient funds.");
        balance -= amount;
        log("Withdrawal     | ₹" + amount + " | Balance: ₹" + balance);
    }

    public synchronized void transfer(Account target, double amount) {
        this.withdraw(amount);
        target.deposit(amount);
        log("Transfer OUT   | ₹" + amount + " → " + target.accountNumber);
        target.transactionHistory.add(LocalDateTime.now().format(FMT) +
            " | Transfer IN    | ₹" + amount + " ← " + this.accountNumber);
    }

    protected void log(String message) {
        transactionHistory.add(LocalDateTime.now().format(FMT) + " | " + message);
    }

    public abstract String getAccountType();
    public abstract void applyMonthlyInterest();

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName()    { return holderName; }
    public double getBalance()        { return balance; }
    public List<String> getHistory()  { return transactionHistory; }

    public void printStatement() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.printf("  %s Account: %s%n", getAccountType(), accountNumber);
        System.out.printf("  Holder  : %s%n", holderName);
        System.out.printf("  Balance : ₹%.2f%n", balance);
        System.out.println("  --- Transaction History ---");
        transactionHistory.forEach(t -> System.out.println("  " + t));
        System.out.println("══════════════════════════════════════════\n");
    }
}
