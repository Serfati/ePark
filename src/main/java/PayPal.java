import java.util.HashMap;
import java.util.LinkedList;

public class PayPal {
    public static final HashMap<String, PayPal> companies = new HashMap<>();
    final String credit_company; //VISA or AMEX or MASTERCARD
    private final LinkedList<Account> accounts; //list of all credit accounts of this. company

    public PayPal(String name) {
        this.credit_company = name;
        accounts = new LinkedList<>();
    }

    public static boolean chargeCard(Account account) {
        return account.getBalance() > 0;
    }

    public void addAccount(Account aAccount) {
        if (accounts.contains(aAccount)) return;
        PayPal existingCreditCompany = aAccount.getCreditCompany();
        boolean isNewCreditCompany = existingCreditCompany != null && !this.equals(existingCreditCompany);
        if (isNewCreditCompany) aAccount.setCreditCompany(this);
        else accounts.add(aAccount);
    }

    public void removeAccount(Account aAccount) {
        if (!this.equals(aAccount.getCreditCompany()))
            accounts.remove(aAccount);
    }

    public static boolean validationAndBalanceCheck(String ccNumber, int creditLimit, int firstNumber) {
        if (creditLimit < 10 || creditLimit > 1000000) return false;
        if (firstNumber < 3 || firstNumber > 5) return false;
        return ccNumber.matches("[0-9]+") && ccNumber.length() > 3;
    }
}