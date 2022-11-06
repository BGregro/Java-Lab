package Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
// titlePanel.setBackground(Color.BLACK);

public class MenuWindow {
    private static JFrame window;

    public void createMenu() {
        // Menu window beállíása
        window = new JFrame("Game of Life");
        window.setSize(400, 400); // TODO referencbe a kezdő méretet?
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

        // Cím
        //drawTitle();
        
        JLabel title = new JLabel("Game of Life", JLabel.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font bigFont = title.getFont().deriveFont(Font.PLAIN, 40f);
        title.setFont(bigFont);

        window.add(title);

        // szabályok beállítása
        setRules();

        // load savefiles
        JPanel loadPanel = new JPanel();
        JButton loadButton = new JButton("Load");
        loadPanel.add(loadButton);
        window.add(loadPanel);

        // Start Button - DONE
        JPanel startPanel = new JPanel(); startPanel.setLayout(null);
        JButton start = new JButton("Start");
        Font startFont = title.getFont().deriveFont(Font.PLAIN, 20f);
        start.setFont(startFont);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.addActionListener(new StartActionButtonListener());
        
        startPanel.add(start);
        window.add(start);

        window.setVisible(true);
    }

    private void setRules() {
    // famous rules list + ComboBox
        JPanel comboPanel = new JPanel();
        String[] famousList = {
            "Custom", 
            "Conway's Game of Life: B3/S23", 
            "Nathan Thompson's HighLife: B36/S23", 
            "Brian Silverman's Seeds: B2/S", 
            "Janko Gravner's Life without death: B3/S012345678", 
            "Kellie Evans' Gnarl: B1/S1", 
            "Edward Fredkin's Replicator: B1357/S1357", 
            "Gérard Y. Vichniac's Anneal: B4678/S35678",
            "David Bell's Day and Night: B3678/S34678",
            "Serviettes: B234/S",
            "Maze: B3/S12345",
            "Maze with mice: B37/S12345",
            "Jeremy Kun's Cave generator: B678/S345678"
        };
        JComboBox<String> famousCombo = new JComboBox<String>(famousList);
        comboPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        comboPanel.add(famousCombo);
        window.add(comboPanel);
        
    // szabályok beállítására szolgáló felület
        JPanel rulePanel = new JPanel();
        rulePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel numReference = new JLabel("012345678");

        // B (born)
        JLabel B = new JLabel("B: ", JLabel.RIGHT);
        JTextField setB = new JTextField();
        setB.setPreferredSize(new Dimension((int) numReference.getPreferredSize().getWidth() + 10, (int) numReference.getPreferredSize().getHeight() + 5));

        // S (survive)
        JLabel S = new JLabel("S: ", JLabel.RIGHT);
        JTextField setS = new JTextField();
        setS.setPreferredSize(new Dimension((int) numReference.getPreferredSize().getWidth() + 10, (int) numReference.getPreferredSize().getHeight() + 5));

        rulePanel.add(B);
        rulePanel.add(setB);
        rulePanel.add(S);
        rulePanel.add(setS);

        window.add(rulePanel);
    }

    private class StartActionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameWindow gw = new GameWindow(window);
        }
    }
}
