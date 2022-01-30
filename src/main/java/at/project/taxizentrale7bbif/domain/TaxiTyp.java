package at.project.taxizentrale7bbif.domain;


import lombok.Getter;

import java.util.Arrays;

public enum TaxiTyp {
    NICHTRAUCHER("N"),
    BEHINDERTENTRANSPORT("B"),
    GROSSRAUM("G"),
    VIP("V");

    @Getter
    private final String databaseValue;

    TaxiTyp(String databaseValue) {
        this.databaseValue = databaseValue;
    }

    public static TaxiTyp forDatabaseValue(String databaseValue) {
        return Arrays.stream(values()).filter(taxityp -> taxityp.databaseValue.equalsIgnoreCase(databaseValue))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(String.format("Ungültiger Wert.")));
    }
}