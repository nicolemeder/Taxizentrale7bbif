package at.project.taxizentrale7bbif.service;

import at.project.taxizentrale7bbif.domain.Taxizentrale;
import at.project.taxizentrale7bbif.persistence.TaxizentraleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class TaxizentraleService { //read only

    private final TaxizentraleRepository taxizentraleRepository; //dependency injection in production code

    public List<Taxizentrale> getTaxizentrale()
    {
        return taxizentraleRepository.findAll();
    }

}
