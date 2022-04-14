package at.project.taxizentrale7bbif.persistence;

import at.project.taxizentrale7bbif.domain.Fahrt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FahrtRepository extends JpaRepository<Fahrt, Long> {
}
