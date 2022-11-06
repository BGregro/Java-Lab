package Graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import Game.Reference;

public class GameWindow {
    protected static GamePanel gamePanel = new GamePanel();
    private SettingsPanel settingsPanel = new SettingsPanel();
    protected static Thread game = new Thread(gamePanel);

    public GameWindow(JFrame window) {
    // a menu start gomb megnyomása után az ablak átalakul
        window.getContentPane().removeAll();
        window.setSize(Reference.WINDOW_WIDTH, Reference.WINDOW_HEIGHT);
        window.setResizable(false);
        window.repaint();
        window.setLayout(new BorderLayout()); // ?
    
    // window kettévágása a game és a setttings panelnekre
        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, settingsPanel); 
        splitpane.setDividerLocation(Reference.GAME_WIDTH);
        splitpane.setEnabled(false);

        window.add(splitpane); // ezzel a panelek is hozzáadva a window-hoz
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        game.start();
    }


}