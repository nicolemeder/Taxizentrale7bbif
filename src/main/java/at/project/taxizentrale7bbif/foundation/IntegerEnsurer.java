package at.project.taxizentrale7bbif.foundation;

public record IntegerEnsurer (Integer value) implements Ensurer<IntegerEnsurer, Integer>
{
    public IntegerEnsurer isPositiv()
    {
        if(value < 0)
        {
            throw new IllegalArgumentException(String.format("Das Value war nicht positiv", value));
        }
        return this;
    }
}
