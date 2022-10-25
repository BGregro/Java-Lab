import java.io.Serializable;

public class Beer implements Serializable {
    String name;
    String style;
    double strength;

    public Beer(String nev, String jelleg, double fok) {
        name = nev;
        style = jelleg;
        strength = fok;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public double getStrength() {
        return strength;
    }

    public String toString() {
        return "Name: " + name + ", Style: " + style + ", Strength: " + strength;
    }
}
