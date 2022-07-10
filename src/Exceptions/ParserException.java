package Exceptions;

import Models.Element;

public class ParserException extends RuntimeException
{

    /** the token that caused the error */
    private Element element = null;
    public ParserException(String message)
    {
        super(message);
    }

    public ParserException(String message, Element element)
    {
        super(message);
        this.element = element;
    }

    /**
     * Get the token.
     * @return the token that caused the exception
     */
    public Element getToken()
    {
        return element;
    }

    /**
     * Overrides RuntimeException.getMessage to add the token information
     * into the error message.
     *
     *  @return the error message
     */
    public String getMessage()
    {
        String msg = super.getMessage();
        if (element != null)
        {
            msg = msg.replace("%s", element.sequence);
        }
        return msg;
    }

}