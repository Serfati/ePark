import java.util.List;

public class PayPal {
    private final String credit_company; // VISA or AMEX or MASTERCARD
    private List<Account> accounts; // list of all credit accounts of this. company

    public PayPal(String name) {
        this.credit_company = name;
    }

    public static boolean chargeCard() {
        return true;
    }

    public void addAccount(Account aAccount) {
        if (accounts.contains(aAccount)) return;
        PayPal existingCreditCompany = aAccount.getCreditCompany();
        boolean isNewCreditCompany = existingCreditCompany != null && !this.equals(existingCreditCompany);
        if (isNewCreditCompany) aAccount.setCreditCompany(this);
        else accounts.add(aAccount);
    }
    //TODO credit card handle

    public void removeAccount(Account aAccount) {
        if (!this.equals(aAccount.getCreditCompany()))
            accounts.remove(aAccount);

    }

    public boolean validateCC(long ccNumber) {
        return ccNumber > 0 && Long.toString(ccNumber).length() == 16;
    }

    public boolean validateCard() {
        return true;
    }

    public boolean validateThatThisCardIsAssignedToThisCompany() {
        return false;
    }
}