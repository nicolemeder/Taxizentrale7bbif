package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class Adresse {
    private String strassennamen;
    private int plz;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Bundesland bundesland;



}
