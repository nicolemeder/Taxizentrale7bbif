package at.project.taxizentrale7bbif.foundation;

public class EnsurerFactory {

    public static final StringEnsurer when(String value)
    {
        return when(value, "value");
    }

    public static final StringEnsurer when(String value, String name)
    {
        return new StringEnsurer(value, name);
    }

}
