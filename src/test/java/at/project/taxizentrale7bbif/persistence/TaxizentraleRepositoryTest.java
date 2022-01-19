package at.project.taxizentrale7bbif.persistence;

import at.project.taxizentrale7bbif.TestFixures;
import at.project.taxizentrale7bbif.domain.Adresse;
import at.project.taxizentrale7bbif.domain.Bundesland;
import at.project.taxizentrale7bbif.domain.Taxizentrale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJdbcTest
class TaxizentraleRepositoryTest {

    @Autowired //it wires it from repos to testrepos
    private TaxizentraleRepository repository;

    @Test
    void testTaxizentrale()
    {
        //given
        Bundesland vie = TestFixures.vie(); //created an object
                                            //created an address
                                            //linked the object to the address
        Adresse adresse = Adresse.builder()
                .strassennamen("Wienerstrasse")
                .plz(1011)
                .bundesland(vie)
                .build();

        Taxizentrale tz = Taxizentrale.builder() //created Taxizentrale
                .name("Weitz")
                .telefonnummer("+438476538")
                .adresse(adresse) //embedded address
                .kmPreis(5.5)
                .grundPreis(2)
                .build();
        //when && (save methode)
        var saved = repository.save(tz);

        //then
        assertThat(saved).isNotNull().isSameAs(tz); //isSameAs: are these two references pointing to the same location in memory
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getAdresse().getBundesland().getId()).isNotNull();

    }
}