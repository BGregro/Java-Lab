
public abstract class Jatekos {
	protected Asztal asztal;
	
	public Jatekos() {}
	
	public void lep() {
		System.out.println(asztal.getKor());
		System.out.println(asztal.getTet());
	}
	
	public void setAsztal(Asztal a) { asztal = a; }
}
