package at.project.taxizentrale7bbif.persistence.converters;

import at.project.taxizentrale7bbif.domain.TaxiTyp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter (autoApply = true)
public class TaxiTypConverter implements AttributeConverter<TaxiTyp, String> {

    @Override
    public String convertToDatabaseColumn(TaxiTyp taxityp)
    {
        return (taxityp == null) ? null : taxityp.getDatabaseValue();
    }

    @Override
    public TaxiTyp convertToEntityAttribute(String databaseValue) {
        return (databaseValue == null) ? null :TaxiTyp.forDatabaseValue(databaseValue);
    }


}
