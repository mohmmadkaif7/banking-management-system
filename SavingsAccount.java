public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.04; // 4% annual → monthly
    private int withdrawalCount = 0;
    private static final int MAX_WITHDRAWALS = 5;

    public SavingsAccount(String accountNumber, String holderName, double initialDeposit) {
        super(accountNumber, holderName, initialDeposit);
    }

    @Override
    public synchronized void withdraw(double amount) {
        if (withdrawalCount >= MAX_WITHDRAWALS)
            throw new IllegalStateException("Monthly withdrawal limit (5) reached for savings account.");
        super.withdraw(amount);
        withdrawalCount++;
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = balance * (INTEREST_RATE / 12);
        balance += interest;
        log(String.format("Interest       | ₹%.2f added (4%% p.a.) | Balance: ₹%.2f", interest, balance));
        withdrawalCount = 0; // reset monthly withdrawal count
    }

    @Override
    public String getAccountType() { return "Savings"; }
}
