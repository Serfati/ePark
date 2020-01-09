public class GuardianController {

    private ChildController cController;
    private Guardian guardian;
    private Account account;
    private CreditCardCom ccCompany;

    public GuardianController() {
    }

    public void setcController(ChildController cController) {
        this.cController = cController;
    }

    public void createGuardian(int id, String name, int cc, AppUser au, Account a) {
        this.guardian = new Guardian(id, name, cc, a, au);
    }

    public void createAccount(int ccNumber, int bal, String comName) {
        CreditCardCom c = new CreditCardCom(comName);
        this.account = new Account(ccNumber, bal, c, guardian);
        this.guardian.setAccount(this.account);
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

    public CreditCardCom getCcCompany() {
        return ccCompany;
    }

    public boolean checkMaxCharge(int price) {
        return account.getMaxPrice() <= account.getBalance()+price;
    }

    public void updatePayment(int price) {
        account.setBalance(price);

    }
}
