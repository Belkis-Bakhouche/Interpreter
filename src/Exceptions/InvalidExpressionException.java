package Exceptions;

public class InvalidExpressionException extends  Exception{

    public InvalidExpressionException(String invalid) {
        super(invalid + " is not a valid expression");
    }
}

