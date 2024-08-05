package banking_system;


class CheckingAccount extends Account<String> {
    private double overdraftLimit;

    public CheckingAccount(Integer accountNumber, double balance, String accountHolder, double overdraftLimit) {
        super(accountNumber, balance, accountHolder);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "accountNumber=" + getAccountNumber() +
                ", balance=" + getBalance() +
                ", accountHolder=" + getAccountHolder() +
                ", overdraftLimit=" + overdraftLimit +
                '}';
    }
}
