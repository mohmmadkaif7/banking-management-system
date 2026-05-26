import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
    private final String bankName;
    private final Map<String, Account> accounts = new LinkedHashMap<>();
    private final AtomicInteger accCounter = new AtomicInteger(1000);

    public Bank(String bankName) { this.bankName = bankName; }

    private String generateAccNumber(String prefix) {
        return prefix + accCounter.incrementAndGet();
    }

    public SavingsAccount createSavings(String name, double deposit) {
        SavingsAccount acc = new SavingsAccount(generateAccNumber("SAV"), name, deposit);
        accounts.put(acc.getAccountNumber(), acc);
        return acc;
    }

    public CurrentAccount createCurrent(String name, double deposit, double overdraft) {
        CurrentAccount acc = new CurrentAccount(generateAccNumber("CUR"), name, deposit, overdraft);
        accounts.put(acc.getAccountNumber(), acc);
        return acc;
    }

    public Optional<Account> find(String accNumber) {
        return Optional.ofNullable(accounts.get(accNumber));
    }

    public void applyMonthlyInterestAll() {
        accounts.values().forEach(Account::applyMonthlyInterest);
        System.out.println("Monthly interest/fees applied to all accounts.");
    }

    public void printAllAccounts() {
        System.out.println("\n=== " + bankName + " — All Accounts ===");
        accounts.values().forEach(a ->
            System.out.printf("  %-12s | %-20s | %s | ₹%.2f%n",
                a.getAccountNumber(), a.getHolderName(), a.getAccountType(), a.getBalance()));
        System.out.println();
    }

    public String getBankName() { return bankName; }
}
