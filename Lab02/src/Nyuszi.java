
public class Nyuszi extends Jatekos {
	private String szin;
	
	public Nyuszi(String sz) {
		szin = sz;
	}
	
	public String toString() {
		return szin;
	}
	
	public void lep() {
		System.out.println(szin);
		System.out.println(asztal.getKor());
		
		if (asztal.getTet() < 50) {
			asztal.emel(5);
		} else {
			System.out.println(asztal.getTet());
			System.out.println("Húha!");
		}
	}
}
