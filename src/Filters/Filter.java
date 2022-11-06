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

    /**
     * @return true - установлен код фильтра по стране
     */
    public Boolean existCountyCode()
    {
        for (int i = 0; i < this.fields.size(); i++) {
            Field field = this.fields.get(i);
            if (field.getName().equals(Registry.Filter.COUNTRY_CODE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true - установлен код фильтра по fun
     */
    public Boolean existFun()
    {
        for (int i = 0; i < this.fields.size(); i++) {
            Field field = this.fields.get(i);
            if (field.getName().equals(Registry.Filter.FUN)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true - установлен код фильтра по стране
     */
    public Boolean existTotalDeposit()
    {
        for (int i = 0; i < this.fields.size(); i++) {
            Field field = this.fields.get(i);
            if (field.getName().equals(Registry.Filter.TOTAL_DEPOSIT)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param type тип фильтра из констант
     * @return отобранные поля по типу
     */
    public ArrayList<Field> getFieldsByType(String type)
    {
        ArrayList<Field> fields = new ArrayList<>();
        for (int i = 0; i < this.fields.size(); i++) {
            if (this.fields.get(i).getName().equals(type)) {
                fields.add(this.fields.get(i));
            }
        }
        return fields;
    }
}
