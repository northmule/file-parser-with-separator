package Filters;

import Registry.Filter;

public class Order
{
    /**
     * Поле для сортировки
     */
    protected String field;
    /**
     * Направление сортировки
     */
    protected String direct;

    public Order(String field, String direct)
    {
        this.field = field.toLowerCase();
        if (direct.equals(Filter.ORDER_ASC)) {
            this.direct = Filter.ORDER_ASC;
        } else {
            this.direct = Filter.ORDER_DESC;
        }
    }

    public String getField()
    {
        return field;
    }

    public String getDirect()
    {
        return direct;
    }
}
