package at.project.taxizentrale7bbif.domain;

import at.project.taxizentrale7bbif.persistence.converters.TaxiTypConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "autos")
public class Auto extends AbstractPersistable<Long> {

    @PastOrPresent
    private Year baujahr;
    @Positive
    private int kmStand;
    private BigInteger einnahmen;
    @Future
    private LocalDate serviceFaelligkeitsdatum;
    @Positive
    private int ps;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Auto_2_Taxizentrale"))
    private Taxizentrale zentrale;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Auto_2_Marke"))
    private Marke marke;


    //@Enumerated(EnumType.STRING)
    @Convert(converter = TaxiTypConverter.class) //converts it to a database value
    @Column(columnDefinition = "char(1) check (taxityp in ('N', 'B', 'G', 'V')))") //check constraint
    private TaxiTyp taxiTyp;


    public boolean hasZentrale()
    {
        return this.zentrale != null;
    }

    public Auto clearZentrale()
    {
        this.zentrale = null;
        return this;
    }


}
