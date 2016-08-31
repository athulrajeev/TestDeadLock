package test.deadlock;

public class AccountServiceImpl implements AccountService {

	public void transferAmount(Account toBeDebitedAccount, Account tobeCreditedAccount, int amount) {

		synchronized (toBeDebitedAccount) {

			System.out.println(Thread.currentThread().getName() + "--> Acquired lock for toBeDebitedAccount-->"
					+ toBeDebitedAccount.toString() + "wating to acquire lock for tobeCreditedAccount--->"
					+ tobeCreditedAccount);

			sleepForSomeTime();
			
			synchronized (tobeCreditedAccount) {
				toBeDebitedAccount.debitAmount(amount);
				tobeCreditedAccount.creditAmount(amount);
			}
		}

	}

	private void sleepForSomeTime() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
