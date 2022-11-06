package Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.Component;
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
        window.setSize(400, 400);
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
        String[] frList = {"Conway's Game of Life: B3/S23", "Nathan Thompson's HighLife: B36/S23", "Brian Silverman's Seeds: B2/S", "Custom"};
        JComboBox<String> frCombo = new JComboBox<String>(frList);
        comboPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        comboPanel.add(frCombo);
        window.add(comboPanel);
        
    // szabályok beállítására szolgáló felület
        JPanel rulePanel = new JPanel(new GridLayout(1, 4));
        // B (born)
        JLabel B = new JLabel("B: ");
        JTextField setB = new JTextField("");
        // S (survive)
        JLabel S = new JLabel("S: ");
        JTextField setS = new JTextField("");

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
