import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1. feladat
        Beer b1 = new Beer("IPA", "vilagos", 4.5);
        Beer b2 = new Beer("APA", "vilagos", 5.5);

        //System.out.println(b1.toString());
        //System.out.println(b2.toString());

        // ArrayList
        ArrayList<Beer> beerList = new ArrayList<Beer>();
        beerList.add(b1);
        beerList.add(b2);
        beerList.add(new Beer("Guiness", "stout", 4.2));
        beerList.add(new Beer("Arany aszok", "vilagos", 4));

        // beolvasás + commandok
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        Commands cmd = new Commands();

        while (true) {
            String input;
            try {
                input = br.readLine();
            } catch (IOException e) {
                break;
            }

            if (input == null) break;
            String[] inputList = input.split(" ");

            /*
            System.out.println(inputList[0]);
            System.out.println(inputList.length);
            */

            if (inputList[0] == "exit") {
                break;
            } else if ("add".equals(inputList[0])) {
                cmd.add(inputList, beerList);

            } else if ("list".equals(inputList[0])) {
                cmd.list(inputList, beerList);

            } else if ("load".equals(inputList[0])) {
                beerList = cmd.load(inputList);

            } else if ("save".equals(inputList[0])) {
                cmd.save(inputList, beerList);

            } else if ("search".equals(inputList[0])) {
                cmd.search(inputList, beerList);

            } else if ("find".equals(inputList[0])) {
                cmd.find(inputList, beerList);

            } else if ("delete".equals(inputList[0])) {
                cmd.delete(inputList, beerList);
            }

        }
    }
}
