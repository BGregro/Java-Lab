
public class Kezdo extends Jatekos {
	private String nev;
	
	public Kezdo(String n) {
		nev = n;
	}
	
	public String toString() {
		return nev;
	}
	
	public void lep() {
		System.out.println(this.toString());
		System.out.println(asztal.getKor());
		
		if (asztal.getKor() % 2 == 0) 
			asztal.emel(1);
	}
	
	
		
}
