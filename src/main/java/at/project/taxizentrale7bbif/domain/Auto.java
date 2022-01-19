package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "autos")
public class Auto extends AbstractPersistable<Long> {

    private int baujahr;
    private int kmStand;
    private BigInteger einnahmen;
    private LocalDate serviceFaelligkeitsdatum;
    private int ps;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Taxizentrale zentrale;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Marke marke;



    @Enumerated(EnumType.STRING)
    private TaxiTyp taxiTyp;






}
