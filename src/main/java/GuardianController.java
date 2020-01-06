public class GuardianController {

    private ChildController cController;
    private Guardian guardian;
    private Account account;
    private CCCompany ccCompany;

    public GuardianController() {
    }

    public void setcController(ChildController cController) {
        this.cController = cController;
    }

    public void createGuardian(int id, String name, int cc, AppUser au, Account a) {
        this.guardian = new Guardian(id, name, cc, a, au);
    }

    public void createAccount(int ccNumber, int bal, String comName) {
        CCCompany c = new CCCompany(comName);
        this.account = new Account(ccNumber, bal, c, guardian);
        this.guardian.setAccount(this.account);
    }


    public void addChildToGuardian(Child child) {
        this.guardian.addKid(child);
    }

    public boolean requestCCApproval(long ccNumber) {
        return ccCompany.validateCC(ccNumber);
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public Account getAccount() {
        return account;
    }

    public CCCompany getCcCompany() {
        return ccCompany;
    }

    public boolean checkMaxCharge(int price) {
        return account.getMaxPrice() <= account.getBalance()+price;
    }

    public void updatePayment(int price) {
        account.setBalance(price);

    }
}
