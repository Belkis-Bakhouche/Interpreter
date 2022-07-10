package EvaluationTree.ExpressionNodes;

import Exceptions.EvaluationException;
import Exceptions.ParserException;
import EvaluationTree.ExpressionNode;
import EvaluationTree.ExpressionNodeVisitor;

public class FunctionNode implements ExpressionNode
{
    /** function id for the sin function */
    public static final int SIN = 1;
    /** function id for the cos function */
    public static final int COS = 2;
    /** function id for the tan function */
    public static final int TAN = 3;

    /** function id for the asin function */
    public static final int ASIN = 4;
    /** function id for the acos function */
    public static final int ACOS = 5;
    /** function id for the atan function */
    public static final int ATAN = 6;

    /** function id for the sqrt function */
    public static final int SQRT = 7;
    /** function id for the exp function */
    public static final int EXP = 8;

    /** function id for the ln function */
    public static final int LN = 9;
    /** function id for the log function */
    public static final int LOG = 10;
    /** function id for the log2 function */
    public static final int LOG2 = 11;
    /** function id for the abs function */
    public static final int ABS = 12;

    /** the id of the function to apply to the argument */
    private int function;

    /** the argument of the function */
    private ExpressionNode argument;

    public FunctionNode(int function, ExpressionNode argument)
    {
        super();
        this.function = function;
        this.argument = argument;
    }

    public int getType()
    {
        return ExpressionNode.FUNCTION_NODE;
    }

    public static int stringToFunction(String str) {
        if (str.equals("sin"))
            return FunctionNode.SIN;
        if (str.equals("cos"))
            return FunctionNode.COS;
        if (str.equals("tan"))
            return FunctionNode.TAN;

        if (str.equals("asin"))
            return FunctionNode.ASIN;
        if (str.equals("acos"))
            return FunctionNode.ACOS;
        if (str.equals("atan"))
            return FunctionNode.ATAN;

        if (str.equals("sqrt"))
            return FunctionNode.SQRT;
        if (str.equals("exp"))
            return FunctionNode.EXP;

        if (str.equals("ln"))
            return FunctionNode.LN;
        if (str.equals("log"))
            return FunctionNode.LOG;
        if (str.equals("log2"))
            return FunctionNode.LOG2;
        if (str.equals("abs"))
            return FunctionNode.ABS;

        throw new ParserException("Fonction inattendue " + str + " trouv�e");
    }

    /**
     * Returns a string with all the function names concatenated by a | symbol.
     *
     * This string is used in Tokenizer.createExpressionTokenizer to create a
     * regular expression for recognizing function names.
     *
     * @return a string containing all the function names
     */
    public static String getAllFunctions()
    {
        return "sin|cos|tan|asin|acos|atan|sqrt|exp|ln|log|log2|abs";
    }

    /**
     * Returns the value of the sub-expression that is rooted at this node.
     *
     * The argument is evaluated and then the function is applied to the resulting
     * value.
     */
    public double getValue() throws EvaluationException {
        switch (function) {
            case SIN:
                return Math.sin(Math.toRadians(argument.getValue()));
            case COS:
                return Math.cos(Math.toRadians(argument.getValue()));
            case TAN:
                return Math.tan(Math.toRadians(argument.getValue()));
            case ASIN:
                return Math.asin(Math.toRadians(argument.getValue()));
            case ACOS:
                return Math.acos(Math.toRadians(argument.getValue()));
            case ATAN:
                return Math.atan(Math.toRadians(argument.getValue()));
            case SQRT:
                return Math.sqrt(argument.getValue());
            case EXP:
                return Math.exp(argument.getValue());
            case LN:
                return Math.log(argument.getValue());
            case LOG:
                return Math.log(argument.getValue()) * 0.43429448190325182765;
            case LOG2:
                return Math.log(argument.getValue()) * 1.442695040888963407360;
            case ABS:
                return Math.abs(argument.getValue());
        }
        throw new EvaluationException("Invalid function id "+function+"!");
    }

    public void accept(ExpressionNodeVisitor visitor) {
        visitor.visit(this);
        argument.accept(visitor);
    }

}
