package gamelogic.player;

import java.awt.Color;
import gameengine.hitbox.RectHitbox;
import java.io.Serializable;

public class PlayerState implements Serializable {
	public Color color;
	public int x;
	public int y;
	static final long serialVersionUID = 1L;
	
	public PlayerState() {
		
	}
	
	public PlayerState(Color c, int x, int y) {
		color = c;
		this.x=x;
		this.y=y;
	}
}
