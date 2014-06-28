package org.ken.avery.exceptions;

public class CustomException extends Exception
{
    private static final long serialVersionUID = 1L;

    final static String defaultMessage = "Custom Exception Default Message";
    private String exceptionMessage = null;

    public CustomException()
    {
        super(defaultMessage);
        this.exceptionMessage = defaultMessage;
    }

    public CustomException(final String message)
    {
        super(message);
        this.exceptionMessage = message;
    }

    public CustomException(final Throwable cause)
    {
        super(cause);
    }

    @Override
    public String toString()
    {
        return "CustomException [exceptionMessage=" + exceptionMessage + "]";
    }

    public String getExceptionMessage()
    {
        return exceptionMessage;
    }
}
