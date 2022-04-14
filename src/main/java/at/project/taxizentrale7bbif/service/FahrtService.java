package at.project.taxizentrale7bbif.service;

import at.project.taxizentrale7bbif.domain.Fahrt;
import at.project.taxizentrale7bbif.domain.Mitarbeiter;
import at.project.taxizentrale7bbif.persistence.FahrtRepository;
import at.project.taxizentrale7bbif.service.dtos.MutateFahrtCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class FahrtService {

    private final FahrtRepository fahrtRepository;
   // private final TemporalValueFactory temporalValueFactory;
    private final TokenService tokenService;

    public List<Fahrt> getFahrten()
    {
        return fahrtRepository.findAll();
    }

    public Optional<Fahrt> getFahrt(Long id) { return fahrtRepository.findById(id); }

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

    public void deleteFahrten() { fahrtRepository.deleteAll(); }

    public void deleteFahrt(Long id) { fahrtRepository.deleteById(id);}


   /* private Fahrt _createFahrt(Optional<String> token, MutateFahrtCommand command) {
        //LocalDateTime creationTS = temporalValueFactory.now();
        String tokenValue = token.orElseGet(() -> tokenService.createNanoToken());
        Fahrt fahrt = Fahrt.builder()
                .nummer(command.getNummer())
                //.token(tokenValue)
                //.creationTS(creationTS)
                .taxifahrer(Mitarbeiter.builder()
                        .vorname(command.getVorname())
                        .nachname(command.getNachname())
                        .build())
                .build();
        return FahrtRepository.save(fahrt);
    }*/








}