package EvaluationTree;
import EvaluationTree.ExpressionNodes.*;
public interface ExpressionNodeVisitor {
	   /** Visit a VariableExpressionNode */
    void visit(VariableNode node);

    /**  Visit a ConstantExpressionNode */
    void visit(ConstantNode node);

    /**  Visit a AdditionExpressionNode */
    void visit(AdditionExpressionNode node);

    /**  Visit a MultiplicationExpressionNode */
    void visit(MultiplicationExpressionNode node);

    /**  Visit a ExponentiationExpressionNode */
    void visit(ExponentiationExpressionNode node);

    /**  Visit a FunctionExpressionNode */
    void visit(FunctionNode node);
}
