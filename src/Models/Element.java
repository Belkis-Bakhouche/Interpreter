package Models;

public class Element {
	 /** Element id for the epsilon terminal */
    public static final int EPSILON = 0;
    /** Element id for plus or minus */
    public static final int PLUSMINUS = 1;
    /** Element id for multiplication or division */
    public static final int MULTDIV = 2;
    /** Element id for the exponentiation symbol */
    public static final int RAISED = 3;
    /** Element id for function names */
    public static final int FUNCTION = 4;
    /** Element id for opening brackets */
    public static final int OPEN_BRACKET = 5;
    /** Element id for closing brackets */
    public static final int CLOSE_BRACKET = 6;
    /** Element id for numbers */
    public static final int NUMBER = 7;
    /** Element id for variable names */
    public static final int VARIABLE = 8;

    /** the Element identifier */
    public final int element;
    /** the string that the Element was created from */
    public final String sequence;
    /** the position of the Element in the input string */
    public final int pos;

    /**
     * Construct the element with its values
     * @param element the element ID
     * @param sequence the string that the element was created from
     * @param pos the position of the element in the input string
     */
    public Element(int element, String sequence, int pos)
    {
        super();
        this.element = element;
        this.sequence = sequence;
        this.pos = pos;
    }


}
