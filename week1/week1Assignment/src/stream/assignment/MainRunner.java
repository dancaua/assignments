package stream.assignment;

import java.time.LocalDate;
import java.util.*;

import static java.util.Collections.singletonList;

public class MainRunner {
    public static final String NEW_EMAIL = "newEmail@gmail.com";

	/*   
	 *   Requirement (to be done by stream API only )
	 *   1. Update email on the basis of accountNumber  
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
	 * */

    public static void main(String[] args) {

        final AccountService accountService = new AccountServiceImpl();

        Policy p1 = new Policy(new Random().nextInt(6000), "Medical Policy-1", 2000, 1500000);
        Policy p2 = new Policy(new Random().nextInt(6000), "Car Policy-1", 2000, 1500000);
        Policy p3 = new Policy(new Random().nextInt(6000), "Medical Policy-2", 2500, 1800000);
        Policy p4 = new Policy(new Random().nextInt(6000), "Term Policy-1", 2000, 15000000);

        p1.setPremiumDate(LocalDate.of(2000, 3, 30));
        p2.setPremiumDate(LocalDate.of(2001, 3, 30));
        p3.setPremiumDate(LocalDate.of(2023, 3, 30));
        p4.setPremiumDate(LocalDate.of(2024, 3, 30));


        ContactDetails c1 = new ContactDetails("123-New Delhi", "Delhi", 1212122, "abc@gmail.com");
        ContactDetails c2 = new ContactDetails("454-New Delhi", "Delhi", 45412122, "xyz@gmail.com");
        ContactDetails c3 = new ContactDetails("A1-Gurgoan", "Delhi-NCR", 45412122, "a@gmail.com");
        ContactDetails c4 = new ContactDetails("A2-Gurgoan", "Delhi-NCR", 45412122, "b@gmail.com");
        ContactDetails c5 = new ContactDetails("A2-Gurgoan", "Delhi-NCR", 45412122, "alexd@gmail.com");

        accountService.add(addAccount("Ramesh", Arrays.asList(p1, p2, p3), 5000, c1));
        accountService.add(addAccount("Mike", Arrays.asList(p1, p4), 15000, c2));
        accountService.add(addAccount("Kirti", singletonList(p1), 25000, c3));
        accountService.add(addAccount("Ajay", singletonList(p4), 17000, c4));
        accountService.add(addAccount("Arti", singletonList(p3), 300000, c1));

        List<Account> accounts = accountService.findAll();
        printAccounts(accounts);

        final long accountByPremiumDateOver = accountService.countAccountsByPremiumDateOver();
        System.out.println("\nAccounts by premium date over: " + accountByPremiumDateOver + "\n");

        System.out.println("Updating account email by account number\n");
        accountService.updateEmailByAccountNumber(4, NEW_EMAIL);

        printAccounts(accountService.findAll());

        System.out.println("\n Displaying accounts by max sum assured amount\n");
        accountService.displayAccountByMaxSumAssuredAmount();

        System.out.println("\n Updating medical policies ammount\n");
        accountService.updateMedicalPoliciesAmmount();

        printAccounts(accountService.findAll());

        accountService.add(addAccount("Alex", null, 17000, c5));

        System.out.println("\n Finding emails by null policies\n");
        final Set<String> nullEmails = accountService.sortAccountEmailsByNullPolicy();
        System.out.println(nullEmails);


        final FilterCriteria statePolicyFilterCriteria = new FilterCriteria();
        statePolicyFilterCriteria.setState("Delhi");
        statePolicyFilterCriteria.setPolicyName("Medical");

        System.out.println("\n Accounts filtered by state and policy name\n");
        List<Account> accountsFilteredByStateAndPolicyName = accountService.filterByCriteria(statePolicyFilterCriteria);
        System.out.println(accountsFilteredByStateAndPolicyName);

        final FilterCriteria stateAmountBalanceFilterCriteria = new FilterCriteria();
        stateAmountBalanceFilterCriteria.setState("Delhi");
        stateAmountBalanceFilterCriteria.setPremiumAmount(2300);
        stateAmountBalanceFilterCriteria.setTotalBalance(26000);

        System.out.println("\n Accounts filtered by state and premium amount and balance\n");
        final List<Account> accountsFilteredByStateAndPremiumAmountAndBalance = accountService.filterByCriteria(stateAmountBalanceFilterCriteria);
        System.out.println(accountsFilteredByStateAndPremiumAmountAndBalance);
    }

    private static void printAccounts(List<Account> accounts) {
        for(Account account : accounts){
            System.out.println(account);
        }
    }

    public static Account addAccount(String name, List<Policy> policyList, int balance, ContactDetails contactDetails) {
        Account a = new Account();
        a.setAccountHolderName(name);
        a.setBalance(balance);
        a.setPolicies(policyList);
        a.setContactDetails(contactDetails);
        return a;
    }

}
