package stream.assignment;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class AccountServiceImpl implements AccountService {

    private static final String MEDICAL = "medical";

    private final AccountDAO accountDAO;

    public AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
    }

    @Override
    public void add(Account account) {
        accountDAO.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public void updateEmailByAccountNumber(int accountNumber, String newEmail) {
        List<Account> accounts = accountDAO.findAll();
        accounts.stream().filter(account -> account.accountNumber == accountNumber)
                .forEach(account -> account.getContactDetails().email = newEmail);
    }

    @Override
    public void displayAccountByMaxSumAssuredAmount() {
        List<Account> accounts = accountDAO.findAll();
        Map<Integer, Account> accountSumMap = new TreeMap<>();
        accounts.forEach(account -> {
            List<Policy> policies = account.getPolicies();
            if (nonNull(policies)) {
                policies.sort(Comparator.comparingInt(Policy::getSumAssuredAmount));
                int maxAmmountPerAccount = policies.get(policies.size() - 1).getSumAssuredAmount();
                accountSumMap.put(maxAmmountPerAccount, account);
            }
        });
        for (Map.Entry<Integer, Account> entry : accountSumMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Override
    public void updateMedicalPoliciesAmmount() {
        List<Account> accounts = accountDAO.findAll();
        accounts.forEach(account -> {
            List<Policy> policiesPerAccount = account.getPolicies();
            policiesPerAccount.stream()
                    .filter(policy -> policy.policyName.toLowerCase().contains(MEDICAL))
                    .forEach(policy -> policy.premiumAmount = calculateNewPremiumAmmount(policy));
        });
    }

    private int calculateNewPremiumAmmount(Policy policy) {
        return policy.premiumAmount + policy.premiumAmount / 10;
    }

    @Override
    public List<Account> filterByCriteria(FilterCriteria filterCriteria) {
        List<Account> dbAccounts = accountDAO.findAll();
        List<Account> accounts;
        if (shouldFilterByStateAndPolicy(filterCriteria)) {
            accounts = dbAccounts.stream()
                    .filter(account -> matchesStatePolicyCriteria(filterCriteria, account))
                    .collect(Collectors.toList());

        } else if (shouldFilterByStateAndBalanceAndPremiumAmount(filterCriteria)) {
            accounts = dbAccounts.stream()
                    .filter(account -> account.getBalance() > filterCriteria.getTotalBalance()
                            && account.getContactDetails().state.equals(filterCriteria.getState())
                            && matchesPremiumAmmount(filterCriteria, account))
                    .collect(Collectors.toList());

        } else {
            throw new RuntimeException("Invalid filter criteria.");
        }
        return accounts;
    }

    private boolean shouldFilterByStateAndBalanceAndPremiumAmount(FilterCriteria filterCriteria) {
        return nonNull(filterCriteria.getState()) && !filterCriteria.getState().isEmpty() && filterCriteria.getPremiumAmount() >= 0 && filterCriteria.getTotalBalance() >= 0;
    }

    private boolean shouldFilterByStateAndPolicy(FilterCriteria filterCriteria) {
        return nonNull(filterCriteria.getState())
                && !filterCriteria.getState().isEmpty()
                && nonNull(filterCriteria.getPolicyName())
                && !filterCriteria.getPolicyName().isEmpty();
    }

    private boolean matchesPremiumAmmount(FilterCriteria filterCriteria, Account account) {
        return account.getPolicies()
                .stream()
                .anyMatch(policy -> policy.getPremiumAmount() > filterCriteria.getPremiumAmount());
    }

    @Override
    public long countAccountsByPremiumDateOver() {
        return accountDAO.findAll().stream()
                .filter(containsInvalidPolicy()).count();
    }

    @Override
    public Set<String> sortAccountEmailsByNullPolicy() {
        return accountDAO.findAll()
                .stream()
                .filter(account -> isNull(account.getPolicies()))
                .map(account -> account.getContactDetails().email)
                .collect(Collectors.toSet());
    }

    private Predicate<Account> containsInvalidPolicy() {
        return account -> account.getPolicies()
                .stream()
                .anyMatch(policy -> policy.getPremiumDate().isBefore(LocalDate.now()));
    }

    private boolean matchesStatePolicyCriteria(FilterCriteria filterCriteria, Account account) {
        if (isNull(account.getPolicies())) {
            return false;
        }
        List<Policy> policies = account.getPolicies()
                .stream()
                .filter(policy -> policy.policyName.toLowerCase().contains(filterCriteria.getPolicyName().toLowerCase())
                        && isValidState(filterCriteria, account)).collect(Collectors.toList());
        return !policies.isEmpty();
    }

    private boolean isValidState(FilterCriteria filterCriteria, Account account) {
        return account.getContactDetails().state.toLowerCase().contains(filterCriteria.getState().toLowerCase());
    }
}
