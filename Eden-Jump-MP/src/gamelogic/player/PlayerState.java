package gamelogic.player;

import java.awt.Color;

public class PlayerState {
	public Player player;
	public Color color;
	int x;
	int y;
	
	
	public PlayerState(Player player) {
		this.player=player;
		color = player.color;
		x=(int)player.getX();
		y=(int)player.getY();
	}
}
