package at.project.taxizentrale7bbif.presentation;

import at.project.taxizentrale7bbif.domain.Taxizentrale;
import at.project.taxizentrale7bbif.service.TaxizentraleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.trace.http.HttpTraceEndpointAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/zentrale")
public class TaxizentraleRestController {

    private final TaxizentraleService taxizentraleService;


    @GetMapping("/")
    public HttpEntity<List<Taxizentrale>> getTaxizentrale()
    {
        return ResponseEntity.ok(taxizentraleService.getTaxizentrale());
    }
}
