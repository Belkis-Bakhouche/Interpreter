package Models;

import Utils.Checkable;
import java.util.regex.Pattern;

import java.util.regex.Matcher;
import Exceptions.InvalidVariableNameException;

public class Variable extends Symbole implements Checkable {

    private String nameKey;
    private Double value;

    public Variable(String string)  {
        super(string);
        nameKey = string;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
    public boolean valider_nom(String name) throws InvalidVariableNameException
   	{
   		
   		String[] Parts = name.split("");
   		boolean res=true;
   		
   		// V�rifier si le premier charact�re n'est pas un nombre.
   		if (isNumeric(Parts[0])) {
   			throw new InvalidVariableNameException("Nom de variable invalide ! S'IL VOUS PLAÎT entrer un nom commençant par une lettre .");
   		}
   		
   		Fonction_math_std fcts[] = Fonction_math_std.values();
   		Print_let cmds[] = Print_let.values();
   		
   		
   		// V�rifier que le nom de la variable n'est pas un nom r�sev� (let,print,cos,sin,log,sqrt,abs).
   	    for(Fonction_math_std fct: fcts) {
   	    	String f=fct.toString();
   	        if (name.equals(f)) {
   	        	throw new InvalidVariableNameException("Nom de variable réservé ! S'IL VOUS PLAÎT entrez un autre nom.");
   	        }
   	    }
   	    for(Print_let cmd: cmds) {
   	    	String c=cmd.toString();
   	    	if (name.equals(c)) {
   	    		throw new InvalidVariableNameException("Nom de variable réservé ! S'IL VOUS PLAÎT entrez un autre nom.");
   	    	}
   	    }
   	    
   	    // V�rifier que le nom de la variable ne contient pas des op�rateurs.
   	    String re=".*[+-/*^].*";
   	    Pattern pt = Pattern.compile(re);
   	    Matcher mt = pt.matcher(name);
   	    if ( mt.matches() ){
   	    	throw new InvalidVariableNameException("Nom de variable non valide ! Veuillez entrer un nom qui ne contient pas de symboles.");
   	    	}  
   		return res;
   	}
    
   	
   	
   	   // Une m�thode qui v�rifie si le charact�re est un nombre
   	public static boolean isNumeric(String s) {
           return s != null && s.matches("[-+]?\\d*\\.?\\d+");
       } 


    @Override
    public boolean checkBnfSyntax() {
        //if super.string matches the regex pattern we return true
        return string.matches(Checkable.VARIABLE_REGEX);
    }

	
}
