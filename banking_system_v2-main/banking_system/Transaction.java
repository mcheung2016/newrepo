package banking_system;


class Transaction<T extends Account<String>> {
    private T account;
    private double amount;
    private boolean isDeposit;

    public Transaction(T account, double amount, boolean isDeposit) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }
        this.account = account;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    public void execute() {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}
