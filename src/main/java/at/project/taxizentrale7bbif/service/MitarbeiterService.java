package at.project.taxizentrale7bbif.service;

import at.project.taxizentrale7bbif.domain.Fahrt;
import at.project.taxizentrale7bbif.domain.Mitarbeiter;
import at.project.taxizentrale7bbif.persistence.FahrtRepository;
import at.project.taxizentrale7bbif.persistence.MitarbeiterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MitarbeiterService {
    private final MitarbeiterRepository mitarbeiterRepository;

    public List<Mitarbeiter> getMitarbeiter() {
        var mitarbeiter = mitarbeiterRepository.findAll();
        return mitarbeiter;
    }

    public Optional<Mitarbeiter> getMitarbeiter(Long id) { return mitarbeiterRepository.findById(id); }

}
