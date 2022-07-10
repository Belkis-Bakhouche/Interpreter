package EvaluationTree;

import Exceptions.ParserException
;
import EvaluationTree.ExpressionNodes.FunctionNode;
import Models.Element;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymboleAnalyzer {
	 /**
     * Internal class holding the information about a token type.
     */
    private class SymboleInfo {
        /** the regular expression to match against */
        public final Pattern regex;
        /** the token id that the regular expression is linked to */
        public final int symboleID;

        /**
         * Construct TokenInfo with its values
         */
        public SymboleInfo(Pattern regex, int token)
        {
            super();
            this.regex = regex;
            this.symboleID = token;
        }
    }

    /**
     * a list of TokenInfo objects
     *
     * Each token type corresponds to one entry in the list
     */
    private LinkedList<SymboleInfo> symboleInfos;

    /** the list of tokens produced when tokenizing the input */
    private LinkedList<Element> elements;

    /** a tokenizer that can handle mathematical expressions */
    private static SymboleAnalyzer expressionAnalyzer = null;



    /**
     * Default constructor
     */

    public SymboleAnalyzer()
    {
        super();
        symboleInfos = new LinkedList<SymboleInfo>();
        elements = new LinkedList<Element>();
    }

    /**
     * A static method that returns a tokenizer for mathematical expressions
     * @return a tokenizer that can handle mathematical expressions
     */
    public static SymboleAnalyzer getExpressionAnalyzer()
    {
        if (expressionAnalyzer == null)
            expressionAnalyzer = createExpressionAnalyzer();
        return expressionAnalyzer;
    }

    /**
     * A static method that actually creates a tokenizer for mathematical expressions
     * @return a tokenizer that can handle mathematical expressions
     */
    private static SymboleAnalyzer createExpressionAnalyzer()
    {
        SymboleAnalyzer symboleAnalyzer = new SymboleAnalyzer();

        symboleAnalyzer.add("[+-]", Element.PLUSMINUS);
        symboleAnalyzer.add("[*/]", Element.MULTDIV);
        symboleAnalyzer.add("\\^", Element.RAISED);

        String funcs = FunctionNode.getAllFunctions();
        symboleAnalyzer.add("(" + funcs + ")(?!\\w)", Element.FUNCTION);

        symboleAnalyzer.add("\\(", Element.OPEN_BRACKET);
        symboleAnalyzer.add("\\)", Element.CLOSE_BRACKET);
        symboleAnalyzer.add("(?:\\d+\\.?|\\.\\d)\\d*(?:[Ee][-+]?\\d+)?", Element.NUMBER);
        symboleAnalyzer.add("[a-zA-Z]\\w*", Element.VARIABLE);

        return symboleAnalyzer;
    }

    /**
     * Add a regular expression and a token id to the internal list of recognized tokens
     * @param regex the regular expression to match against
     * @param token the token id that the regular expression is linked to
     */
    public void add(String regex, int token)
    {
        symboleInfos.add(new SymboleInfo(Pattern.compile("^("+regex+")"), token));
    }

    /**
     * Tokenize an input string.
     *
     * The reult of tokenizing can be accessed via getTokens
     *
     * @param str the string to tokenize
     */
    public void analyzeSymboles(String str)
    {
        String s = str.trim();
        int totalLength = s.length();
        elements.clear();
        while (!s.equals(""))
        {
            int remaining = s.length();
            boolean match = false;
            for (SymboleInfo info : symboleInfos)
            {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    elements.add(new Element(info.symboleID, tok, totalLength - remaining));
                    break;
                }
            }
            if (!match)
                throw new ParserException("Symboles inattendu en entr�e: " + s);
        }
    }

    
    public LinkedList<Element> getElements() {
        return elements;
    }

}
