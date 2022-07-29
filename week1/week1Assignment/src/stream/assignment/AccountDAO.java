package stream.assignment;

import java.util.ArrayList;
import java.util.List;

public interface AccountDAO {
    void save(Account account);
    List<Account> findAll();
}
