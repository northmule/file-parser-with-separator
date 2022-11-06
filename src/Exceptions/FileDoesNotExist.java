package Exceptions;

public class FileDoesNotExist extends RuntimeException
{
    public static Throwable fileNotFound(String path)
    {
        return new Throwable("Файл который требуется обработать, не найден по указанному пути: " + path);
    }
}
