package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Embeddable;
import java.time.LocalDate;


@Data
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Savings {
    private LocalDate savingDate;
    private String savingName;
    private int depositAmount;
    private float annualInterest;
    private LocalDate maturityDate;
}
