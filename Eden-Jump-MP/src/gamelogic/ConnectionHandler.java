package gamelogic;

import java.net.*;

import gamelogic.level.Level;
import gamelogic.player.Player;
import gamelogic.player.PlayerState;
import gamelogic.player.Player2;
import java.io.*;

public class ConnectionHandler extends Thread{

	public Main main;
	public final int port = 8911;
	public Player player;
	public boolean mpActive;
	public PlayerState player2State;
	ServerSocket listener;
	Socket connection;
	
	public void run(){
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
		this.interrupt();
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
			while (player!=null) {
				try {
					oos.writeObject(player.state);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	class Reciever extends Thread{
		public ObjectInputStream ois;
		
		public void run() {
			try {
				ois = new ObjectInputStream(connection.getInputStream());
			}
			catch (IOException e) {
				e.printStackTrace();
				return;
			}
			while (true) {
				try {
					player2State = (PlayerState)ois.readObject();
					if (!mpActive) {
						mpActive=true;
						Level.player2 = new Player2(player2State.x,player2State.y,null, player2State.color,player2State,main);
					}
					Level.player2.state = player2State;
				}
				catch (IOException | ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		}
	}
	
}
