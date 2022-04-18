package at.project.taxizentrale7bbif.presentation.www;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateMitarbeiterForm {

    @NotBlank
    @Size(min = 3, max = 10)
    private String vorname;
    @NotBlank
    @Size(min = 2, max = 10)
    private String nachname;
}
