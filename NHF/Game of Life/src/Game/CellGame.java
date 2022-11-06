package Game;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class CellGame {
    private Cell[][] cellArray;
    

    public CellGame() {
        cellArray = new Cell[(Reference.GAME_HEIGHT/Reference.CELL_SIZE)][(Reference.GAME_WIDTH/Reference.CELL_SIZE)];
        randomFill();
    }

    // egyelőre random töltöm fel a cellákat
    public void fillArray() {

    }

    // kitölti randomizálva a tömböt
    public void randomFill() {
        Random rand = new Random();
        for (int i = 0; i < cellArray.length; ++i) {
            for (int j = 0; j < cellArray[i].length; ++j) {
                double randint = rand.nextDouble();
                cellArray[i][j] = 0.3<randint ? Cell.DEAD : Cell.ALIVE;//cellArray[i][j] = randint>0.5 ? Cell.DEAD : Cell.ALIVE;
            }
        }
    }

    public void resetGame() {
        for (int i = 0; i < cellArray.length; ++i) {
            for (int j = 0; j < cellArray[i].length; ++j) {
                cellArray[i][j] = Cell.DEAD;
            }
        }
    }

    public void updateGame() {
        Cell[][] previousCellArray = new Cell[(Reference.GAME_HEIGHT/Reference.CELL_SIZE)][(Reference.GAME_WIDTH/Reference.CELL_SIZE)];
        for (int i = 0; i < cellArray.length; ++i) {
            for (int j = 0; j < cellArray[i].length; ++j) {
                previousCellArray[i][j] = cellArray[i][j];
            }
        }

        // ideiglenesen itt a B../S..
        int B[] = {3};
        int S[] = {2, 3};
        
        for (int i = 0; i < cellArray.length; ++i) {
            for (int j = 0; j < cellArray[i].length; ++j) {
                int aliveN = getAliveNeighbours(i, j, previousCellArray);

                if (previousCellArray[i][j] == Cell.ALIVE && !containsInt(S, aliveN)) {
                    //System.out.println(aliveN);
                    cellArray[i][j] = Cell.DEAD;
                } 
                else if (previousCellArray[i][j] == Cell.DEAD && containsInt(B, aliveN)) {
                    cellArray[i][j] = Cell.ALIVE;
                }
            }
        }
    }

    private boolean containsInt(int[] array, int num) {
        for (int i = 0; i < array.length; ++i) {
            if(array[i] == num) {
                return true;
            }
        }
        return false;
    }

    // megadja, hogy hány db élő szomszédja van a megadot tindexen levő cellának
    private int getAliveNeighbours(int iIndex, int jIndex, Cell[][] cells) {
        int aliveCount = 0;
        for (int i = iIndex-1; i <= iIndex+1; ++i) {
            for (int j = jIndex-1; j <= jIndex+1; ++j) {
                if (i != iIndex || j != jIndex) {
                    try {
                        if (cells[i][j] == Cell.ALIVE)
                            ++aliveCount;
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        }
        return aliveCount;
    }

    // kirajzolja a jelenlegi állapotot
    public void drawGame(Graphics g) {
        for (int i = 0; i < cellArray.length; ++i) {
            for (int j = 0; j < cellArray[i].length; ++j) {
                g.setColor(cellArray[i][j].getColor());
                g.fillRect(j * Reference.CELL_SIZE, i * Reference.CELL_SIZE, Reference.CELL_SIZE, Reference.CELL_SIZE);
                g.setColor(Color.darkGray);
                g.drawRect(j * Reference.CELL_SIZE, i * Reference.CELL_SIZE, Reference.CELL_SIZE, Reference.CELL_SIZE);
            }
        }
    }

    // kattintás utáni beállítás
    public void changeSingleCell(int x, int y) {
        //System.out.println("x index: " + (int) Math.floor(x/Reference.CELL_SIZE) + " y index:" +(int) Math.floor(y/Reference.CELL_SIZE));
        cellArray[(int) Math.floor(y/Reference.CELL_SIZE)][(int) Math.floor(x/Reference.CELL_SIZE)] = Cell.ALIVE;
    }
}
