package newpackage;

import java.util.Scanner;

public class UserOperations {

	private static final int MAX_ACCOUNT_COUNT = 5;

	private static final int MAX_CREDIT_CARD_COUNT = 2;

	private static String operation = MainPanel.operation;

	private static Scanner scanner = MainPanel.scanner;

	private static User loggedOnUser = MainPanel.loggedOnUser;

	public static void userOperations() {

		while (!operation.equals("e")) {
			System.out.println("Choose the operation:");
			System.out.println("\n1.Add Account\n2.Add Credit Card\n3.Pay Credit Card Debt\n4.Make EFT Operation\n"
					+ "5.Show Acounts Informations\n6.Show Credit Cards Informations\ne.Sign Out\n");

			operation = scanner.nextLine();

			switch (operation) {
			case "1":
				if (loggedOnUser.getAccountList().size() < MAX_ACCOUNT_COUNT) {
					boolean result = addAccount();
					if (result) {
						System.out.println("\nAccount has added successfully.\n");
					}
				} else {
					System.out.println("\nYou already have " + loggedOnUser.getAccountList().size()
							+ " accounts. You can't add more account.");
					System.out.println("\n-----------------------------------------------------------------\n");
				}
				break;
			case "2":
				if (loggedOnUser.getCreditCardList().size() < MAX_CREDIT_CARD_COUNT) {
					boolean result = addCreditCard();
					if (result) {
						System.out.println("\nCredit Card has added successfully.\n");
					}
				} else {
					System.out.println("\nYou already have " + loggedOnUser.getAccountList().size()
							+ " credit cards. You can't add more credit cards.");
					System.out.println("\n-----------------------------------------------------------------\n");
				}
				break;
			case "3":
				payCreditCardDebt();
				break;
			case "4":
				eftOperation();
				break;
			case "5":
				if (loggedOnUser.getAccountList().size() == 0) {
					System.out.println("\nThere is no account found of the user.\n");
				} else {
					for (Account account : loggedOnUser.getAccountList()) {
						System.out.println(account.toString());
					}
				}
				break;
			case "6":
				if (loggedOnUser.getCreditCardList().size() == 0) {
					System.out.println("\nThere is no credit card found of the user.\n");
				} else {
					for (CreditCard creditCard : loggedOnUser.getCreditCardList()) {
						System.out.println(creditCard.toString());
					}
				}
				break;
			case "e":
				MainPanel.userMap.put(loggedOnUser.getCustomerNumber(), loggedOnUser);
				System.out.println("\nProgram closed succesfully...");
				break;
			default:
				System.out.println("\nError Occured!!!\nYou have typed wrong command.");
				System.out.println("\n*****************************************************************\n");
				break;
			}
		}

	}

	private static boolean addAccount() {
		try {
			System.out.print("Enter account number: ");
			int accountNumber = Integer.parseInt(scanner.nextLine());

			if (!loggedOnUser.isAcountExists(accountNumber)) {
				System.out.print("Enter account balance: ");
				double accountBalance = Double.parseDouble(scanner.nextLine());

				if (accountBalance < Account.MINIMUM_BALANCE_LIMIT) {
					System.out.println("Your account balance should be over minimum balance limit ("
							+ Account.MINIMUM_BALANCE_LIMIT + ")\n");
					return false;
				}

				System.out.print("Enter account IBAN: ");
				long accountIBAN = Long.parseLong(scanner.nextLine());

				Account account = new Account(loggedOnUser, accountBalance, accountNumber, accountIBAN);
				loggedOnUser.getAccountList().add(account);
				account.setUser(loggedOnUser);

				return true;
			} else {
				System.out.println("\nYou already have an account with " + accountNumber + " account number.");
				System.out.println("\n-----------------------------------------------------------------\n");
				return false;
			}
		} catch (NumberFormatException e) {
			System.out.println("\nError Occured!!! Account number, balance and IBAN shouldn't"
					+ " contain alphabetical characters!!!\nAdding Account Operation Failed.");
			System.out.println("\n*****************************************************************\n");
			return false;
		}
	}

	private static boolean addCreditCard() {
		try {
			System.out.print("Enter card number: ");
			int creditCardNumber = Integer.parseInt(scanner.nextLine());

			if (!loggedOnUser.isCreditCardExists(creditCardNumber)) { //bu numarayla baska kart varsa hata
				System.out.print("Enter card limit: ");
				double creditCardLimit = Double.parseDouble(scanner.nextLine());

				System.out.print("Enter card total debt: ");
				double creditCardTotalDebt = Double.parseDouble(scanner.nextLine());

				CreditCard creditCard = new CreditCard(creditCardLimit, creditCardNumber, creditCardTotalDebt);
				loggedOnUser.getCreditCardList().add(creditCard);
				creditCard.setUser(loggedOnUser);

				return true;
			} else {
				System.out
						.println("\nYou already have a credit card with " + creditCardNumber + " credit card number.");
				System.out.println("\n-----------------------------------------------------------------\n");
				return false;
			}
		} catch (NumberFormatException e) {
			System.out.println("\nError Occured!!! Credit Card number, limit and total debt shouldn't contain"
					+ " alphabetical characters!!!\nAdding Credit Card Operation Failed.");
			System.out.println("\n*****************************************************************\n");
			return false;
		}
	}

	private static void payCreditCardDebt() {
		try {
			System.out.print("Enter card number: ");
			int creditCardNumber = Integer.parseInt(scanner.nextLine());

			if (loggedOnUser.isCreditCardExists(creditCardNumber)) { //kart num ve cekilecek aacc num alıyor
				System.out.print("Enter account number: ");
				int accountNumber = Integer.parseInt(scanner.nextLine());

				if (loggedOnUser.isAcountExists(accountNumber)) {
					System.out.print("Enter amount: ");
					double amount = Double.parseDouble(scanner.nextLine());

					for (CreditCard creditCard : loggedOnUser.getCreditCardList()) {    //hashmap olsa get ile alıp getirip dönmeyecektim 01 big o nota burada n2
						if (creditCard.getCardNumber() == creditCardNumber) {
							for (Account account : loggedOnUser.getAccountList()) {
								if (account.getAccountNumber() == accountNumber) {
									boolean result = creditCard.payDebt(account, amount);
									if (result) {
										System.out.println("\nPayment operation successfully done.\n");
									} else {
										System.out.println("\nOperation Failed!!! Possible causes:");
										System.out.println(
		                                                                                                                                                                                            "1.Paying amount should be bigger than minimum debt (%25 of total debt).");
										System.out
												.println("2.Your account balance should be over minimum balance limit ("
														+ Account.MINIMUM_BALANCE_LIMIT
														+ ") after paying operation.\n");
									}
								}
							}
						}
					}
					return;
				} else {
					System.out.println("\nYou don't have an account with " + accountNumber + " account number.");
					System.out.println("\n-----------------------------------------------------------------\n");
					return;
				}
			} else {
				System.out.println("\nYou don't have a credit card with " + creditCardNumber + " card number.");
				System.out.println("\n-----------------------------------------------------------------\n");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("\nError Occured!!! Credit Card number and Account Number shouldn't contain"
					+ " alphabetical characters!!!\nPaying Credit Card Debt Operation Failed.");
			System.out.println("\n*****************************************************************\n");
			return;
		}
	}

	private static void eftOperation() {
		try {
			System.out.print("Enter sender account number: ");
			int senderAccountNumber = Integer.parseInt(scanner.nextLine());

			if (loggedOnUser.isAcountExists(senderAccountNumber)) {
				System.out.print("Enter receiver account number: ");
				int receiverAccountNumber = Integer.parseInt(scanner.nextLine());

				if (loggedOnUser.isAcountExists(receiverAccountNumber)) {
					System.out.print("Enter amount: ");
					double amount = Double.parseDouble(scanner.nextLine());

					for (Account senderAccount : loggedOnUser.getAccountList()) {
						if (senderAccount.getAccountNumber() == senderAccountNumber) {
							for (Account receiverAccount : loggedOnUser.getAccountList()) {
								if (receiverAccount.getAccountNumber() == receiverAccountNumber) {
									boolean result = senderAccount.eftTransaction(receiverAccount, amount);

									if (result) {
										System.out.println("\nEFT operation successfully done.\n");
									} else {
										System.out.println("Your account balance should be over minimum balance limit ("
												+ Account.MINIMUM_BALANCE_LIMIT + ") after EFT operation.\n");
									}
								}
							}
						}
					}

				} else {
					System.out
							.println("\nYou don't have an account with " + receiverAccountNumber + " account number.");
					System.out.println("EFT Operations can be made only between user's own accounts.");
					System.out.println("\n-----------------------------------------------------------------\n");
					return;
				}
			} else {
				System.out.println("\nYou don't have an account with " + senderAccountNumber + " account number.");
				System.out.println("\n-----------------------------------------------------------------\n");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("\nError Occured!!! Account Number shouldn't contain"
					+ " alphabetical characters!!!\nEFT Operation Failed.");
			System.out.println("\n*****************************************************************\n");
			return;
		}
	}

}
