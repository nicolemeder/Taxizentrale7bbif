package at.project.taxizentrale7bbif.persistence;

import at.project.taxizentrale7bbif.domain.Mitarbeiter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MitarbeiterRepositoryTest {

    @Autowired
    private MitarbeiterRepository mitarbeiterRepository;

    @Test
    void ensureSavingAMitarbeiterWorks(){
        //given
        Mitarbeiter ma = Mitarbeiter.builder()
                .vorname("Nico")
                .nachname("rose")
                .build();


        //when
        var saved  = mitarbeiterRepository.save(ma);

        //then
        assertThat(saved).isNotNull().isSameAs(ma);
        assertThat(saved.getId()).isNotNull();

    }



}