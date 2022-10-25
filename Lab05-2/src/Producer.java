import static java.lang.Thread.sleep;

public class Producer implements Runnable {
    private String uzenet;
    private Fifo fifo;

    public Producer(String str, Fifo storage) {
        uzenet = str;
        fifo = storage;
    }

    public void go() throws InterruptedException {
        int i = 0;
        while (true) {
            fifo.put((uzenet + " " + i));
            System.out.println("produced" + " " + i++ + " " + System.currentTimeMillis());
            sleep(1000);
        }
    }

    public void run() {
        try {
            go();
        } catch (InterruptedException ex) {
            System.out.println("xd");
        }
    }
}
