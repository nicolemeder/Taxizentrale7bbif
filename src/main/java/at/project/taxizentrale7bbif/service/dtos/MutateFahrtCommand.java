package at.project.taxizentrale7bbif.service.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MutateFahrtCommand {
    private Integer nummer;
    private String vorname;
    private String nachname;
}
