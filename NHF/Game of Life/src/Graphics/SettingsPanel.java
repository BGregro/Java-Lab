package Graphics;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Game.Reference;

public class SettingsPanel extends JPanel {
    // start gomb feliratának állításához
    JButton startButton;
    private boolean started = false;
    private int setStartState = 0; // ha fut akkor 1, ha meg van állítva akkor 0
    private JSlider gameSpeedSlider;

    public SettingsPanel() {
        // Slider: szimuláció iteráció sebességét állító slider a panel tetején
            // TODO slider feliratot és beosztást megcsinálni
        gameSpeedSlider = new JSlider(Reference.MIN_SPEED, Reference.MAX_SPEED, Reference.START_SPEED); 
        gameSpeedSlider.addChangeListener(new SliderChangeListener());

        gameSpeedSlider.setMajorTickSpacing(20);
        gameSpeedSlider.setMinorTickSpacing(5);
        gameSpeedSlider.setPaintTicks(true);
        gameSpeedSlider.setPaintLabels(true);

        add(gameSpeedSlider);

        // Random gomb: randomizálja az élő cellákat (futás közben nem lehet, vagy ha már el van indítva?) - DONE
        JButton randomButton = new JButton("Randomize");
        randomButton.addActionListener(new RandomButtonActionListener());
        add(randomButton);

        // Save gomb: lementi az aktuális játék állapotot
        JButton saveButton = new JButton("Save");
        add(saveButton);

        // Load gomb: be lehet vele tölteni egy korábban lementett állapotot
        JButton loadButton = new JButton("Load");
        add(loadButton);

        // Start gomb: elindítja a szimulációt és utána meg lehet állítani - DONE
        startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonActionListener());
        add(startButton);

        // Reset gomb: reseteli a pályát, mindegyik mező halott - DONE
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonActionListener());
        add(resetButton);
    }

    // ActionListeners - a gombok megnyomásához
    private class StartButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            started = true;

            // Resume state
            if (setStartState == 0) {
                Reference.RUNNING = true;
                startButton.setText("Pause");
                ++setStartState;

                if (Reference.CURRENT_SPEED == 0 && started) {
                    Reference.CURRENT_SPEED = 5; // erre állítja be a játék sebességét, ha a slider le lett véve 0-ra és a resume gomb meg lett nyomva
                    gameSpeedSlider.setValue(Reference.CURRENT_SPEED);
                }

                synchronized (GameWindow.gamePanel) {
                    GameWindow.gamePanel.notify();
                }
            // Pause state
            } else if (setStartState == 1) {
                Reference.RUNNING = false;
                startButton.setText("Resume");
                --setStartState;
            }
        }
    }

    private class RandomButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Reference.RUNNING == false && !started) {
                GameWindow.gamePanel.getCellGame().randomFill();
                GameWindow.gamePanel.update();
            }
        }
    }

    private class ResetButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameWindow.gamePanel.getCellGame().resetGame();
            GameWindow.gamePanel.update();
            startButton.setText("Start");
            started = false;
            setStartState = 0;
            Reference.RUNNING = false;
            gameSpeedSlider.setValue(Reference.START_SPEED);
        }
    }

    // ChangeListener sliderhez
    private class SliderChangeListener implements ChangeListener {
        private boolean sliderZero = false;
        @Override
        public void stateChanged(ChangeEvent e) {
            Reference.CURRENT_SPEED = gameSpeedSlider.getValue();
            
            if (Reference.CURRENT_SPEED == 0 && started) {
                sliderZero = true;
                // Pause state funkciói
                Reference.RUNNING = false;
                startButton.setText("Resume");
                setStartState = 0;

            } else if (Reference.RUNNING == false && started && sliderZero) {
                sliderZero = false;
                // Resume state funkciói
                Reference.RUNNING = true;
                startButton.setText("Pause");
                setStartState = 1;
                synchronized (GameWindow.gamePanel) {
                    GameWindow.gamePanel.notify();
                }
            }
        }   
    }
}

