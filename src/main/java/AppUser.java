public class AppUser {
    private final String userName;
    private final String password;
    private Guardian guardian;

    public AppUser(String aUserName, String aPassword, Guardian aGuardian) {
        userName = aUserName;
        password = aPassword;
        if (aGuardian == null || aGuardian.getAppUser() != null) throw new RuntimeException();
        guardian = aGuardian;
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
        if (existingGuardian != null) existingGuardian.delete();
    }
}