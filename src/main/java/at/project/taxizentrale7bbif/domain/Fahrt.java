package at.project.taxizentrale7bbif.domain;

import at.project.taxizentrale7bbif.foundation.TokenSourceProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "fahrten")
public class Fahrt extends AbstractPersistable<Long> implements TokenSourceProvider {



    @Positive
    private String nummer;
    private Integer streckeInKm;
    private BigDecimal kosten;

    //@Embedded
    //private Adresse start;
    @Embedded
    private Adresse ziel;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Fahrt_2_Mitarbeiter"))
    private Mitarbeiter taxifahrer;


    @Override
    public List<Supplier<String>> getTokenRelevantFields() {
        return List.of(
                this::getNummer,
                this.taxifahrer::getVorname,
                this.taxifahrer::getNachname
        );
    }


}