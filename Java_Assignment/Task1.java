// Task1.java
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task1 {

    public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {
        // FIX: 1 — result was null; initialised to ArrayList to prevent NullPointerException on result.add()
        List<LoanAccount> result = new ArrayList<>();

        // FIX: 2 — accounts list itself may be null; guard prevents NullPointerException in for-each
        if (accounts == null) {
            return result;
        }

        for (LoanAccount account : accounts) {
            // FIX: 3 — dueDate may be null for restructured accounts; skip those accounts
            if (account.getDueDate() != null && account.getDueDate().before(new Date())) {
                if (account.getOutstandingBalance() > 0) {
                    result.add(account);
                }
            }
        }
        return result;
    }
}