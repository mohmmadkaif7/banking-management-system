public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String holderName, double initialDeposit, double overdraftLimit) {
        super(accountNumber, holderName, initialDeposit);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public synchronized void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        if (amount > balance + overdraftLimit)
            throw new IllegalStateException("Exceeds overdraft limit of ₹" + overdraftLimit);
        balance -= amount;
        log(String.format("Withdrawal     | ₹%.2f | Balance: ₹%.2f%s",
                amount, balance, balance < 0 ? " (overdraft)" : ""));
    }

    @Override
    public void applyMonthlyInterest() {
        // Current accounts don't earn interest; charge overdraft fee if balance negative
        if (balance < 0) {
            double fee = Math.abs(balance) * 0.01;
            balance -= fee;
            log(String.format("Overdraft Fee  | ₹%.2f charged | Balance: ₹%.2f", fee, balance));
        }
    }

    @Override
    public String getAccountType() { return "Current"; }
}
