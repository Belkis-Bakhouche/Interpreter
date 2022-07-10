package Main;


import Exceptions.EvaluationException;
import Exceptions.InvalidCommandeException;
import Exceptions.InvalidExpressionException;
import Exceptions.InvalidVariableNameException;
import Models.*;

import java.util.HashMap;


public class Compilateur{

    public static HashMap<String, Double> TableDesSymboles = new HashMap<String, Double>();

    public void interpreter(Commande commandeLine) throws InvalidCommandeException, InvalidExpressionException, EvaluationException, InvalidVariableNameException {

        int cmdCode = identifyCommande(commandeLine);

        switch (cmdCode){
            case 1:
                // instantiate new Print commande then evaluate and print it

                Print printCommande = new Print(new Expression(commandeLine.getCmdBody()));
                printCommande.evaluate();
                printCommande.print();

                break;

            case 0:

                //extract the variable and the expression from the let body
                String varString = getLetArgs(commandeLine.getCmdBody().strip())[0].strip();
                String expreString = getLetArgs(commandeLine.getCmdBody().strip())[1].strip();

                // instantiate new Let commande and assign the expression to the
                Let letCommande = new Let(new Variable(varString) , new Expression(expreString));
                letCommande.assing();

                break;
            case -1:
                throw new InvalidCommandeException(commandeLine.getCommandeName());
        }
    }

    private int identifyCommande(Commande commande){
       
        if(commande.getCommandeName().strip().toLowerCase().equals("print")) return 1;
        if(commande.getCommandeName().strip().toLowerCase().equals("let")) return 0;
        return -1;

    }

    private String[] getLetArgs(String cmdBody) {
        return cmdBody.split("=");
    }

}