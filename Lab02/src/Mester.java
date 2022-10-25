
public class Mester extends Jatekos {
	private double fok;
	
	public Mester(double f) {
		fok = f;
	}
	
	public String toString() {
		return "Mester, fokozat: " + fok;
	}
	
	public void lep() {
		System.out.println(fok);
		System.out.println(asztal.getKor());
		
		double szazalek = 1 + fok/100;
		asztal.emel(asztal.getTet()*szazalek);
	}
}
