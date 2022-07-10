package Models;

public abstract class Symbole {
	 public String string;

	    public Symbole(String string) {
	        this.string = string;
	    }

	    public String getString() {
	        return string;
	    }

	    public void setString(String string) {
	        this.string = string;
	    }
}
