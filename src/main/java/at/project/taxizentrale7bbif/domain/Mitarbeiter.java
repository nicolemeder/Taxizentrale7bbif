package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "mitarbeiter")
public class Mitarbeiter extends AbstractPersistable<Long> {

    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;
    private int telefonnummer;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Auto auto;
}
