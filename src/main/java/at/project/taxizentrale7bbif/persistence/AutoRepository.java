package at.project.taxizentrale7bbif.persistence;

import at.project.taxizentrale7bbif.domain.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long>{
}
