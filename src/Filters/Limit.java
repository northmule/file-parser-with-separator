package Filters;

public class Limit
{
    protected Integer start = 0;

    protected Integer end = 1000000000;

    public Limit(Integer start, Integer end)
    {
        this.start = start;
        this.end = end;
    }

    public Limit()
    {

    }

    public Integer getStart()
    {
        return start;
    }

    public Integer getEnd()
    {
        return end;
    }
}
