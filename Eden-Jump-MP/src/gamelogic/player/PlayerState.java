package gamelogic.player;

import java.awt.Color;
import gameengine.hitbox.RectHitbox;
import java.io.Serializable;

public class PlayerState implements Serializable {
	public Color color;
	public RectHitbox hitbox;
	public int x;
	public int y;
	static final long serialVersionUID = 1L;
	
	public PlayerState() {
		
	}
	
	public PlayerState(Player player) {;
		color = player.color;
		x=(int)player.getX();
		y=(int)player.getY();
		hitbox=player.getHitbox();
	}
}
