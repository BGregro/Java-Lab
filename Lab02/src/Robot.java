
public class Robot extends Jatekos {
	private static int id = 0;
	private int peldanydID = 0;
	
	public Robot() {
		++id;
		peldanydID = id;
	}
	
	public String toString() {
		return "Robot" + peldanydID;
	}
	
	public void lep() {
		System.out.println(this.toString());
		System.out.println(asztal.getKor());
	}
}
 