
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Guardian {

    private int ID;
    private String name;
    private int creditCard;

    private Account account;
    private AppUser webUser;
    private List<Child> kids;


    public Guardian(int aID, String aName, int aCreditCard, Account aAccount, AppUser aWebUser) {
        ID = aID;
        name = aName;
        creditCard = aCreditCard;
        if (aAccount == null || aAccount.getGuardian() != null) {
            throw new RuntimeException("Unable to create Guardian due to aAccount");
        }
        account = aAccount;
        if (aWebUser == null || aWebUser.getGuardian() != null) {
            throw new RuntimeException("Unable to create Guardian due to aWebUser");
        }
        webUser = aWebUser;
        kids = new ArrayList<>();
    }

    public Guardian(int aID, String aName, int aCreditCard, int aCreditCardForAccount, int aBalanceForAccount, CCCompany aCreditCompanyForAccount, String aUserNameForWebUser, String aPasswordForWebUser) {
        ID = aID;
        name = aName;
        creditCard = aCreditCard;
        account = new Account(aCreditCardForAccount, aBalanceForAccount, aCreditCompanyForAccount, this);
        webUser = new AppUser(aUserNameForWebUser, aPasswordForWebUser, this);
        kids = new ArrayList<>();
    }

    public Guardian(int aID, String aName, int aCreditCard) {
        ID = aID;
        name = aName;
        creditCard = aCreditCard;
        kids = new ArrayList<>();
    }

    public static int minimumNumberOfKids() {
        return 1;
    }

    public boolean setID(int aID) {
        boolean wasSet = false;
        ID = aID;
        wasSet = true;
        return wasSet;
    }

    public boolean setName(String aName) {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    public boolean setCreditCard(int aCreditCard) {
        boolean wasSet = false;
        creditCard = aCreditCard;
        wasSet = true;
        return wasSet;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account newAccount) {
        account = newAccount;
    }

    public AppUser getWebUser() {
        return webUser;
    }

    public void setWebUser(AppUser webUser) {
        this.webUser = webUser;
    }

    public Child getKid(int index) {
        Child aKid = kids.get(index);
        return aKid;
    }

    public List<Child> getKids() {
        List<Child> newKids = Collections.unmodifiableList(kids);
        return newKids;
    }

    public int numberOfKids() {
        int number = kids.size();
        return number;
    }

    public boolean hasKids() {
        boolean has = kids.size() > 0;
        return has;
    }

    public int indexOfKid(Child aKid) {
        int index = kids.indexOf(aKid);
        return index;
    }

    public boolean isNumberOfKidsValid() {
        boolean isValid = numberOfKids() >= minimumNumberOfKids();
        return isValid;
    }

    public Child addKid(int aID, String aName, int aHeight, int aWeight, int aAge, boolean aRidingDevice, eTicket aETicket, Device aDevice) {
        Child aNewKid = new Child(aID, aName, aHeight, aWeight, aAge, aRidingDevice, aETicket, aDevice, this);
        return aNewKid;
    }

    public boolean addKid(Child aKid) {
        boolean wasAdded = false;
        if (kids.contains(aKid)) {
            return false;
        }
        Guardian existingGuardian = aKid.getGuardian();
        boolean isNewGuardian = existingGuardian != null && !this.equals(existingGuardian);

        if (isNewGuardian && existingGuardian.numberOfKids() <= minimumNumberOfKids()) {
            return wasAdded;
        }
        if (isNewGuardian) {
            aKid.setGuardian(this);
        } else {
            kids.add(aKid);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeKid(Child aKid) {
        boolean wasRemoved = false;
        //Unable to remove aKid, as it must always have a guardian
        if (!this.equals(aKid.getGuardian())) {
            return wasRemoved;
        }

        //guardian already at minimum (1)
        if (numberOfKids() <= minimumNumberOfKids()) {
            return wasRemoved;
        }

        kids.remove(aKid);
        wasRemoved = true;
        return wasRemoved;
    }

    public boolean addKidAt(Child aKid, int index) {
        boolean wasAdded = false;
        if (addKid(aKid)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfKids()) {
                index = numberOfKids()-1;
            }
            kids.remove(aKid);
            kids.add(index, aKid);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveKidAt(Child aKid, int index) {
        boolean wasAdded = false;
        if (kids.contains(aKid)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfKids()) {
                index = numberOfKids()-1;
            }
            kids.remove(aKid);
            kids.add(index, aKid);
            wasAdded = true;
        } else {
            wasAdded = addKidAt(aKid, index);
        }
        return wasAdded;
    }

    public void delete() {
        Account existingAccount = account;
        account = null;
        if (existingAccount != null) {
            existingAccount.delete();
        }
        AppUser existingWebUser = webUser;
        webUser = null;
        if (existingWebUser != null) {
            existingWebUser.delete();
        }
        for(int i = kids.size(); i > 0; i--) {
            Child aKid = kids.get(i-1);
            aKid.delete();
        }
    }
}