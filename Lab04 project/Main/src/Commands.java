import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Commands {
    protected void add(String[] inputList, ArrayList<Beer> beerList) {
        beerList.add(new Beer(inputList[1], inputList[2], Double.parseDouble(inputList[3])));
    }

    protected void list(String[] inputList, ArrayList<Beer> beerList) {
        Comparator<Beer> cmp = null;
        if (inputList.length > 1) {
            switch (inputList[1]) {
                case "name":
                    cmp = new NameComparator();
                case "style":
                    cmp = new StyleComparator();
                case "strength":
                    cmp = new StrengthComparator();
            }
            Collections.sort(beerList, cmp);
        }
        
        for (Beer element: beerList) {
            System.out.println(element.toString());
        }
    }

    protected void save(String[] inputList, ArrayList<Beer> beerList) {
        try {
            FileOutputStream fr = new FileOutputStream(inputList[1]);
            ObjectOutputStream oos = new ObjectOutputStream(fr);
            oos.writeObject(beerList);
            oos.close();
        } catch (IOException e) {
            System.out.println("Nem sikerült kiírni");
        }
    }

    protected ArrayList<Beer> load(String[] inputList) {
        try {
            FileInputStream fir = new FileInputStream(inputList[1]);
            ObjectInputStream is = new ObjectInputStream(fir);
            ArrayList<Beer> ujLista = (ArrayList<Beer>)is.readObject();
            
            is.close();
            return ujLista;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nem sikerült beolvasni");
        }
        return null;
    }
    
    protected void search(String[] inputList, ArrayList<Beer> beerList) {
        for (Beer sor: beerList) {
            if ("name".equals(inputList[1])) {
                if (sor.getName().equals(inputList[2]))
                    System.out.println(sor.toString());

            } else if ("style".equals(inputList[1])) {
                if (sor.getStyle().equals(inputList[2]))
                    System.out.println(sor.toString());

            } else if ("strength".equals(inputList[1])) {
                    if (sor.getStrength() == Double.parseDouble(inputList[2]))
                        System.out.println(sor.toString());
            }
        }
    }

    protected void find(String[] inputList, ArrayList<Beer> beerList) {
        for (Beer sor: beerList) {
            if ("name".equals(inputList[1])) {
                if (sor.getName().contains(inputList[2]))
                    System.out.println(sor.toString());

            } else if ("style".equals(inputList[1])) {
                if (sor.getStyle().contains(inputList[2]))
                    System.out.println(sor.toString()); 

            } else if ("strength".equals(inputList[1])) {
                if (sor.getStrength() >= Double.parseDouble(inputList[2]))
                    System.out.println(sor.toString());

            } else if("weaker".equals(inputList[1])) {
                if (sor.getStrength() <= Double.parseDouble(inputList[2]))
                    System.out.println(sor.toString());
            }
       }
    }

    protected void delete(String[] inputList, ArrayList<Beer> beerList) {
        Iterator<Beer> beerIt = beerList.iterator();
        while (beerIt.hasNext()) {
            Beer it = beerIt.next();
            if (it.getName().equals(inputList[1])) {
                beerIt.remove();
            }
        }
        
        /*
        try {
            beerList.remove(Collections.binarySearch(beerList, new Beer(inputList[1], null, 0), new NameComparator()));
        }
        catch (ArrayIndexOutOfBoundsException err) {
            System.err.println("szar");
        }
        */
    }

}
