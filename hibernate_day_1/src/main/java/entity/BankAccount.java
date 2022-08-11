package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Embeddable
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int bankAccountNumber;

    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;

    @ElementCollection
    @CollectionTable(name = "verification_documents", joinColumns = @JoinColumn(name = "id"))
    private List<Document> verificationDocument;

    @ElementCollection
    @CollectionTable(name = "fixed_deposits", joinColumns = @JoinColumn(name = "id"))
    private List<Savings> fixedDeposits;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "houseNumber", column = @Column(name = "client_HouseNumber", length = 20)),
            @AttributeOverride(name = "city", column = @Column(name = "client_city")),
            @AttributeOverride(name = "country", column = @Column(name = "client_country"))
    })
    private Address clientAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "houseNumber", column = @Column(name = "communicationAddress_HouseNumber", length = 20)),
            @AttributeOverride(name = "city", column = @Column(name = "communicationAddress_city")),
            @AttributeOverride(name = "country", column = @Column(name = "communicationAddress_country"))
    })
    private Address communicationAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "houseNumber", column = @Column(name = "branch_HouseNumber", length = 20)),
            @AttributeOverride(name = "city", column = @Column(name = "branch_city")),
            @AttributeOverride(name = "country", column = @Column(name = "branch_country"))
    })
    private Address branchAddress;
}
