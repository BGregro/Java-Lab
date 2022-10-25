import java.util.Random;

public class Asztal {
	private Jatekos[] jatekosok = new Jatekos[10];
	private double tet;
	private int kor;
	private double goal;
	private int hossz = 0;
	
	public void ujJatek() {
		tet = 0;
		kor = 0;

		Random r = new Random();
		goal = r.nextDouble()*100 + 1;
	}
	
	public void addJatekos(Jatekos j) {		
		if (hossz < 10) {
			j.setAsztal(this);
			jatekosok[hossz++] = j;
			
		} else {
			System.out.println("Az asztal megtelt");
		}
	}
	
	
	public void emel(double d) {
		this.tet = tet + d;
	}
	
	public void kor() {
		try {
			if (hossz == 0) throw (new NincsJatekos("Senki sem ül az asztalnál"));
		} catch (RuntimeException NincsJatekos) {
			System.out.println("Senki sem ül az asztalnál");
		}
		
	
		for (int i = 0; i < hossz; ++i) {
			jatekosok[i].lep();
		}
		++kor;
	}

	public int getKor() { return kor; }

	public double getTet() { return tet; }
	
	
}


