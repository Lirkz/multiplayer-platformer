package gamelogic;

import java.net.*;
import gamelogic.player.Player;
import java.io.*;

public class ConnectionHandler{

	public final int port = 8911;
	public Player player;
	ServerSocket listener;
	Socket connection;
	
	public void connect(){
		try {
            listener = new ServerSocket(port);
            System.out.println("Listening on port " + port);
            connection = listener.accept();
        }
        catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            return;
        }
		new Sender().start();
		new Reciever().start();
		
	}
	class Sender extends Thread{
		public ObjectOutputStream oos;
		
		public void run() {
			try {
				oos = new ObjectOutputStream(connection.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			try {
				oos.writeObject(player.state);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	class Reciever extends Thread{
		
	}
	
}
