package stream.assignment;

import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    private final List<Account> accounts;

    public AccountDAOImpl() {
        accounts = new ArrayList<>();
    }

    public void save(Account account) {
        accounts.add(account);
        account.setAccountNumber(accounts.size());
    }

    public List<Account> findAll() {
        return accounts;
    }
}
