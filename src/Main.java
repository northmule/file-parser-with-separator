import Filters.Field;
import Filters.Filter;
import Filters.Limit;
import Filters.Order;
import java.util.ArrayList;

public class Main
{
    protected Boolean debugFilter = false;

    /**
     * Параметры запуска:
     *  - country_code=UM:RU:BY фильтр по странам, страны разделяются через двойную точку
     *  - totaldeposit=1 выбираются значения большие чем
     *  - fun=1 выбираются значения большие чем
     *  - limit=0-15 выбираются записи с 0 позиции по 15-ю
     *  - lastactive=desc сортировка по последней активности (desc или asc направление сортировки)
     *  Параметры можно указывать в любом порядке, так же их можно пропускать
     * java -jar file-parser-with-separator.jar country_code=UM:RU:BY totaldeposit=1 fun=1 limit=0-15 lastactive=desc
     *
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

        if (!this.debugFilter && args.length == 0) {
            System.out.println("Укажите параметры для обработки:");
            System.out.println("\tcountry_code=UM:RU:BY фильтр по странам, страны разделяются через двойную точку");
            System.out.println("\ttotaldeposit=1 выбираются значения большие чем");
            System.out.println("\tfun=1 выбираются значения большие чем");
            System.out.println("\tlimit=0-15 выбираются записи с 0 позиции по 15-ю");
            System.out.println("\tlastactive=desc сортировка по последней активности (desc или asc направление сортировки)");
            System.out.println("Параметры можно указывать в любом порядке, так же их можно пропускать");
            return;
        }

        Filter filter = new Filter(new ArrayList(), new ArrayList(), new Limit());
        for(int i = 0; i < args.length; i++) {
            String paramLine = args[i];
            String[] paramData = paramLine.split("=", 2);
            String paramName = paramData[0];
            String paramValue = paramData[1];
            if (paramName.equals("country_code")) {
                String[] countryCodeValue = paramValue.split(":");
                for(int j = 0; j < countryCodeValue.length; j++) {
                    filter.getFields().add((new Field(Registry.Filter.COUNTRY_CODE, countryCodeValue[j])));
                }
            }
            if (paramName.equals("fun")) {
                filter.getFields().add((new Field(Registry.Filter.FUN, paramValue)));
            }
            if (paramName.equals("totaldeposit")) {
                filter.getFields().add((new Field(Registry.Filter.TOTAL_DEPOSIT, paramValue)));
            }
            if (paramName.equals("lastactive")) {
                filter.getOrder().add(new Order("Lastactive", paramValue));
            }

            if (paramName.equals("limit")) {
                String[] limitValue = paramValue.split("-", 2);
                filter.setLimit(new Limit(Integer.valueOf(limitValue[0]), Integer.valueOf(limitValue[1])));
            }

        }
        if (this.debugFilter && args.length == 0) {
            filter.getFields().add((new Field("country_code", "CA")));
            filter.getFields().add((new Field("country_code", "DE")));
//            filter.getFields().add((new Field("country_code", "BY")));
//            filter.getFields().add((new Field("country_code", "RU")));
//            filter.getFields().add((new Field("country_code", "US")));
//            filter.getFields().add((new Field("fun", "0")));
//            filter.getFields().add((new Field("totaldeposit", "0")));
//            filter.getOrder().add(new Order("Lastactive", "asc"));
//            filter.setLimit(new Limit(0, 100000));
        }
        FileParser fileParser = new FileParser("files/full111.txt", filter);
        try {
            fileParser.run();
        } catch (Exception e) {
            System.out.println("Файл не прочитан:" + e.toString());
        }
        return;
    }



}

