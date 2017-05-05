package test.concurrent;

public class UserAccount {
	private String account;
	private double balance;
	
	public UserAccount(String account, double balance) {
		this.account = account;
		this.balance = balance;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
