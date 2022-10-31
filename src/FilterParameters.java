import java.util.HashMap;

public class FilterParameters
{
    /**
     * Параметры фильтра
     */
    private HashMap<String, String> filter;
    /**
     * Лимит строк на вывод
     */
    private HashMap<String, Integer> limit;
    /**
     * Направление сортировки по колонки lastActive
     */
    private String orderLastActive = "desc";

    public FilterParameters(HashMap<String, String> filter, HashMap<String, Integer> limit, String orderLastActive)
    {
        this.filter = filter;
        this.limit = limit;
        this.orderLastActive = orderLastActive;
    }

    public HashMap<String, String> getFilter()
    {
        return filter;
    }

    public HashMap<String, Integer> getLimit()
    {
        return limit;
    }

    public String getOrderLastActive()
    {
        return orderLastActive;
    }
}
