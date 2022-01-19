package at.project.taxizentrale7bbif.persistence;

import at.project.taxizentrale7bbif.domain.Bundesland;
import at.project.taxizentrale7bbif.domain.Taxizentrale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundeslandRepository extends JpaRepository<Bundesland, Long> {


}
