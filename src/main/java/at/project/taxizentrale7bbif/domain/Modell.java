package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "modelle")
public class Modell extends AbstractPersistable<Long> {

    private String name;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Marke marke;
}
