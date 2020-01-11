
public class Account {
    private int balance;
    private PayPal creditCompany;
    private Guardian guardian;
    final int creditCard;

    public Account(int aBalance, int aCreditCard, PayPal aCreditCompany, Guardian aGuardian) {
        balance = Math.max(aBalance, 100);
        setCreditCompany(aCreditCompany);
        creditCard = aCreditCard;
        if (aGuardian == null || aGuardian.getAccount() != null)
            throw new RuntimeException();
        guardian = aGuardian;
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

    public boolean removeFromBalance(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
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
}