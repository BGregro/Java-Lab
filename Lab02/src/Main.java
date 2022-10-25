
public class Main {
	static public void main(String[] args) {
		Asztal a = new Asztal();
		a.ujJatek();
		
		Kezdo k1 = new Kezdo("k1"); a.addJatekos(k1);
		Kezdo k2 = new Kezdo("k2"); a.addJatekos(k2);
		Robot r1 = new Robot(); a.addJatekos(r1);
		
		a.kor();
		a.kor();
		a.kor();
		
		Asztal b = new Asztal();
		b.kor();
		
		Mester m = new Mester(2);
		Nyuszi ny = new Nyuszi("fehér");
		b.addJatekos(m);
		b.addJatekos(ny);
		
		for (int i = 0; i < 10; ++i) b.kor();
		
	}
}
