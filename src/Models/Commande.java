package Models;

import Exceptions.TooLongLineException;

public class Commande {

    private String cmdLine;

    public Commande(){
    }

    public Commande(String line)  {
        this.cmdLine = line.strip().toLowerCase();
    }

    public String getCmdLine() {
        return cmdLine;
    }

    public void setCmdLine(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    public String getCmdBody() {
        return cmdLine.replace(getCommandeName(),"").strip();
    }

    public String getCommandeName(){
        String[] arr ;
        arr = cmdLine.strip().split(" ");
        return arr[0];
    }
    public void longuer_valide() throws TooLongLineException{
		if (this.cmdLine.length() > 150)
		{
			throw new TooLongLineException("Ligne de commande trop longue");
		}
	}

}
