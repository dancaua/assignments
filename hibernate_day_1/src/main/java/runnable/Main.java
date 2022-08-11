package runnable;

import entity.Address;
import entity.BankAccount;
import entity.Document;
import entity.Savings;
import org.hibernate.Session;
import util.SessionFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) {
        Session hibernate = SessionFactoryUtil.getFactory().openSession();
        new Main().doSave(hibernate);
    }

    public void doSave(Session session) {
        session.beginTransaction();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountHolderName("test");

        Document d1 = new Document();
        d1.setDocumentNumber("1");
        d1.setDocumentName("ID");

        Document d2 = new Document();
        d2.setDocumentNumber("2");
        d2.setDocumentName("Passport");

        List<Document> documents = new ArrayList<>(Arrays.asList(d1, d2));

        bankAccount.setVerificationDocument(documents);

        Savings s1 = new Savings();
        s1.setSavingName("saving 1");
        s1.setMaturityDate(LocalDate.now());
        s1.setDepositAmount(100);
        s1.setAnnualInterest(1.0f);
        s1.setSavingDate(LocalDate.now());

        Savings s2 = new Savings();
        s2.setSavingName("saving 2");
        s2.setMaturityDate(LocalDate.now());
        s2.setDepositAmount(200);
        s2.setAnnualInterest(2.0f);
        s2.setSavingDate(LocalDate.now());

        List<Savings> savings = new ArrayList<>(Arrays.asList(s1, s2));

        bankAccount.setFixedDeposits(savings);

        Address clientAddress = new Address();
        Address communicationAddress = new Address();
        Address branchAddress = new Address();

        clientAddress.setCountry("Romania");
        clientAddress.setCity("Bucharest");
        clientAddress.setHouseNumber("1a");

        communicationAddress.setCountry("India");
        communicationAddress.setCity("New Delhi");
        communicationAddress.setHouseNumber("2a");

        branchAddress.setCountry("France");
        branchAddress.setCity("Paris");
        branchAddress.setHouseNumber("3a");

        bankAccount.setBranchAddress(branchAddress);
        bankAccount.setClientAddress(clientAddress);
        bankAccount.setCommunicationAddress(communicationAddress);

        session.save(bankAccount);
        session.getTransaction().commit();

        List<BankAccount> objects = entityManager.createQuery("select b from BankAccount b")
                .setHint("org.hibernate.cacheable", true)
                .getResultList();

        System.out.println(objects.size() + " bank accounts received from cache");
    }
}
