package EvaluationTree.ExpressionNodes;

import Exceptions.EvaluationException;
import EvaluationTree.ExpressionNode;
import EvaluationTree.ExpressionNodeVisitor;

public class VariableNode  implements ExpressionNode
{
    /** The name of the variable */
    private String name;
    /** The value of the variable */
    private double value;
    /** indicates if the value has been set */
    private boolean valueSet;

    /**
     * Construct with the name of the variable.
     *
     * @param name
     *          the name of the variable
     */
    public VariableNode(String name)
    {
        this.name = name;
        valueSet = false;
    }

    /**
     * @return the name of the variable
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the type of the node, in this case ExpressionNode.VARIABLE_NODE
     */
    public int getType()
    {
        return ExpressionNode.VARIABLE_NODE;
    }

    /**
     * Sets the value of the variable
     *
     * @param value
     *          the value of the variable
     */
    public void setValue(double value)
    {
        this.value = value;
        this.valueSet = true;
    }

    /**
     * Returns the value of the variable but throws an exception if the value has
     * not been set
     */
    public double getValue() throws EvaluationException {
        if (valueSet)
            return value;
        else
            throw new EvaluationException("Variable '" + name + "' n'a pas �t� initialis�.");
    }

    
   
    public void accept(ExpressionNodeVisitor visitor)
    {
        visitor.visit(this);
    }


}
