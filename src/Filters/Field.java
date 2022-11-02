package Filters;

public class Field
{
    /**
     * Имя параметра
     */
    protected String name;
    /**
     * Значение параметра
     */
    protected String value;

    public Field(String name, String value)
    {
        this.name = name.toLowerCase();
        this.value = value.toLowerCase();
    }

    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return value;
    }

}
