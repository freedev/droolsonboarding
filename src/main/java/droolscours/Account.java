package droolscours;

public class Account {
	public Account() {}
	public Account(int _accountNo, double _balance) {
		this.accountNo = _accountNo;
		this.balance = _balance;
		
	}
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", balance=" + balance + "]";
	}
	private long accountNo;
	private double balance;
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
