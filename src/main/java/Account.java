
public class Account {
    private int creditCard;
    private int balance;
    private int maxPrice;
    private CreditCardCom creditCompany;
    private Guardian guardian;

    public Account(int aCreditCard, int aBalance, CreditCardCom aCreditCompany, Guardian aGuardian) {
        creditCard = aCreditCard;
        balance = aBalance;
        maxPrice = aBalance;
        boolean didAddCreditCompany = setCreditCompany(aCreditCompany);
        if (!didAddCreditCompany) {
            throw new RuntimeException("Unable to create account due to creditCompany");
        }
        if (aGuardian == null || aGuardian.getAccount() != null) {
            throw new RuntimeException("Unable to create src.impl.Account due to aGuardian");
        }
        guardian = aGuardian;
    }

    public Account(int aCreditCard, int aBalance, CreditCardCom aCreditCompany, int aIDForGuardian, String aNameForGuardian, int aCreditCardForGuardian, AppUser aWebUserForGuardian) {
        creditCard = aCreditCard;
        balance = aBalance;
        boolean didAddCreditCompany = setCreditCompany(aCreditCompany);
        if (!didAddCreditCompany) {
            throw new RuntimeException("Unable to create account due to creditCompany");
        }
        guardian = new Guardian(aIDForGuardian, aNameForGuardian, aCreditCardForGuardian, this, aWebUserForGuardian);
    }

    public boolean setCreditCard(int aCreditCard) {
        boolean wasSet = false;
        creditCard = aCreditCard;
        wasSet = true;
        return wasSet;
    }

    public boolean setBalance(int aBalance) {
        boolean wasSet = false;
        balance = aBalance;
        wasSet = true;
        return wasSet;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public boolean addToBalance(int amount) {
        this.balance += amount;
        return true;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public int getBalance() {
        return balance;
    }

    public CreditCardCom getCreditCompany() {
        return creditCompany;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public boolean setCreditCompany(CreditCardCom aCreditCompany) {
        boolean wasSet = false;
        if (aCreditCompany == null) {
            return wasSet;
        }

        CreditCardCom existingCreditCompany = creditCompany;
        creditCompany = aCreditCompany;
        if (existingCreditCompany != null && !existingCreditCompany.equals(aCreditCompany)) {
            existingCreditCompany.removeAccount(this);
        }
        creditCompany.addAccount(this);
        wasSet = true;
        return wasSet;
    }

    public void delete() {
        CreditCardCom placeholderCreditCompany = creditCompany;
        this.creditCompany = null;
        if (placeholderCreditCompany != null) {
            placeholderCreditCompany.removeAccount(this);
        }
        Guardian existingGuardian = guardian;
        guardian = null;
        if (existingGuardian != null) {
            existingGuardian.delete();
        }
    }

    public boolean removeFromBalance(int amount) {
        if (amount <= balance) {
            balance = -amount;
            return true;
        } else {
            return false;
        }
    }
}