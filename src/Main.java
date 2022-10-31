import Filters.Field;
import Filters.Filter;
import Filters.Limit;
import Filters.Order;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        (new Main()).appRun(args);
    }

    /**
     * @param args
     */
    public void appRun(String[] args)
    {

        Filter filter = new Filter(new ArrayList(), new ArrayList(), new Limit());
        if (args.length == 0) {
            filter.getFields().add((new Field("country_code","UM")));
            filter.getFields().add((new Field("country_code","BY")));
            filter.getFields().add((new Field("country_code","RU")));
            filter.getFields().add((new Field("country_code","US")));
            filter.getFields().add((new Field("fun","1")));
            filter.getFields().add((new Field("totaldeposit","1")));

            filter.getOrder().add(new Order("Lastactive","asc"));
            // filter.setLimit(new Limit(0, 100000));
        }
        FileParser fileParser = new FileParser(filter);
        try {
            fileParser.run();
        } catch (Exception e) {
            System.out.println("Файл не прочитан:" + e.toString());
        }
        return;
    }

    protected boolean inputIsInt(String userInput)
    {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException $e) {
            return false;
        }
    }


}

