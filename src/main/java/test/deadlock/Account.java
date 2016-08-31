package test.deadlock;

public class Account {

	private final String name;
	private int balance;

	public Account(String name, int balance) {
		super();
		this.balance = balance;
		this.name = name;
	}

	public void creditAmount(int amount) {
		this.balance = this.balance + amount;
	}

	public void debitAmount(int amount) {
		this.balance = this.balance - amount;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", balance=" + balance + "]";
	}
}
