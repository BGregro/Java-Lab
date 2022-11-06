package Graphics;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import static java.lang.Thread.sleep;

import Game.CellGame;
import Game.Reference;

public class GamePanel extends JPanel  implements Runnable {
    private CellGame game = new CellGame();

    public GamePanel() {
        addMouseListener(new ClickMouseListener());
    }

    @Override
    public synchronized void run() {
        // játék működése:
        update();
        while (true) {
            while (!Reference.RUNNING) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                sleep(1000/Reference.CURRENT_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update();
        }
    }

    // TODO a current speed alapján megcsinálni a játék sebességének változását
    public void update() {
        game.updateGame();
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.drawGame(g);
    }

    public CellGame getCellGame() {
        return game;
    }

    // a játékmezőbe való kattintást érzékeli és az ott levő cellákat élővé/halottá teszi
    public class ClickMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

    }
}
