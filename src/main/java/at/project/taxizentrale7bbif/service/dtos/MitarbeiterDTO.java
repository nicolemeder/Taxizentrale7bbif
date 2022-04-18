package at.project.taxizentrale7bbif.service.dtos;

import at.project.taxizentrale7bbif.domain.Mitarbeiter;

public record MitarbeiterDTO(String vorname, String nachname) {
    public MitarbeiterDTO(Mitarbeiter mitarbeiter) {
        this(mitarbeiter.getVorname(), mitarbeiter.getNachname());
    }

}
