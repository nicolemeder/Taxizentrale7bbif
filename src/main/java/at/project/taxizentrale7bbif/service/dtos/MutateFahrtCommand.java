package at.project.taxizentrale7bbif.service.dtos;


import at.project.taxizentrale7bbif.foundation.TokenSourceProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Supplier;

@Getter
@NoArgsConstructor
public class MutateFahrtCommand implements TokenSourceProvider {
    private String nummer;
    private String vorname;
    private String nachname;


@Override
public List<Supplier<String>> getTokenRelevantFields() {
    return List.of(
            this::getNummer,
            this::getVorname,
            this::getNachname
    );
}




}
