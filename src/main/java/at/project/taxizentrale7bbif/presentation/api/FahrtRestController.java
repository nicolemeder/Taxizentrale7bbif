package at.project.taxizentrale7bbif.presentation.api;


import at.project.taxizentrale7bbif.domain.Fahrt;
import at.project.taxizentrale7bbif.service.FahrtService;
import at.project.taxizentrale7bbif.service.dtos.FahrtDTO;
import at.project.taxizentrale7bbif.service.dtos.MutateFahrtCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.spec.OAEPParameterSpec;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(FahrtRestController.BASE_URL)
public class FahrtRestController {

    public static final String BASE_URL = "/api/fahrten";
    public static final String PATH_INDEX = "/";
    public static final String PATH_VAR_ID = "/{id}";
    public static final String ROUTE_ID = BASE_URL + PATH_VAR_ID;

    private final FahrtService fahrtService;

    @GetMapping({"",PATH_INDEX})
    public HttpEntity<List<FahrtDTO>> getFahrten(@RequestParam(required = false)Optional<String> nummer) {
        List<FahrtDTO> fahrten = nummer.map(fahrtService::getFahrtByNummer)
                .orElseGet(fahrtService::getFahrten)
                .stream()
                .map(FahrtDTO::new)
                .toList();

        return (fahrten.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(fahrten);
    }
    @GetMapping(PATH_VAR_ID)
    public HttpEntity<FahrtDTO> getFahrt(@PathVariable Long id) {
        return fahrtService.getFahrt(id)
                .map(FahrtDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(PATH_INDEX)
    public HttpEntity<List<FahrtDTO>> getFahrtByNummer(@RequestParam String nummer) {
        List<Fahrt> fahrten = fahrtService.getFahrtByNummer(nummer);
        return (fahrten.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(fahrten.stream().map(FahrtDTO::new).toList());
    }

    @PostMapping({"", PATH_INDEX})
    public HttpEntity<FahrtDTO> createFahrt(@RequestBody MutateFahrtCommand command) {
        Fahrt fahrt = fahrtService.createFahrt(command);
        return ResponseEntity.created(createSelfLink(fahrt)).body(new FahrtDTO(fahrt));
    }

    @PutMapping(PATH_VAR_ID) //Idem-Potent
    public HttpEntity<FahrtDTO> replaceFahrt(@PathVariable Long id, @RequestBody MutateFahrtCommand command) {
        return ResponseEntity.ok(new FahrtDTO((fahrtService.replaceFahrt(id, command))));
    }

    @PatchMapping(PATH_VAR_ID)
    public HttpEntity<FahrtDTO> partiallyUpdateFahrt(@PathVariable Long id, @RequestBody MutateFahrtCommand command) {
        return ResponseEntity.ok(new FahrtDTO(fahrtService.partiallyUpdateFahrt(id, command)));
    }

    @DeleteMapping({"", PATH_INDEX})
    public HttpEntity<Void> deleteFahrten() {
        fahrtService.deleteFahrten();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(PATH_VAR_ID)
    public HttpEntity<Void> deleteFahrt(@PathVariable Long id) {
        fahrtService.deleteFahrt(id);
        return ResponseEntity.ok().build();
    }






    private URI createSelfLink(Fahrt fahrt) {
        return UriComponentsBuilder.fromPath(ROUTE_ID)
                .uriVariables(Map.of("id", fahrt.getId()))
                .build().toUri();
    }
}
