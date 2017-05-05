package test.concurrent;

public class DepositHandler implements Runnable {

	private UserAccount userAccount;
	private double money;
	
	public DepositHandler(UserAccount userAccount, double money) {
		this.userAccount = userAccount;
		this.money = money;
	}
	
	@Override
	public void run() {
		synchronized(userAccount) {
			while ((userAccount.getBalance() + money) > 100) {
				try {
					userAccount.wait(); // 余额最大只能为100
					System.out.println("deposit money: " + money + ", The balance is full please wait");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			userAccount.setBalance(userAccount.getBalance() + money);
			System.out.println("deposit money: " + money + "; the balance is: " + userAccount.getBalance());
			userAccount.notifyAll();
		}
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
}
