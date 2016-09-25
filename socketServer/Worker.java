import java.io.*;
import java.net.*;
 
 
public class Worker extends Thread {
 
	protected Socket clientSocket = null;
	protected MultiThreadedServer clientServerSocket = null;
 
	public Worker(MultiThreadedServer clientServerSocket, Socket clientSocket) {
		this.clientServerSocket = clientServerSocket;
		this.clientSocket = clientSocket;
	}
 
	@Override
	public void run() {
		try {
			setDaemon(true);
			InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
    	    DataInputStream in = new DataInputStream(input);
    	    DataOutputStream out = new DataOutputStream(output);
    	    String line = null;
    	    while(true) {
    	    	line = in.readUTF();
    	        System.out.println("Input msg : " + line);
    	        if (line.equals("/exit"))
	    	    {
	    	        clientServerSocket.stop();
	    	        break;
    	        }
    	        System.out.println("Sending back");
    	        out.writeUTF("Nice day for suicide. " + line);
    	        out.flush();
    	        System.out.println("Waiting next");
    	        System.out.println();
    	    }
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}