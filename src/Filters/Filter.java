package Filters;

import java.util.ArrayList;

public class Filter
{
    /**
     * Все поля для отбора
     */
    protected ArrayList<Field> fields;
    /**
     * Поля для сортировки
     */
    protected ArrayList<Order> order;

    /**
     * Лимит на выборку
     */
    protected Limit limit;

    public Filter(ArrayList<Field> fields, ArrayList<Order> order, Limit limit)
    {
        this.fields = fields;
        this.order = order;
        this.limit = limit;
    }

    public ArrayList<Field> getFields()
    {
        return fields;
    }

    public ArrayList<Order> getOrder()
    {
        return order;
    }

    public void addField(Field field)
    {
        this.fields.add(field);
    }

    public void addOrder(Order order)
    {
        this.order.add(order);
    }

    public Limit getLimit()
    {
        return limit;
    }

    public void setLimit(Limit limit)
    {
        this.limit = limit;
    }
}
