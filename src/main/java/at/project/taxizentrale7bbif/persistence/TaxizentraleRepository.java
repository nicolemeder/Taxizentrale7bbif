package at.project.taxizentrale7bbif.persistence;

import at.project.taxizentrale7bbif.domain.Taxizentrale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaxizentraleRepository extends JpaRepository<Taxizentrale, Long>/*,QuerydslPredicateExecutor<Taxizentrale>*/ {
                                                                                    //dynamic queries
                                                                                    //couldn't find QTaxizentrale-->error
    Optional<Taxizentrale> findByName(String name);

    List<Taxizentrale> findByNameLike(String name);

}
