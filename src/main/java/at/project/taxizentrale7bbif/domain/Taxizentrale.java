package at.project.taxizentrale7bbif.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

import static at.project.taxizentrale7bbif.foundation.EnsurerFactory.when;

@Data //data class
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "taxizentralen")
public class Taxizentrale extends AbstractPersistable<Long> {
    //instead of a primary key (@id) we use AbstractPersi....

    @Version
    private Integer version;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
    @Size(min = 10, max = 10)
    private String telefonnummer;
    @Positive
    private Integer kmPreis;
    @Positive
    @Min(1)
    @Max(5)
    private Integer grundPreis;

    @Embedded //taxizentrale will also get the columns from the address class
    private Adresse adresse;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "zentrale")
    private List<Auto> autos = new ArrayList<>(5);

    public Taxizentrale(String name, String telefonnummer, int kmPreis, int grundPreis) {
        this.name = when(name, "name").isNotEmpty().isNotBlank().thenAssign();
        this.telefonnummer = when(telefonnummer, "telefonnummer").isNotEmpty().isNotBlank().thenAssign();
        this.kmPreis = kmPreis;
        this.grundPreis = grundPreis;
    }
    public  Taxizentrale addAutos(Auto...autos)
    {
        Objects.requireNonNull(autos,"Die AutoList darf nicht nul sein!");
        Arrays.stream(autos).forEach(this::addAuto);
        return this;
    }
    public Taxizentrale addAuto(Auto auto)
    {
        Objects.requireNonNull(auto, "Auto darf nicht null sein");
        if(auto.hasZentrale())
        {
            auto.getZentrale().removeAuto(auto);
        }
        autos.add(auto);
        auto.setZentrale(this);
        return this;
    }
    public Taxizentrale removeAutos(Auto...autos)
    {
        Objects.requireNonNull(autos,"Keine AutoListe zum entfernen!");
        Arrays.stream(autos).forEach(this::removeAuto);
        return this;
    }

    public Taxizentrale removeAuto(Auto auto)
    {
        Objects.requireNonNull(auto, "Kein Auto ausgewählt!");
        autos.remove(auto);
        auto.clearZentrale();
        return this;
    }
    public Taxizentrale clearAutos()
    {
        autos.clear();
        return this;
    }
    public List<Auto> getAutos()
    {
        return Collections.unmodifiableList(autos);
    }



}
