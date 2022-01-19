package at.project.taxizentrale7bbif.persistence;

import at.project.taxizentrale7bbif.TestFixures;
import at.project.taxizentrale7bbif.domain.Bundesland;
import at.project.taxizentrale7bbif.domain.Taxizentrale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class BundeslandRepositoryTest {

        @Autowired //it wires it from repos to testrepos
        private BundeslandRepository repository;

        @Test
        void testBundesland()
        {
            //given
            Bundesland vie = TestFixures.vie();


            //when && (save methode)
            var saved = repository.save(vie);

            //then
            assertThat(saved).isNotNull().isSameAs(vie); //are these two references pointing to the same location in memory
            assertThat(saved.getId()).isNotNull();

        }

}