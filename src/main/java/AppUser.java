
public class AppUser {

    private String userName;
    private String password;

    private Guardian guardian;

    public AppUser(String aUserName, String aPassword, Guardian aGuardian) {
        userName = aUserName;
        password = aPassword;
        if (aGuardian == null || aGuardian.getWebUser() != null) {
            throw new RuntimeException("Unable to create appUser due to aGuardian");
        }
        guardian = aGuardian;
    }

    public AppUser(String aUserName, String aPassword, int aIDForGuardian, String aNameForGuardian, int aCreditCardForGuardian, Account aAccountForGuardian) {
        userName = aUserName;
        password = aPassword;
        guardian = new Guardian(aIDForGuardian, aNameForGuardian, aCreditCardForGuardian, aAccountForGuardian, this);
    }

    public boolean setUserName(String aUserName) {
        userName = aUserName;
        return true;
    }

    public boolean setPassword(String aPassword) {
        password = aPassword;
        return true;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void delete() {
        Guardian existingGuardian = guardian;
        guardian = null;
        if (existingGuardian != null) {
            existingGuardian.delete();
        }
    }
}