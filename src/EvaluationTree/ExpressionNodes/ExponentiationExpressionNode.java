package EvaluationTree.ExpressionNodes;

import Exceptions.EvaluationException;
import EvaluationTree.ExpressionNode;
import EvaluationTree.ExpressionNodeVisitor;

public class ExponentiationExpressionNode implements ExpressionNode
{
    private ExpressionNode base;
    private ExpressionNode exponent;

    public ExponentiationExpressionNode(ExpressionNode base, ExpressionNode exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public int getType()
    {
        return ExpressionNode.EXPONENTIATION_NODE;
    }

    public double getValue() throws EvaluationException {
        return Math.pow(base.getValue(), exponent.getValue());
    }

    public void accept(ExpressionNodeVisitor visitor) {
        visitor.visit(this);
        base.accept(visitor);
        exponent.accept(visitor);
    }

}
