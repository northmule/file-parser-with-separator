package Exceptions;

public class VariablesExceptions extends RuntimeException
{
    public static Throwable variableIsNotExpected()
    {
        return new Throwable("The specified variable is not expected");
    }
}
