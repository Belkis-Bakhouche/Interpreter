package Models;

import EvaluationTree.ExpressionNode;
import EvaluationTree.Parser;
import EvaluationTree.SetVariable;
import Exceptions.EvaluationException;
import Main.Compilateur;

public class Print extends Commande{


    private Expression expression;
    private double result;

    public Print(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void evaluate() throws EvaluationException {

        Parser parser = new Parser();
        ExpressionNode exprNode = parser.parse(expression.getString());
        for(String key: Compilateur.TableDesSymboles.keySet()) {
            exprNode.accept(new SetVariable(key,Compilateur.TableDesSymboles.get(key)));
        }
        result = exprNode.getValue();

//        if(expression.checkBnfSyntax()){
//            System.out.println("is a valid expression");
//        }else{
//            System.out.println("is not a valid expression");
//        }

    }
    public void print(){
        System.out.println("La valeur est: "+result);
    }


}
