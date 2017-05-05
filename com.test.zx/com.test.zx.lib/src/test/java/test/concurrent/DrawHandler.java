package test.concurrent;

public class DrawHandler implements Runnable {

	private UserAccount userAccount;
	private double money;
	
	public DrawHandler(UserAccount userAccount, double money) {
		this.userAccount = userAccount;
		this.money = money;
	}
	
	@Override
	public void run() {
		synchronized (userAccount) {
			while ((userAccount.getBalance() - money) < 0) {
				try {
					userAccount.wait(); // 余额不足时等待存入
					System.out.println("draw money: " + money + ", The balance is almost empty, please wait");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			userAccount.setBalance(userAccount.getBalance() - money);
			System.out.println("draw money: " + money + "; the balance is: " + userAccount.getBalance());
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
