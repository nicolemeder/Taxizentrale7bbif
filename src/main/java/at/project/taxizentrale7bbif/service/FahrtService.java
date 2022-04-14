package at.project.taxizentrale7bbif.service;

import at.project.taxizentrale7bbif.domain.Fahrt;
import at.project.taxizentrale7bbif.persistence.FahrtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FahrtService {

    private final FahrtRepository fahrtRepository;

    public List<Fahrt> getFahrten()
    {
        return fahrtRepository.findAll();
    }

}