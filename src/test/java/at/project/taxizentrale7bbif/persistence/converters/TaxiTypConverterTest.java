package at.project.taxizentrale7bbif.persistence.converters;

import at.project.taxizentrale7bbif.domain.TaxiTyp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class TaxiTypConverterTest {

    private TaxiTypConverter converter;

    @BeforeEach
    void setup() {
        converter = new TaxiTypConverter();
    }

    @ParameterizedTest
    @EnumSource(TaxiTyp.class)
    void ensureMappingOfValidValuesWorksProperly(TaxiTyp taxiTyp)
    {
        assertThat(converter.convertToDatabaseColumn(taxiTyp)).isEqualTo(taxiTyp.getDatabaseValue());
        assertThat(converter.convertToDatabaseColumn(null)).isNull();
    }

    @Test
    void ensureMappingofValidDatabaseValueWorksProperly(TaxiTyp taxiTyp)
    {
        assertThat(converter.convertToEntityAttribute(taxiTyp.getDatabaseValue())).isEqualTo(taxiTyp);
        assertThat(converter.convertToEntityAttribute(null)).isNull();
    }

   /* @Test
    void ensureMappingOfUnknownMappingValuesThrowsProperException()
    {
        var ex = assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("X"));
        assertThat(ex).hasMessage("Unbekannter Datenbankwert!");
    }

    */
}
