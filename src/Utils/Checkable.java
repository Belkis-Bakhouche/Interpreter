package Utils;

public interface Checkable {
	 String NOMBRE_REGEX = "[\\d]+";
     String VARIABLE_REGEX = "^[a-zA-Z_]\\w*$"  ;

     String ELEMENT_REGEX = NOMBRE_REGEX + "|" + VARIABLE_REGEX  ;                      // === <nombre> | <variable>      to add later : | "("expression")" | <nom-fonction-standard>"("expression")"
     String FACTEUR_REGEX = ELEMENT_REGEX + "(^" + ELEMENT_REGEX + ")*";                // === <element> [["*" | "/"] <element> ]...
     String TERM_REGEX = FACTEUR_REGEX + "((\\*|/)?" + FACTEUR_REGEX + ")*";            // === <facteur> [[ "*" | "/" ] <facteur> ]...
     String EXPRESSION_REGEX = "-?" + TERM_REGEX + "((\\+|-)?" + TERM_REGEX + ")*";     // === ["-"] <term> [["+" | "-"] <term> ]...

     boolean checkBnfSyntax();

}
