package EvaluationTree;

import Exceptions.EvaluationException;

public interface ExpressionNode {

    /** Node id for variable nodes */
    int VARIABLE_NODE = 1;
    /** Node id for constant nodes */
    int CONSTANT_NODE = 2;
    /** Node id for addition nodes */
    int ADDITION_NODE = 3;
    /** Node id for multiplication nodes */
    int MULTIPLICATION_NODE = 4;
    /** Node id for exponentiation nodes */
    int EXPONENTIATION_NODE = 5;
    /** Node id for function nodes */
    int FUNCTION_NODE = 6;

    public int getType();

    //should return the evaluated result of the sub-tree which is rooted in the current node
    public double getValue() throws EvaluationException;

    public void accept(ExpressionNodeVisitor visitor);

}
