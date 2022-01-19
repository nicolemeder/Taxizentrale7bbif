package at.project.taxizentrale7bbif;

import at.project.taxizentrale7bbif.domain.Bundesland;

public class TestFixures {

    public static Bundesland vie() {
    return Bundesland.builder()
            .name("Wien")
            .build();
    }
}
