package newpackage;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name;

	private String surName;

	private int customerNumber;

	private String emailAddress;

	private String password;

	private String phoneNumber;

	private List<Account> accountList;

	private List<CreditCard> creditCardList;

	public User(String name, String surName, int customerNumber, String emailAddress, String password,
			String phoneNumber) {
		this.name = name;
		this.surName = surName;
		this.customerNumber = customerNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.accountList = new ArrayList<>();
		this.creditCardList = new ArrayList<>();
	}
	
	public boolean isAcountExists(int accountNumber) {
		for(Account account : accountList) {
			if(account.getAccountNumber() == accountNumber) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isCreditCardExists(int creditCardNumber) {
		for(CreditCard creditCard : creditCardList) {
			if(creditCard.getCardNumber() == creditCardNumber) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surName=" + surName + ", customerNumber=" + customerNumber + ", emailAddress="
				+ emailAddress + ", password=" + password + ", phoneNumber=" + phoneNumber + ", accountList="
				+ accountList + ", creditCardList=" + creditCardList + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<CreditCard> getCreditCardList() {
		return creditCardList;
	}

	public void setCreditCardList(List<CreditCard> creditCardList) {
		this.creditCardList = creditCardList;
	}

}
