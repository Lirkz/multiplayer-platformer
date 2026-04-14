package gamelogic;

import java.net.*;
import gamelogic.player.PlayerState;
import java.io.*;

public class Server {
	private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 8911;
    public static Connection player1C;
    public static Connection player2C;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        Socket socket = server.accept();
        player1C=new Connection(socket);
        socket=server.accept();
        player2C=new Connection(socket);
        player1C.start();
        player2C.start();
        
    }
    
    
    static class Connection extends Thread{
    	
    	public ObjectOutputStream oos;
    	public ObjectInputStream ois;
    	Socket socket;
    	
    	public Connection(Socket s) {
    		socket=s;
    	}
    	
    	public void run() {
    		if (player1C==this) {
    			try {
					ois = new ObjectInputStream(socket.getInputStream());
					oos = new ObjectOutputStream(player2C.socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			while(true) {
    				try {
    					PlayerState state = (PlayerState)ois.readObject();
						oos.writeObject(state);
						oos.flush();
					} catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    		else {
    			try {
    				oos = new ObjectOutputStream(player1C.socket.getOutputStream());
					ois = new ObjectInputStream(socket.getInputStream());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			while(true) {
    				try {
    					PlayerState state = (PlayerState)ois.readObject();
						oos.writeObject(state);
						oos.flush();
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
						break;
					}
    			}
    		}
    	}
    }
}
