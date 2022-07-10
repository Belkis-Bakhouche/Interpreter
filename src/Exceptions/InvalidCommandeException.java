package Exceptions;

public class InvalidCommandeException extends Exception {

    public InvalidCommandeException(String invalid) {
        super(invalid + " n'est pas une commande valide, Veuillez utiliser : let | print");
    }
}
