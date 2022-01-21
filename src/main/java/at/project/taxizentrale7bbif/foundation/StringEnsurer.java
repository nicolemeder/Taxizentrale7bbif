package at.project.taxizentrale7bbif.foundation;

public record StringEnsurer(String value, String name) implements Ensurer<StringEnsurer, String> {

    public StringEnsurer isNotNull()
    {
        if(value == null)
        {
            throw new IllegalArgumentException(String.format(("Es darf nicht Null sein"), name));
        }
        return this;
    }

    public StringEnsurer isNotEmpty()
    {
        isNotNull();
        if(value.isEmpty())
        {
            throw new IllegalArgumentException(String.format(("Es darf nicht Empty sein"), name));
        }
        return this;
    }

    public StringEnsurer isNotBlank()
    {
        if(value.isBlank())
        {
            throw new IllegalArgumentException(String.format(("Es darf nicht Blank sein")));
        }
        return this;
    }
}
