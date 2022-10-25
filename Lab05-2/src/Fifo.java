import java.util.ArrayList;

public class Fifo {
    private ArrayList<String> fifo = new ArrayList<>(10);
    private int maxSize = 10;
    private int size = 0;

    public synchronized void put(String str) throws InterruptedException {
        while (size == maxSize) {
            this.wait();    
        }
        fifo.add(str);
    }

    public synchronized String get() {
        while (size == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String last = fifo.get(0);
        fifo.remove(0);
        return last;
    }
}
