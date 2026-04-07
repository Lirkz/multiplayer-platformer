package gamelogic.player;

import java.awt.Color;
import gameengine.hitbox.RectHitbox;
import java.io.Serializable;

public class PlayerState implements Serializable {
	public Player player;
	public Color color;
	public RectHitbox hitbox;
	int x;
	int y;
	
	
	public PlayerState(Player player) {
		this.player=player;
		color = player.color;
		x=(int)player.getX();
		y=(int)player.getY();
		hitbox=player.getHitbox();
	}
}
