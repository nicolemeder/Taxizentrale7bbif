package at.project.taxizentrale7bbif.persistence;

import at.project.taxizentrale7bbif.domain.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long>{
}
