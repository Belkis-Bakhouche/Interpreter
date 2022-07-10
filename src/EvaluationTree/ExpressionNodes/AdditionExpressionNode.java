package EvaluationTree.ExpressionNodes;

import Exceptions.EvaluationException;
import EvaluationTree.ExpressionNode;
import EvaluationTree.ExpressionNodeVisitor;
import Models.Term;

public class AdditionExpressionNode extends SequenceExpressionNode {

    public AdditionExpressionNode() {}

    public AdditionExpressionNode(ExpressionNode node, boolean positive)
    {
        super(node, positive);
    }

    public int getType()
    {
        return ExpressionNode.ADDITION_NODE;
    }

    public double getValue() throws EvaluationException {
        double sum = 0.0;
        for (Term t : terms)
        {
            if (t.positive)
                sum += t.expression.getValue();
            else
                sum -= t.expression.getValue();
        }
        return sum;
    }

    public void accept(ExpressionNodeVisitor visitor)
    {
        visitor.visit(this);
        for (Term t : terms)
            t.expression.accept(visitor);
    }


}
