package Game;

public class Reference {
    public static int[] B;
    public static int[] S;

    // itt csak a játék ablak adatai vannak, a menü ablakhoz nem kell
    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1080;

    public static final int SETTINGS_WIDTH = WINDOW_WIDTH/5;
    public static final int SETTINGS_HEIGHT = WINDOW_HEIGHT;

    public static final int GAME_WIDTH = 4*WINDOW_WIDTH/5;
    public static final int GAME_HEIGHT = WINDOW_HEIGHT;

    public static final int CELL_SIZE = 10; // TODO? normálisan megadni a cella méretet

    public static boolean RUNNING = false;

    // játék sebességének állítása (iteráció/sec)
    public static final int MIN_SPEED = 0;
    public static final int MAX_SPEED = 100;
    public static int START_SPEED = 50;
    public static int CURRENT_SPEED = 50; // itt megadott érték a kezdő érték a slideren
}
