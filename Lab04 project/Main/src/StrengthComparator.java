import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer> {
    public int compare(Beer sor1, Beer sor2) {
        if(sor1.strength == sor2.strength)
			return 0;
		else if(sor1.strength < sor2.strength)
			return -1;
		else 
			return 1;
    }
}
