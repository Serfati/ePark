import java.util.HashSet;

public class Guardian {
    private Account account;
    final int ID;
    final String name;
    private AppUser appUser;

    public Guardian(int gID, String gName) {
        ChildController.kids = new HashSet<>();
        ID = gID;
        name = gName;
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
        AppUser exist = appUser;
        appUser = null;
        if (exist != null) exist.delete();
        ChildController.kids.removeIf(child -> child.getGuardian() == this);
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public HashSet<Child> getKids() {
        return ChildController.kids;
    }

    public int numberOfKids() {
        return ChildController.kids.size();
    }
}