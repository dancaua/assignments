package stream.assignment;

import java.util.List;
import java.util.Set;

/*
         1. Update email on the basis of accountNumber
	 *   2. display Account based on maximum sumAssuredAmount
	 *   3. update premium amount of all medical policies by 10%
	 *   4. filter accounts based on following
	 *      4.a) state and policyname
	 *      4.b) state and premiumAmount (higher than certain amount) and total balance
	 *   5. count accounts where premium date is over
	          Note :- use setter method to Set premium date
			     LocalDate.of()  // is used to create date
				 LocalDate.now() // is used to get current date
	 *   6. Store account's email in a Set<String> where policy is null
	 *
	 *   Note:- Add couple of more records based on requirement
 */
public interface AccountService {
    void add(Account account);

    List<Account> findAll();

    void updateEmailByAccountNumber(int accountNumber, String newEmail);

    void displayAccountByMaxSumAssuredAmount();

    void updateMedicalPoliciesAmmount();

    List<Account> filterByCriteria(FilterCriteria filterCriteria);

    long countAccountsByPremiumDateOver();

    Set<String> sortAccountEmailsByNullPolicy();
}
