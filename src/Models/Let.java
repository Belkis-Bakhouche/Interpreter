package Models;

import EvaluationTree.ExpressionNode;

import EvaluationTree.Parser;
import Exceptions.EvaluationException;
import Exceptions.InvalidVariableNameException;
//import Exceptions.NomR�serv�Exception;
import Main.Compilateur;


public class Let extends Commande {

    private Variable var;
    private Expression expression;

    public Let(Variable variable, Expression expression) {
        setVar(variable);
        setExpression(expression);
    }

    public Variable getVar() {
        return var;
    }

    public void setVar(Variable var) {
        this.var = var;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void assing() throws EvaluationException, InvalidVariableNameException {
//        if(var.checkBnfSyntax() && expression.checkBnfSyntax()){
            Parser parser = new Parser();
            ExpressionNode exprNode = parser.parse(expression.getString());
            boolean bool=var.valider_nom(var.getNameKey());
            Compilateur.TableDesSymboles.put(var.getNameKey(), exprNode.getValue());
            System.out.println("ok");
//        }
    }
}
