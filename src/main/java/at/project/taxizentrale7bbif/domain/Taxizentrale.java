package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data //data class
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "taxizentralen")
public class Taxizentrale extends AbstractPersistable<Long> {
                            //instead of a primary key (@id) we use AbstractPersi....
    private String name;
    private String telefonnummer;
    private double kmPreis;
    private int grundPreis;

    @Embedded //taxizentrale will also get the columns from the address class
    private Adresse adresse;
}
