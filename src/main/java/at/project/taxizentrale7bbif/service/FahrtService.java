package at.project.taxizentrale7bbif.service;

import at.project.taxizentrale7bbif.domain.Fahrt;
import at.project.taxizentrale7bbif.domain.Mitarbeiter;
import at.project.taxizentrale7bbif.persistence.FahrtRepository;
import at.project.taxizentrale7bbif.presentation.www.CreateFahrtForm;
import at.project.taxizentrale7bbif.presentation.www.CreateMitarbeiterForm;
import at.project.taxizentrale7bbif.service.dtos.MutateFahrtCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static at.project.taxizentrale7bbif.domain.QFahrt.fahrt;

@RequiredArgsConstructor
@Slf4j //logging
@Service
@Transactional
public class FahrtService {

    private final FahrtRepository fahrtRepository;
   // private final TemporalValueFactory temporalValueFactory;
   // private final TokenService tokenService;

    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Fahrt> getFahrten() {
        var fahrten = fahrtRepository.findAll();
        //System.out.println(fahrten.size() + " Fahrten wurden gefunden.");
        log.info("Es wurden {} Fahrten gefunden", fahrten.size()); //log statement
        return fahrten;
    }

    public Optional<Fahrt> getFahrt(Long id) { return fahrtRepository.findById(id); }

    public List<Fahrt> getFahrtByNummer(String nummer) {
        return fahrtRepository.findByNummer(nummer);
    }

    public Fahrt createFahrt(MutateFahrtCommand command) {
    Fahrt fahrt = Fahrt.builder()
            .nummer(command.getNummer())
            .taxifahrer(Mitarbeiter.builder()
                    .vorname(command.getVorname())
                    .nachname(command.getNachname())
                    .build())
            .build();
    return fahrtRepository.save(fahrt);

    }

    public Fahrt replaceFahrt(Long id, MutateFahrtCommand command) {
        Optional<Fahrt> entity = fahrtRepository.findById(id);

        if(entity.isPresent()) {
            Fahrt fahrt = entity.get();
            fahrt.setNummer(command.getNummer());
            fahrt.getTaxifahrer().setVorname(command.getVorname());
            fahrt.getTaxifahrer().setNachname(command.getNachname());

            return fahrtRepository.save(fahrt);
        }
        throw new IllegalStateException("Diese Fahrt mit der ID '" +id+ "' kann nicht gefunden werden.");
    }

    public Fahrt partiallyUpdateFahrt(Long id, MutateFahrtCommand command) {
        Optional<Fahrt> entity = fahrtRepository.findById(id);

        if(entity.isPresent()) {
            Fahrt fahrt = entity.get();

            if (command.getNummer() != null) fahrt.setNummer((command.getNummer()));
            if (command.getVorname() != null) fahrt.getTaxifahrer().setVorname(command.getVorname());
            if (command.getNachname() != null) fahrt.getTaxifahrer().setNachname(command.getNachname());

            return fahrtRepository.save(fahrt);
        }
        throw new IllegalStateException("Diese Fahrt mit der ID '" +id+ "' kann nicht gefunden werden.");
    }

    public Fahrt partiallyUpdateFahrt(Long id, Fahrt newFahrt) {
        Optional<Fahrt> entity = fahrtRepository.findById(id);

        if(entity.isPresent()) {
            Fahrt oldFahrt = entity.get();

            if (newFahrt.getNummer() != null) oldFahrt.setNummer((newFahrt.getNummer()));
            if (newFahrt.getTaxifahrer().getVorname() != null) oldFahrt.getTaxifahrer().setVorname(newFahrt.getTaxifahrer().getVorname());
            if (newFahrt.getTaxifahrer().getNachname() != null) oldFahrt.getTaxifahrer().setNachname(newFahrt.getTaxifahrer().getNachname());

            return fahrtRepository.save(oldFahrt);
        }
        throw new IllegalStateException("Diese Fahrt mit der ID '" +id+ "' kann nicht gefunden werden.");
    }

    public void deleteFahrten() { fahrtRepository.deleteAll(); }

    public void deleteFahrt(Long id) { fahrtRepository.deleteById(id);}


    private Fahrt _createFahrt(/*Optional<String> token,*/ MutateFahrtCommand command) {
        //LocalDateTime creationTS = temporalValueFactory.now();
       // String tokenValue = token.orElseGet(() -> tokenService.createNanoToken());
        Fahrt fahrt = Fahrt.builder()
                .nummer(command.getNummer())
                //.token(tokenValue)
                //.creationTS(creationTS)
                .taxifahrer(Mitarbeiter.builder()
                        .vorname(command.getVorname())
                        .nachname(command.getNachname())
                        .build())
                .build();
        return fahrtRepository.save(fahrt);
    }


    public void createFahrt(CreateFahrtForm newFahrtForm, CreateMitarbeiterForm newMitarbeiterForm) {
        Fahrt fahrt = Fahrt.builder()
                .nummer(newFahrtForm.getNummer())
                .taxifahrer(Mitarbeiter.builder()
                        .vorname(newMitarbeiterForm.getVorname())
                        .nachname(newMitarbeiterForm.getNachname())
                        .build())
                .build();

        fahrtRepository.save(fahrt);
    }
}