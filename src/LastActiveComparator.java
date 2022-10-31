import Registry.Filter;

import java.util.Comparator;

public class LastActiveComparator implements Comparator<FieldView>
{

    protected String direction;

    public LastActiveComparator(String direction)
    {
        this.direction = direction;
    }

    @Override
    public int compare(FieldView o1, FieldView o2)
    {
        if (this.direction.equals(Filter.ORDER_ASC)) {
            return this.directionAsc(o1, o2);
        }
        if (this.direction.equals(Filter.ORDER_DESC)) {
            return this.directionDesc(o1, o2);
        }

        return 0;
    }

    protected int directionAsc(FieldView o1, FieldView o2)
    {
        if (o2.getLastactive() < o1.getLastactive()) {
            return 1;
        } else if (o2.getLastactive() < o1.getLastactive()) {
            return -1;
        }
        return 0;
    }

    protected int directionDesc(FieldView o1, FieldView o2)
    {
        if (o2.getLastactive() > o1.getLastactive()) {
            return 1;
        } else if (o2.getLastactive() < o1.getLastactive()) {
            return -1;
        }
        return 0;
    }
}
