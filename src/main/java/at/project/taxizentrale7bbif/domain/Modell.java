package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "modelle")
public class Modell extends AbstractPersistable<Long> {

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Modell_2_Marke"))
    private Marke marke;




}