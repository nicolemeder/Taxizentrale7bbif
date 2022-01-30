package at.project.taxizentrale7bbif.persistence;

import at.project.taxizentrale7bbif.domain.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long>{
    List<Auto> findAllByMarke_Name(String name);
}

//in the making