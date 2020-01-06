
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreditCardCom {

    private String name;

    private List<Account> accounts;

    public CreditCardCom(String aName) {
        name = aName;
        accounts = new ArrayList<>();
    }


    public boolean setName(String aName) {
        name = aName;
        return true;
    }

    public String getName() {
        return name;
    }

    public Account getAccount(int index) {
        Account aAccount = accounts.get(index);
        return aAccount;
    }

    public List<Account> getAccounts() {
        List<Account> newAccounts = Collections.unmodifiableList(accounts);
        return newAccounts;
    }

    public int numberOfAccounts() {
        int number = accounts.size();
        return number;
    }

    public boolean hasAccounts() {
        boolean has = accounts.size() > 0;
        return has;
    }

    public int indexOfAccount(Account aAccount) {
        int index = accounts.indexOf(aAccount);
        return index;
    }

    public Account addAccount(int aCreditCard, int aBalance, Guardian aGuardian) {
        return new Account(aCreditCard, aBalance, this, aGuardian);
    }

    public boolean addAccount(Account aAccount) {
        boolean wasAdded = false;
        if (accounts.contains(aAccount)) {
            return false;
        }
        CreditCardCom existingCreditCompany = aAccount.getCreditCompany();
        boolean isNewCreditCompany = existingCreditCompany != null && !this.equals(existingCreditCompany);
        if (isNewCreditCompany) {
            aAccount.setCreditCompany(this);
        } else {
            accounts.add(aAccount);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeAccount(Account aAccount) {
        boolean wasRemoved = false;
        if (!this.equals(aAccount.getCreditCompany())) {
            accounts.remove(aAccount);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    //TODO

    public boolean validateCC(long ccNumber) {
        return ccNumber > 0 && Long.toString(ccNumber).length() == 16;
    }


    public boolean charge(long ccNumber, int balance) {
        return validateCC(ccNumber);
    }
}