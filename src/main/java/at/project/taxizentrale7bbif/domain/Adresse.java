package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class Adresse {
    @NotNull
    @NotBlank
    @NotEmpty
    private String strassennamen;
    @Min(4)
    @Max(4)
    private Integer plz;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_Adresse_2_Bundesland"))
    private Bundesland bundesland;




}