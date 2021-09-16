package newpackage;

public class CreditCard {

	private User user;

	private double cardLimit;

	private int cardNumber;

	private double totalDebt;

	private double minimumDebt;

	public CreditCard(double cardLimit, int cardNumber, double totalDebt) {
		this.cardLimit = cardLimit;
		this.cardNumber = cardNumber;
		this.setTotalDebt(totalDebt);
	}

	public boolean payDebt(Account account, double amount) {
		if ((amount >= minimumDebt) && account.checkBalance(amount)) {
			account.setBalance(account.getBalance() - amount);
			this.setTotalDebt(this.getTotalDebt() - amount);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Card Number: " + cardNumber + " - Total Debt: " + String.format("%.2f", totalDebt) + " - Card Limit: " + cardLimit + " - Minimum Debt: " + String.format("%.2f", minimumDebt);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getTotalDebt() {
		return totalDebt;
	}

	public void setTotalDebt(double totalDebt) {
		this.totalDebt = totalDebt;
		this.setMinimumDebt(Math.round((totalDebt / 4.0) * 100.0) / 100.0); // yuzde 25 odenmesi gereken en az tutar
	}

	public double getMinimumDebt() {
		return minimumDebt;
	}

	private void setMinimumDebt(double minimumDebt) {
		this.minimumDebt = minimumDebt;
	}

}
