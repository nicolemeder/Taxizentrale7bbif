package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

import static at.project.taxizentrale7bbif.foundation.EnsurerFactory.when;

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
    private int kmPreis;
    private int grundPreis;

    @Embedded //taxizentrale will also get the columns from the address class
    private Adresse adresse;

    public Taxizentrale(String name, String telefonnummer, int kmPreis, int grundPreis)
    {
        this.name = when(name, "name").isNotEmpty().isNotBlank().thenAssign();
        this.telefonnummer = when(telefonnummer, "telefonnummer").isNotEmpty().isNotBlank().thenAssign();
    }
}
