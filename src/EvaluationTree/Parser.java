package EvaluationTree;

import Exceptions.ParserException;
import EvaluationTree.ExpressionNodes.*;
import Models.Element;
import java.util.LinkedList;

public class Parser {
	 /** the elements to parse */
    LinkedList<Element> elements;
    /** the next element */
    Element elementSuivant;

    /**
     * Parse a mathematical expression in a string and return an ExpressionNode.
     *
     * This is a convenience method that first converts the string into a linked
     * list of elements using the expression toElement provided by the ToElement
     * class.
     *
     * @param expression
     *          the string holding the input
     * @return the internal representation of the expression in form of an
     *         expression tree made out of ExpressionNode objects
     */
    public ExpressionNode parse(String expression)
    {
        SymboleAnalyzer symboleAnalyzer = SymboleAnalyzer.getExpressionAnalyzer();
        symboleAnalyzer.analyzeSymboles(expression);
        LinkedList<Element> elements = SymboleAnalyzer.getExpressionAnalyzer().getElements();
        return this.parse(elements);
    }

    /**
     * Parse a mathematical expression in contained in a list of elements and return
     * an ExpressionNode.
     *
     * @param elements
     *          a list of elements holding the elementized input
     * @return the internal representation of the expression in form of an
     *         expression tree made out of ExpressionNode objects
     */
    public ExpressionNode parse(LinkedList<Element> elements)
    {
        // implementing a recursive descent parser
        this.elements = (LinkedList<Element>) elements.clone();
        elementSuivant = this.elements.getFirst();

        // top level non-terminal is expression
        ExpressionNode expr = expression();

        if (elementSuivant.element != Element.EPSILON)
            throw new ParserException("Expression erron�e .Symbole inattendu %s trouv�", elementSuivant);

        return expr;
    }

    /** handles the non-terminal expression */
    private ExpressionNode expression()
    {
        // only one rule
        // expression -> signed_term sum_op

        ExpressionNode expr = signedTerm();
        expr = sumOp(expr);
        return expr;
    }

    /** handles the non-terminal sum_op */
    private ExpressionNode sumOp(ExpressionNode expr)
    {
        // sum_op -> PLUSMINUS term sum_op
        if (elementSuivant.element == Element.PLUSMINUS)
        {
            AdditionExpressionNode sum;
            // This means we are actually dealing with a sum
            // If expr is not already a sum, we have to create one
            if (expr.getType() == ExpressionNode.ADDITION_NODE)
                sum = (AdditionExpressionNode) expr;
            else
                sum = new AdditionExpressionNode(expr, true);

            // reduce the input and recursively call sum_op
            boolean positive = elementSuivant.sequence.equals("+");
            nextElement();
            ExpressionNode t = term();
            sum.add(t, positive);

            return sumOp(sum);
        }

        // sum_op -> EPSILON
        return expr;
    }

    /** handles the non-terminal signed_term */
    private ExpressionNode signedTerm()
    {
        // signed_term -> PLUSMINUS term
        if (elementSuivant.element == Element.PLUSMINUS)
        {
            boolean positive = elementSuivant.sequence.equals("+");
            nextElement();
            ExpressionNode t = term();
            if (positive)
                return t;
            else
                return new AdditionExpressionNode(t, false);
        }

        // signed_term -> term
        return term();
    }

    /** handles the non-terminal term */
    private ExpressionNode term()
    {
        // term -> factor term_op
        ExpressionNode f = factor();
        return termOp(f);
    }

    /** handles the non-terminal term_op */
    private ExpressionNode termOp(ExpressionNode expression)
    {
        // term_op -> MULTDIV factor term_op
        if (elementSuivant.element == Element.MULTDIV)
        {
            MultiplicationExpressionNode prod;

            // This means we are actually dealing with a product
            // If expr is not already a PRODUCT, we have to create one
            if (expression.getType() == ExpressionNode.MULTIPLICATION_NODE)
                prod = (MultiplicationExpressionNode) expression;
            else
                prod = new MultiplicationExpressionNode(expression, true);

            // reduce the input and recursively call sum_op
            boolean positive = elementSuivant.sequence.equals("*");
            nextElement();
            ExpressionNode f = signedFactor();
            prod.add(f, positive);

            return termOp(prod);
        }

        // term_op -> EPSILON
        return expression;
    }

    /** handles the non-terminal signed_factor */
    private ExpressionNode signedFactor()
    {
        // signed_factor -> PLUSMINUS factor
        if (elementSuivant.element == Element.PLUSMINUS)
        {
            boolean positive = elementSuivant.sequence.equals("+");
            nextElement();
            ExpressionNode t = factor();
            if (positive)
                return t;
            else
                return new AdditionExpressionNode(t, false);
        }

        // signed_factor -> factor
        return factor();
    }

    /** handles the non-terminal factor */
    private ExpressionNode factor()
    {
        // factor -> argument factor_op
        ExpressionNode a = argument();
        return factorOp(a);
    }

    /** handles the non-terminal factor_op */
    private ExpressionNode factorOp(ExpressionNode expr)
    {
        // factor_op -> RAISED expression
        if (elementSuivant.element == Element.RAISED)
        {
            nextElement();
            ExpressionNode exponent = signedFactor();

            return new ExponentiationExpressionNode(expr, exponent);
        }

        // factor_op -> EPSILON
        return expr;
    }

    /** handles the non-terminal argument */
    private ExpressionNode argument()
    {
        // argument -> FUNCTION argument
        if (elementSuivant.element == Element.FUNCTION)
        {
            int function = FunctionNode.stringToFunction(elementSuivant.sequence);
            nextElement();
            ExpressionNode expr = argument();
            return new FunctionNode(function, expr);
        }
        // argument -> OPEN_BRACKET sum CLOSE_BRACKET
        else if (elementSuivant.element == Element.OPEN_BRACKET)
        {
            nextElement();
            ExpressionNode expr = expression();
            if (elementSuivant.element != Element.CLOSE_BRACKET)
                throw new ParserException("Parenth�ses fermantes attendues", elementSuivant);
            nextElement();
            return expr;
        }

        // argument -> value
        return value();
    }

    /** handles the non-terminal value */
    private ExpressionNode value()
    {
        // argument -> NUMBER
        if (elementSuivant.element == Element.NUMBER)
        {
            ExpressionNode expr = new ConstantNode(elementSuivant.sequence);
            nextElement();
            return expr;
        }

        // argument -> VARIABLE
        if (elementSuivant.element == Element.VARIABLE)
        {
            ExpressionNode expr = new VariableNode(elementSuivant.sequence);
            nextElement();
            return expr;
        }

        if (elementSuivant.element == Element.EPSILON)
            throw new ParserException("fin inattendue de l'entr�e");
        else
            throw new ParserException("Expression erron�e .Symbole inattendu %s trouv�", elementSuivant);
    }

    /**
     * Remove the first element from the list and store the next element in elementSuivant
     */
    private void nextElement()
    {
        elements.pop();
        // at the end of input we return an epsilon element
        if (elements.isEmpty())
            elementSuivant = new Element(Element.EPSILON, "", -1);
        else
            elementSuivant = elements.getFirst();
    }
}
