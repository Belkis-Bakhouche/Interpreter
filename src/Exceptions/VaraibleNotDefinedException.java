package Exceptions;

public class VaraibleNotDefinedException extends Exception{

    public VaraibleNotDefinedException(String invalid) {
        super(invalid + " is not defined");
    }

}