package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "fahrten")
public class Fahrt extends AbstractPersistable<Long> {

    private int laengeInKm;
    private BigInteger kosten;
    
    @Embedded
    private Adresse start;
    @Embedded
    private Adresse ziel;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Mitarbeiter taxifahrer;


}
