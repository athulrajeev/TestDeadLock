package org.gradle;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import test.deadlock.Account;
import test.deadlock.AccountService;
import test.deadlock.AccountServiceImpl;

public class AccountServiceImplTest {

	@Test
	public void test_RecreateDeadLock() throws InterruptedException {
		// Arrange
		Account tomsAccount = new Account("Tom", 100);
		Account harrysAccount = new Account("Harry", 50);
		AccountServiceImpl service = new AccountServiceImpl();
		final int concurrentThreads = 2;
		final CountDownLatch startLatch = new CountDownLatch(concurrentThreads);
		final CountDownLatch endLatch = new CountDownLatch(concurrentThreads);

		// Act
		Thread t1 = new Thread(getRunnable(tomsAccount, harrysAccount, service, 10, startLatch, endLatch));
		t1.start();
		startLatch.countDown();

		Thread t2 = new Thread(getRunnable(harrysAccount, tomsAccount, service, 10, startLatch, endLatch));
		t2.start();
		startLatch.countDown();

		endLatch.await();
	}

	private Runnable getRunnable(Account a1, Account a2, AccountService service, int amount, CountDownLatch startLatch,
			CountDownLatch endLatch) {

		return () -> {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			service.transferAmount(a1, a2, amount);
			endLatch.countDown();
		};
	}

}
