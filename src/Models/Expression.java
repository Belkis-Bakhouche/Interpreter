package Models;

import Utils.Checkable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression extends Symbole implements Checkable {

    public Expression(String string) {
        super(string);
    }

    public String formatExpressionSpaces(){
        Pattern pattern = Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\+\\-\\*/\\(\\)]))");
        Matcher m = pattern.matcher(string.strip());
        StringBuilder sb = new StringBuilder();

        while(m.find()) {
            sb.append(m.group()+" ");
        }
        String str = sb.toString();
        return str;
    }

    @Override
    public boolean checkBnfSyntax() {
        //if the super.string matches the Expression regex we return true
        return string.matches(Checkable.EXPRESSION_REGEX);
//
//         Pattern simpleLang = Pattern.compile("\\s*-?\\d+(\\s*[-+*%/]\\s*-?\\d+)*\\s*");
//         Pattern innerParen = Pattern.compile("[(]([^()]*)[)]");
//            while (string.contains(")") || string.contains("(")) {
//                Matcher m = innerParen.matcher(string);
//                if (m.find()) {
//                    if (!simpleLang.matcher(m.group(1)).matches()) {
//                        return false;
//                    }
//                    string = string.substring(0, m.start()) + " 1 " + string.substring(m.end());
//                } else {
//                    // we have parens but not an innermost paren-free region
//                    // This implies mismatched parens
//                    return false;
//                }
//            }
//            return simpleLang.matcher(string).matches();

        }


    /**
     * ExpressionEvaluator
     *
     */
        public int evaluate()
        {
            char[] tokens = string.toCharArray();

            // Stack for numbers: 'values'
            Stack<Integer> values = new
                    Stack<Integer>();

            // Stack for Operators: 'ops'
            Stack<Character> ops = new
                    Stack<Character>();

            for (int i = 0; i < tokens.length; i++)
            {

                // Current token is a
                // whitespace, skip it
                if (tokens[i] == ' ')
                    continue;

                // Current token is a number,
                // push it to stack for numbers
                if (tokens[i] >= '0' &&
                        tokens[i] <= '9')
                {
                    StringBuffer sbuf = new
                            StringBuffer();

                    // There may be more than one
                    // digits in number
                    while (i < tokens.length &&
                            tokens[i] >= '0' &&
                            tokens[i] <= '9')
                        sbuf.append(tokens[i++]);
                    values.push(Integer.parseInt(sbuf.
                            toString()));

                    // right now the i points to
                    // the character next to the digit,
                    // since the for loop also increases
                    // the i, we would skip one
                    //  token position; we need to
                    // decrease the value of i by 1 to
                    // correct the offset.
                    i--;
                }

                // Current token is an opening brace,
                // push it to 'ops'
                else if (tokens[i] == '(')
                    ops.push(tokens[i]);

                    // Closing brace encountered,
                    // solve entire brace
                else if (tokens[i] == ')')
                {
                    while (ops.peek() != '(')
                        values.push(applyOp(ops.pop(),
                                values.pop(),
                                values.pop()));
                    ops.pop();
                }

                // Current token is an operator.
                else if (tokens[i] == '+' ||
                        tokens[i] == '-' ||
                        tokens[i] == '*' ||
                        tokens[i] == '/')
                {
                    // While top of 'ops' has same
                    // or greater precedence to current
                    // token, which is an operator.
                    // Apply operator on top of 'ops'
                    // to top two elements in values stack
                    while (!ops.empty() &&
                            hasPrecedence(tokens[i],
                                    ops.peek()))
                        values.push(applyOp(ops.pop(),
                                values.pop(),
                                values.pop()));

                    // Push current token to 'ops'.
                    ops.push(tokens[i]);
                }
            }

            // Entire expression has been
            // parsed at this point, apply remaining
            // ops to remaining values
            while (!ops.empty())
                values.push(applyOp(ops.pop(),
                        values.pop(),
                        values.pop()));

            // Top of 'values' contains
            // result, return it
            return values.pop();
        }

        // Returns true if 'op2' has higher
        // or same precedence as 'op1',
        // otherwise returns false.
        public  boolean hasPrecedence(char op1, char op2)
        {
            if (op2 == '(' || op2 == ')')
                return false;
            if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
                return false;
            else
                return true;
        }

        // A utility method to apply an
        // operator 'op' on operands 'a'
        // and 'b'. Return the result.
        public  int applyOp(char op, int b, int a)
        {
            switch (op)
            {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    if (b == 0)
                        throw new
                                UnsupportedOperationException(
                                "Cannot divide by zero");
                    return a / b;
            }
            return 0;
        }

    }



