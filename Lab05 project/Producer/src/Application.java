public class Application {
	public static void main(String args[]) throws InterruptedException {
		Fifo storage = new Fifo();
		Thread threads[] = new Thread[7];
		
		for(int i=0;i<3;i++)
			threads[i] = new Thread(new Producer(storage, "producer", 1000));

		for(int i=3;i<7;i++)
			threads[i] = new Thread(new Consumer(storage, "consumer", 100));
		
		for(int i=0;i<7;i++)
			threads[i].start();
	}
}