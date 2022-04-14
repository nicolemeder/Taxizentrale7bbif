package at.project.taxizentrale7bbif.service.dtos;

import at.project.taxizentrale7bbif.domain.Fahrt;

public record FahrtDTO(Integer fahrtNummer, MitarbeiterDTO taxifahrer) {
    public FahrtDTO(Fahrt fahrt)
    {
        this(fahrt.getNummer(), new MitarbeiterDTO(fahrt.getTaxifahrer()));
    }
}
