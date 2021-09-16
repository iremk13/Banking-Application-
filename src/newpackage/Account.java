package newpackage;

public class Account {

	private User user;

	private double balance;

	private double minimumBalanceLimit;

	private int accountNumber;

	private long IBAN;
	
	static final double MINIMUM_BALANCE_LIMIT = 100.0;

	public Account(User user, double balance, int accountNumber, long IBAN) {
		this.user = user;
		this.balance = balance;
		this.minimumBalanceLimit = MINIMUM_BALANCE_LIMIT;
		this.accountNumber = accountNumber;
		this.IBAN = IBAN;
	}

	public boolean eftTransaction(Account receiverAccount, double amount) {
		if (checkBalance(amount)) {
			receiverAccount.setBalance(receiverAccount.getBalance() + amount);
			this.setBalance(this.getBalance() - amount);
			return true;
		}
		return false;
	}

	public boolean checkBalance(double amount) {
		return ((this.getBalance() - amount) >= this.getMinimumBalanceLimit());
	}

	@Override
	public String toString() {
		return "Account Number: " + accountNumber + " - Balance: " + String.format("%.2f", balance) + " - IBAN: " + IBAN;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getMinimumBalanceLimit() {
		return minimumBalanceLimit;
	}

	public void setMinimumBalanceLimit(double minimumBalanceLimit) {
		this.minimumBalanceLimit = minimumBalanceLimit;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getIBAN() {
		return IBAN;
	}

	public void setIBAN(long IBAN) {
		this.IBAN = IBAN;
	}

}
