package test.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BankTest {

	public static void main(String[] args) {
		UserAccount userAccount = new UserAccount("zx", 100);
		DepositHandler dpHandler = new DepositHandler(userAccount, 10);
		DepositHandler dpHandler1 = new DepositHandler(userAccount, 20);
		DepositHandler dpHandler2 = new DepositHandler(userAccount, 30);
		DepositHandler dpHandler3 = new DepositHandler(userAccount, 40);
		DrawHandler drHandler = new DrawHandler(userAccount, 10);
		DrawHandler drHandler1 = new DrawHandler(userAccount, 90);
		DrawHandler drHandler2 = new DrawHandler(userAccount, 100);
		DrawHandler drHandler3 = new DrawHandler(userAccount, 30);
		DrawHandler drHandler4 = new DrawHandler(userAccount, 30);
		/*Thread depositThread = new Thread(dpHandler);
		Thread depositThread1 = new Thread(dpHandler1);
		Thread depositThread2 = new Thread(dpHandler2);
		Thread depositThread3 = new Thread(dpHandler3);
		Thread drawThread = new Thread(drHandler));
		Thread drawThread1 = new Thread(drHandler1);
		Thread drawThread2 = new Thread(drHandler2);
		Thread drawThread3 = new Thread(drHandler3);
		Thread drawThread4 = new Thread(drHandler4);
		depositThread.start();
		depositThread1.start();
		depositThread2.start();
		depositThread3.start();
		drawThread.start();
		drawThread1.start();
		drawThread2.start();
		drawThread3.start();
		drawThread4.start();*/
		
		// 
		final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 30, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(5));
		poolExecutor.execute(dpHandler);
		poolExecutor.execute(dpHandler1);
		poolExecutor.execute(dpHandler2);
		poolExecutor.execute(dpHandler3);
		poolExecutor.execute(drHandler);
		poolExecutor.execute(drHandler1);
		poolExecutor.execute(drHandler2);
		poolExecutor.execute(drHandler3);
		poolExecutor.execute(drHandler4);
		
		// query the threadpool's info
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int waitSize = -1;
				while (true) {
					waitSize = poolExecutor.getQueue().size();
					System.out.println("The pool size is :" + poolExecutor.getPoolSize()+"; there are " +
							waitSize+" wating to be executed, and " + poolExecutor.getCompletedTaskCount() 
							+ " are already executed.");
					if (waitSize == 0) break;
					
				}
			}
		}).start();
		// 
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("-------query-------" + userAccount.getAccount() + "'s balance is:" + userAccount.getBalance());
			System.out.print(poolExecutor.getActiveCount());
		}
		
	}
	
	class MyThread extends Thread {
		
	}

}
