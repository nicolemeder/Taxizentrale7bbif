package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "mitarbeiter")
public class Mitarbeiter extends AbstractPersistable<Long> {

    @Version
    private Integer version;
    @NotNull
    @NotBlank
    @NotEmpty
    private String vorname;
    private String nachname;
    @Past
    private LocalDate geburtsdatum;
    @Size(min = 10, max = 10)
    private String telefonnummer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Mitarbeiter_2_Auto"))
    private Auto auto;
}