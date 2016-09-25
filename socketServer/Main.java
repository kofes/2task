
public class Main {
    public static final int PORT_WORK = 9000;
    public static final int PORT_STOP = 9001;
 
    public static void main(String[] args) {
        MultiThreadedServer server = new MultiThreadedServer(PORT_WORK);
        Thread tmp = new Thread(server);
        tmp.start();
        try {
			tmp.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println("Stopping Server");
        server.stop();	
    }
}