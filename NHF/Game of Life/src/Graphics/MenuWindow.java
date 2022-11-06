package Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Game.Reference;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
// titlePanel.setBackground(Color.BLACK);

public class MenuWindow {
    private static JFrame window;

    private JComboBox<String> famousCombo;
    JTextField setB;
    JTextField setS;

    public void createMenu() {
        // Menu window + egyéb beállíása
        window = new JFrame("Game of Life");
        window.setSize(400, 400); // TODO referencbe a kezdő méretet?
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

        // a szabályok beállítására szolgáló tömbök inicializálása
        Reference.B = new int[9];
        Reference.S = new int[9];
        for (int i = 0; i < 9; ++i) {
            Reference.B[i] = -1;
            Reference.S[i] = -1;
        }
        // az alap állapot a Conway féle Game of Life
        Reference.B[0] = 3; Reference.S[0] = 2; Reference.S[1] = 3;

        // Cím
        //drawTitle();
        JLabel title = new JLabel("Game of Life", JLabel.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font bigFont = title.getFont().deriveFont(Font.PLAIN, 40f);
        title.setFont(bigFont);

        window.add(title);

        // szabályok beállítása
        setRules();

        // Load Button
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
            "Conway's Game of Life: B3/S23", 
            "Nathan Thompson's HighLife: B36/S23", 
            "Brian Silverman's Seeds: B2/S", 
            "Janko Gravner's Life without death: B3/S012345678", 
            "Kellie Evans' Gnarl: B1/S1", 
            "Edward Fredkin's Replicator: B1357/S1357", 
            "Gérard Y. Vichniac's Anneal: B4678/S35678",
            "David Bell's Day and Night: B3678/S34678",
            "Serviettes: B234/S",
            "Diamoeba: B35678/S5678",
            "Maze: B3/S12345",
            "Maze with mice: B37/S12345",
            "Jeremy Kun's Cave generator: B678/S345678",
            "Custom"
        };
        
        famousCombo = new JComboBox<String>(famousList);
        famousCombo.addActionListener(new FamousComboActionListener()); // combobox által kiválasztot elem alapján a B../S.. beállítása

        comboPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        comboPanel.add(famousCombo);
        window.add(comboPanel);
        
    // szabályok beállítására szolgáló felület
        JPanel rulePanel = new JPanel();
        rulePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sizeReference = new JLabel("012345678");

        // B (born)
        JLabel B = new JLabel("B: ", JLabel.RIGHT);
        setB = new JTextField("3");
        setB.setPreferredSize(new Dimension((int) sizeReference.getPreferredSize().getWidth() + 10, (int) sizeReference.getPreferredSize().getHeight() + 5));

        // S (survive)
        JLabel S = new JLabel("S: ", JLabel.RIGHT);
        setS = new JTextField("23");
        setS.setPreferredSize(new Dimension((int) sizeReference.getPreferredSize().getWidth() + 10, (int) sizeReference.getPreferredSize().getHeight() + 5));

        rulePanel.add(B);
        rulePanel.add(setB);
        rulePanel.add(S);
        rulePanel.add(setS);

        window.add(rulePanel);
    }

    private class FamousComboActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = (String) famousCombo.getSelectedItem();
            String[] split_selected = selected.split(" ");
            int splitLen = split_selected.length;
            String[] BS = split_selected[splitLen-1].split("/");

            String BText = "";
            String SText = "";

            if (selected != "Custom") {
                // B beállítása
                for (int i = 1; i < 10; ++i) {
                    if (i < BS[0].length()) {
                        Reference.B[i-1] = (int) (BS[0].charAt(i)-'0');
                        BText += BS[0].charAt(i);
                    } else {
                        Reference.B[i-1] = -1;
                    }
                }
                
                // S beállítása
                for (int i = 1; i < 10; ++i) {
                    if (i < BS[1].length()) {
                        Reference.S[i-1] = (int) (BS[1].charAt(i)-'0');
                        SText += BS[1].charAt(i);
                    } else {
                        Reference.S[i-1] = -1;
                    }
                }
                for (int i = 0; i < 9; ++i) {
                    System.out.println(Reference.B[i]);
                    System.out.println(Reference.S[i]);
                }
                setB.setText(BText);
                setS.setText(SText);
            }
        }
    }

    private class StartActionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameWindow gw = new GameWindow(window);
        }
    }
}
