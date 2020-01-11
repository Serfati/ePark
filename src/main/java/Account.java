
public class Account {
    private int balance;
    private PayPal creditCompany;
    private Guardian guardian;

    public Account(int aCreditCard, int aBalance, PayPal aCreditCompany, Guardian aGuardian) {
        balance = aBalance;
        setCreditCompany(aCreditCompany);
        if (aGuardian == null || aGuardian.getAccount() != null)
            throw new RuntimeException();
        guardian = aGuardian;
    }

    public Account(int aCreditCard, PayPal aCreditCompany, Guardian aGuardian) {
        balance = 100;
        setCreditCompany(aCreditCompany);
        if (aGuardian == null || aGuardian.getAccount() != null)
            throw new RuntimeException();
        guardian = aGuardian;
    }

    public void addToBalance(int amount) {
        this.balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public PayPal getCreditCompany() {
        return creditCompany;
    }

    public void setCreditCompany(PayPal aCreditCompany) {
        if (aCreditCompany == null) return;
        PayPal existingCreditCompany = creditCompany;
        creditCompany = aCreditCompany;
        if (existingCreditCompany != null && !existingCreditCompany.equals(aCreditCompany))
            existingCreditCompany.removeAccount(this);
        creditCompany.addAccount(this);
    }

    public void delete() {
        PayPal placeholderCreditCompany = creditCompany;
        this.creditCompany = null;
        if (placeholderCreditCompany != null) placeholderCreditCompany.removeAccount(this);
        Guardian existingGuardian = guardian;
        guardian = null;
        if (existingGuardian != null) existingGuardian.delete();
    }

    public void removeFromBalance(int amount) {
        if (amount <= balance)
            balance = -amount;
    }

    public Guardian getGuardian() {
        return guardian;
    }
}