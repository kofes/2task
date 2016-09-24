import java.net.*;
import java.io.*;
 
public class MultiThreadedServer implements Runnable{
 
    protected int          serverPort   = 9000; //default
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
 
    public MultiThreadedServer(int port){
        serverPort = port;
    }
 
    @Override
    public void run(){
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            new Thread(
                new Worker(this, clientSocket)
            ).start();
        }
        System.out.println("Server Stopped.") ;
    }
 
    //synchronized - выполняется только одним потоком одновременно
 
    private synchronized boolean isStopped() {
        return this.isStopped;
    }
 
    public synchronized void stop(){ 
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }
 
    private void openServerSocket() {
        System.out.println("Opening server socket...");
     try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }
 
}