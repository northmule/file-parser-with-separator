# file-parser-with-separator
Parses the file according to the specified parameters. Creates a new csv file with the selected data.

     * Параметры запуска:
     *  - country_code=UM:RU:BY фильтр по странам, страны разделяются через двойную точку
     *  - totaldeposit=1 выбираются значения большие чем
     *  - fun=1 выбираются значения большие чем
     *  - limit=0-15 выбираются записи с 0 позиции по 15-ю
     *  - lastactive=desc сортировка по последней активности (desc или asc направление сортировки)
     *  Параметры можно указывать в любом порядке, так же их можно пропускать
     * java -jar file-parser-with-separator.jar country_code=UM:RU:BY totaldeposit=1 fun=1 limit=0-15 lastactive=desc

**Начальные настройки**
```json
{
  "incomingNameFile": "full111.txt", // Входящий файл для обработки
  "outputNameFile": "result1.csv", // Файл с результатом
  "folderPath": "programData" // Путь к папке с входящим и результирующим файлом
}
```