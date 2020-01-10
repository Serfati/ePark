import java.util.HashSet;

public class Guardian {
    private Account account;
    private AppUser webUser;

    public Guardian(int aID, String aName, int aCreditCard) {
        ChildController.kids = new HashSet<>();
    }

    public void addKid(Child aKid) {
        if (ChildController.kids.contains(aKid)) return;
        Guardian existingGuardian = aKid.getGuardian();
        boolean isNewGuardian = existingGuardian != null && !this.equals(existingGuardian);
        if (isNewGuardian && ChildController.kids.size() <= 1) return;
        if (isNewGuardian) aKid.setGuardian(this);
        else ChildController.kids.add(aKid);
    }

    public boolean removeKid(Child aKid) {
        if (!this.equals(aKid.getGuardian())) return false;
        if (ChildController.kids.size() <= 1) return false;
        ChildController.kids.remove(aKid);
        return true;
    }

    public void delete() {
        Account existingAccount = account;
        account = null;
        if (existingAccount != null) existingAccount.delete();
        AppUser existingWebUser = webUser;
        webUser = null;
        if (existingWebUser != null) existingWebUser.delete();
        ChildController.kids.removeIf(child -> child.getGuardian() == this);
    }

    public Account getAccount() {
        return account;
    }

    public AppUser getWebUser() {
        return webUser;
    }

    public HashSet<Child> getKids() {
        return ChildController.kids;
    }

    public int numberOfKids() {
        return ChildController.kids.size();
    }
}