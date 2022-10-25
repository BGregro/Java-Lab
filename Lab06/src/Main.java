import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Beer> beerList = new ArrayList<Beer>();
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, Command> commands = new HashMap<String, Command  >();
    static HashMap<String, Comparator<Beer>> comps = new HashMap<String, Comparator<Beer>>();
    
    static {
        comps.put("name", Comparator.comparing(b -> b.getName()));
        comps.put("style", Comparator.comparing(b -> b.getStyle()));
        comps.put("strength", Comparator.comparingDouble(b -> b.getStrength()));
    }
    public static void main(String[] args) {
        commands.put("add", Main::add);
        commands.put("list", Main::list);
        commands.put("save", Main::save);
        commands.put("load", Main::load);
        commands.put("search", Main::search);
        commands.put("find", Main::find);
        commands.put("delete", Main::delete);
        commands.put("exit", (cmd) -> System.exit(0) );

        String[] cmd;
        while (true) {
            cmd = sc.nextLine().split(" ");
            if (commands.get(cmd[0]) == null)
                System.out.println("Unknown command...");
            else
                commands.get(cmd[0]).execute(cmd);
        }
    }
    
    protected static void add(String[] cmd) {
        beerList.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
    }
    
    static List<String> lparams = new LinkedList<String>(comps.keySet());
    
    protected static void list(String[] cmd) {
        if (cmd.length > 1) {
            lparams.remove(cmd[1]);
            lparams.add(0, cmd[1]);
        }
        //Comparator<Beer> cmp = comps.get("name");
        ArrayList<Beer> ordered = new ArrayList<>(beerList);
        Comparator<Beer> comparator = comps.get(lparams.get(0)).thenComparing(comps.get(lparams.get(1))).thenComparing(comps.get(lparams.get(2)));
        Collections.sort(ordered, comparator);
        for (Beer b : ordered) {
            System.out.println(b.toString());
        }
    }
    
    protected static void save(String[] cmd) {
        try {
            FileOutputStream fos = new FileOutputStream(cmd[1]);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(beerList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
    
    protected static void load(String[] cmd) {
        try {
            FileInputStream fis = new FileInputStream(cmd[1]);
            ObjectInputStream ois = new ObjectInputStream(fis);
            beerList = (ArrayList<Beer>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    protected static void search(String[] cmd) {
        for (Beer b : beerList) {
            if ("name".equals(cmd[1])) {
                if (cmd[2].equals(b.getName()))
                    System.out.println(b.toString());
            } else if ("style".equals(cmd[1])) {
                if (cmd[2].equals(b.getStyle()))
                    System.out.println(b.toString());
            } else if ("strength".equals(cmd[1])) {
                if (b.getStrength() == Double.parseDouble(cmd[2]))
                    System.out.println(b.toString());
            }
        }
    }
    
    protected static void find(String[] cmd) {
        for (Beer b : beerList) {
            if ("name".equals(cmd[1])) {
                if (b.getName().contains(cmd[2]))
                    System.out.println(b.toString());
            } else if ("style".equals(cmd[1])) {
                if (b.getStyle().contains(cmd[2]))
                    System.out.println(b.toString());
            } else if ("strength".equals(cmd[1])) {
                if (b.getStrength() >= Double.parseDouble(cmd[2]))
                    System.out.println(b.toString());
            } else if ("weaker".equals(cmd[1])) {
                if (b.getStrength() <= Double.parseDouble(cmd[2]))
                    System.out.println(b.toString());
            }
        }
    }

    protected static void delete(String[] cmd) {
        Collections.sort(beerList, (b1, b2) -> b1.getName().compareTo(b2.getName()));
        int idx = Collections.binarySearch(beerList, new Beer(cmd[1], null, 0), (b1, b2) -> b1.getName().compareTo(b2.getName()));
        beerList.remove(idx);
    }
}