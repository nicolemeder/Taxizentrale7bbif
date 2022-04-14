package at.project.taxizentrale7bbif.foundation;

import java.util.List;
import java.util.function.Supplier;

public interface TokenSourceProvider {
    List<Supplier<String>> getTokenRelevantFields();
}
