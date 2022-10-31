import Filters.Field;
import Filters.Filter;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser
{

    protected String filePath;

    final static protected String FILE_PATH_RESULT = "files/result.csv";

    Filter filter;

    public FileParser(String filePath, Filter filter)
    {
        this.filePath = filePath;
        this.filter = filter;
    }

    public void run() throws FileNotFoundException
    {
        FileReader file = new FileReader(this.filePath);
        BufferedReader bufferedReader = new BufferedReader(file);
        boolean skipFirst = true;
        ArrayList<FieldView> dataOut = new ArrayList<>();
        Integer startLimit = this.filter.getLimit().getStart();
        Integer endLimit = this.filter.getLimit().getEnd();
        try {
            int lineNumber = 1;
            String dataLine = bufferedReader.readLine();
            while (dataLine != null) {
                if (skipFirst && lineNumber == 1) {
                    lineNumber++;
                    dataLine = bufferedReader.readLine();
                    continue;
                }
                if (lineNumber < startLimit) {
                    lineNumber++;
                    continue;
                }
                if (lineNumber >= endLimit) {
                    break;
                }
                FieldView fieldViewItem = this.parsingByLine(dataLine);
                if (fieldViewItem.getName() == null || fieldViewItem.getName().isEmpty()) {
                    dataLine = bufferedReader.readLine();
                    lineNumber++;
                    continue;
                }
                dataOut.add(fieldViewItem);
                lineNumber++;
                dataLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            System.out.println("Массив с данными создан, элементов: " + dataOut.stream().count());
            try {
                dataOut = this.sorting(dataOut);
                System.out.println("Массив отсортирован");
                File fileCsv = new File(FILE_PATH_RESULT);
                if (fileCsv.exists()) {
                    fileCsv.delete();
                }
                fileCsv.createNewFile();
                FileWriter newFile = new FileWriter(fileCsv, true);
                String text;
                for (FieldView saveData : dataOut) {
                    text = "";
                    text += saveData.getName();
                    text += ";";
                    text += saveData.getLastactiveForView();
                    text += ";";
                    text += saveData.getEmailPlayer();
                    text += ";";
                    text += saveData.getCountryCode();
                    text += ";";
                    text += saveData.getFun();
                    text += ";";
                    text += saveData.getPhone();
                    text += '\n';

                    newFile.append(text);
                    newFile.flush();
                }
                System.out.println("Данные записаны в файл");
                newFile.close();

            } catch (IOException e) {
                System.out.println("Ошибка записи файла:" + e.getMessage());
            }


        } catch (Exception e) {
            System.out.println("Ошибка чтения:" + e.toString());
        }

    }

    protected ArrayList<FieldView> sorting(ArrayList<FieldView> data)
    {
        String direction = "desc";
        if (!this.filter.getOrder().isEmpty()) {
            direction = this.filter.getOrder().get(0).getDirect();
        }
        Comparator lastActiveComparator = new LastActiveComparator(direction);
        Collections.sort(data, lastActiveComparator);
        return data;
    }

    /**
     * Разбор одной строки из файла
     */
    protected FieldView parsingByLine(String dataLine)
    {
        dataLine = dataLine.replaceAll(";", "");
        dataLine = dataLine.replaceAll("\n", "");
        dataLine = dataLine.replaceAll("'", "");
        dataLine = dataLine.replaceAll("\"", "");
        String[] stringCollection = dataLine.split(",", 20);

        Pattern pattern = Pattern.compile("([0-9]{4}-[0-9]{2}-[0-9]{2})");
        Matcher matcher = pattern.matcher(dataLine);
        SimpleDateFormat formatterIn = new SimpleDateFormat("Y-MM-dd");
        SimpleDateFormat formatterOut = new SimpleDateFormat("dd.MM.Y");
        String lastactiveForView = "";
        // Для сортировки
        long lastActiveToSort = 1;
        int regIndex = 1;
        while (matcher.find()) {
            if (regIndex == 2) {
                try {
                    Date lastActive = formatterIn.parse(matcher.group(1));
                    lastActiveToSort = lastActive.getTime();
                    lastactiveForView = formatterOut.format(lastActive);
                } catch (ParseException e) {
                    continue;
                }
                break;
            }
            regIndex++;
        }
        FieldView result = new FieldView();
        result.setName(stringCollection[0].trim());
        result.setLastactiveForView(lastactiveForView);
        result.setEmailPlayer(stringCollection[3].trim());
        result.setPhone(stringCollection[4].trim());
        result.setCountryCode(stringCollection[6].toLowerCase().trim());
        result.setFun(stringCollection[7].trim() + stringCollection[8].trim() + stringCollection[9].trim());
        result.setLastactive(lastActiveToSort);


        Double totalDeposit = this.getFloatValue(stringCollection[16]);
        Double fun1 = this.getFloatValue(stringCollection[7]);
        Double fun2 = this.getFloatValue(stringCollection[8]);
        Double fun3 = this.getFloatValue(stringCollection[9]);

        Double sumFun = fun1 + fun2 + fun3;
        if (this.filter.getFields().size() > 0) {
            if (!this.chooseByFilter(Registry.Filter.COUNTRY_CODE, result.getCountryCode())) {
                return new FieldView();
            }
            if (!this.chooseByFilter(Registry.Filter.FUN, sumFun)) {
                return new FieldView();
            }
            if (!this.chooseByFilter(Registry.Filter.TOTAL_DEPOSIT, totalDeposit)) {
                return new FieldView();
            }
        }
        return result;
    }

    // Фильтрует
    private boolean chooseByFilter(String column, String value)
    {
        value = value.toLowerCase();
        if (column.equals(Registry.Filter.COUNTRY_CODE)) {
            ArrayList<Field> fieldsFilter = this.filter.getFields();
            for (int i = 0; i < fieldsFilter.size(); i++) {
                Field field = fieldsFilter.get(i);
                if (field.getName().equals(column) && field.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean chooseByFilter(String column, Double value)
    {
        if (column.equals(Registry.Filter.FUN) || column.equals(Registry.Filter.TOTAL_DEPOSIT)) {
            ArrayList<Field> fieldsFilter = this.filter.getFields();
            for (int i = 0; i < fieldsFilter.size(); i++) {
                Field field = fieldsFilter.get(i);
                if (!field.getName().equals(column)) {
                    continue;
                }
                Double filterValue = this.getFloatValue(field.getValue());
                return value >= filterValue;
            }

        }
        return false;
    }


    private double getFloatValue(String value)
    {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

}
