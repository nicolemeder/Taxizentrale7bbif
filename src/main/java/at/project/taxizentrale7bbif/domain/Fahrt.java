package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "fahrten")
public class Fahrt extends AbstractPersistable<Long> {


    @Positive
    private Integer streckeInKm;
    private BigDecimal kosten;

    //@Embedded
    //private Adresse start;
    @Embedded
    private Adresse ziel;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Fahrt_2_Mitarbeiter"))
    private Mitarbeiter taxifahrer;


}