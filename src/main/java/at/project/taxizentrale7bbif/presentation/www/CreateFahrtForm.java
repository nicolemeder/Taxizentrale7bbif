package at.project.taxizentrale7bbif.presentation.www;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor

public class CreateFahrtForm {
    @NotBlank
    @Size(min = 1, max = 20)
    private String nummer;

    private Long mitarbeiterId;

    @NotNull
    private MitarbeiterTyp mitarbeiterTyp;

    public enum MitarbeiterTyp {
        EXISTING,
        NEW;

        public boolean isExisting() { return this == EXISTING; }
    }


}
