
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CCCompany {

    private String name;

    private List<Account> accounts;

    public CCCompany(String aName) {
        name = aName;
        accounts = new ArrayList<Account>();
    }

    public static int minimumNumberOfAccounts() {
        return 0;
    }

    public boolean setName(String aName) {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
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
        CCCompany existingCreditCompany = aAccount.getCreditCompany();
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
        //Unable to remove aAccount, as it must always have a creditCompany
        if (!this.equals(aAccount.getCreditCompany())) {
            accounts.remove(aAccount);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    /* Code from template association_AddIndexControlFunctions */
    public boolean addAccountAt(Account aAccount, int index) {
        boolean wasAdded = false;
        if (addAccount(aAccount)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfAccounts()) {
                index = numberOfAccounts()-1;
            }
            accounts.remove(aAccount);
            accounts.add(index, aAccount);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveAccountAt(Account aAccount, int index) {
        boolean wasAdded = false;
        if (accounts.contains(aAccount)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfAccounts()) {
                index = numberOfAccounts()-1;
            }
            accounts.remove(aAccount);
            accounts.add(index, aAccount);
            wasAdded = true;
        } else {
            wasAdded = addAccountAt(aAccount, index);
        }
        return wasAdded;
    }

    public void delete() {
        for(int i = accounts.size(); i > 0; i--) {
            Account aAccount = accounts.get(i-1);
            aAccount.delete();
        }
    }

    public boolean validateCC(long ccNumber) {
        return ccNumber > 0 && Long.toString(ccNumber).length() == 16;
    }

    public boolean charge(long ccNumber, int balance) {
        return validateCC(ccNumber);
    }
}