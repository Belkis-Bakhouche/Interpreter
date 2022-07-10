package Main;

import java.util.Scanner;
import EvaluationTree.*;
import Exceptions.EvaluationException;
import EvaluationTree.ExpressionNodes.*;
import Exceptions.InvalidCommandeException;
import Exceptions.InvalidExpressionException;
import Exceptions.ParserException;
import Exceptions.TooLongLineException;
import Exceptions.InvalidVariableNameException;

import Models.Commande;

class Main {
    public static void main(String[] args) throws EvaluationException, TooLongLineException {

        Compilateur miniInterpreter = new Compilateur();
        Scanner scanner = new Scanner(System.in);
        String line;

        System.out.println("Entrez vos commandes ici. Pour arr�ter le programme, entrez END \n");

        while (true) {
            System.out.print(">");
            line = scanner.nextLine();

            //end the program if the user enters "end"
            if (line.trim().toLowerCase().equals("end")) break;

            try {
            	Commande ligne=new Commande(line);
                ligne.longuer_valide();
                miniInterpreter.interpreter(new Commande(line));

            } catch ( InvalidCommandeException | InvalidExpressionException | EvaluationException | ParserException | InvalidVariableNameException |TooLongLineException    e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

