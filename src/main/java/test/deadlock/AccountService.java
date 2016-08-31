package test.deadlock;

public interface AccountService {

	public void transferAmount(Account toBeDebitedAccount, Account tobeCreditedAccount, int amount);

}
