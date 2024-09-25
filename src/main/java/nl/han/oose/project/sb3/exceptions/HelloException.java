package nl.han.oose.project.sb3.exceptions;

public class HelloException extends RuntimeException
{
    public HelloException(String message)
    {
        this(message, null);
    }

    public HelloException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
