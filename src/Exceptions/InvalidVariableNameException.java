package Exceptions;

public class InvalidVariableNameException extends Exception{

    public InvalidVariableNameException(String invalid) {
        super(invalid);
    }

}
