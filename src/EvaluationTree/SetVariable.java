package EvaluationTree;
import EvaluationTree.ExpressionNodes.*;
public class SetVariable implements ExpressionNodeVisitor {


    private String name;
    private double value;

   
    public SetVariable(String name, double value)
    {
        super();
        this.name = name;
        this.value = value;
    }

    
    public void visit(VariableNode node)
    {
        if (node.getName().equals(name))
            node.setValue(value);
    }

    /** Do nothing */
    public void visit(ConstantNode node)
    {}

    /** Do nothing */
    public void visit(AdditionExpressionNode node)
    {}

    /** Do nothing */
    public void visit(MultiplicationExpressionNode node)
    {}

    /** Do nothing */
    public void visit(ExponentiationExpressionNode node)
    {}

    /** Do nothing */
    public void visit(FunctionNode node)
    {}

}
