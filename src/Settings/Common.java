package Settings;

/**
 * Настройки скрипта
 */
public class Common
{
    /**
     * Файл для обработки
     */
    protected String incomingNameFile;

    /**
     * Файл с результатом
     */
    protected String outputNameFile;

    /**
     * Путь к папке с файлами
     */
    protected String folderPath;

    public Common()
    {
        this.incomingNameFile = "full111.txt";
        this.outputNameFile = "result1.csv";
        this.folderPath = "programData";
    }

    public String getIncomingNameFile()
    {
        return incomingNameFile;
    }

    public void setIncomingNameFile(String incomingNameFile)
    {
        this.incomingNameFile = incomingNameFile;
    }

    public String getOutputNameFile()
    {
        return outputNameFile;
    }

    public void setOutputNameFile(String outputNameFile)
    {
        this.outputNameFile = outputNameFile;
    }

    public String getFolderPath()
    {
        return folderPath;
    }

    public void setFolderPath(String folderPath)
    {
        this.folderPath = folderPath;
    }
}
