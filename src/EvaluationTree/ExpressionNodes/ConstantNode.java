package EvaluationTree.ExpressionNodes;

import EvaluationTree.ExpressionNode;
import EvaluationTree.ExpressionNodeVisitor;

public class ConstantNode implements ExpressionNode
{
    private double value;

    public ConstantNode(double value)
    {
        this.value = value;
    }

    public ConstantNode(String value)
    {
        this.value = Double.valueOf(value);
    }

    public double getValue()
    {
        return value;
    }

    public int getType()
    {
        return ExpressionNode.CONSTANT_NODE;
    }

    public void accept(ExpressionNodeVisitor visitor)
    {
        visitor.visit(this);
    }

}
