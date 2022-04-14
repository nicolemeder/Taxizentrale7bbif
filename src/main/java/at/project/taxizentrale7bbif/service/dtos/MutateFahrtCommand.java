package at.project.taxizentrale7bbif.service.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MutateFahrtCommand {
    private String nummer;
    private String vorname;
    private String nachname;
}
