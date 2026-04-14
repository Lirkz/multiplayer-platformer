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
	
	public PlayerState(Color c, int x, int y, RectHitbox hitbox) {;
		color = c;
		this.x=x;
		this.y=y;
		this.hitbox=hitbox;
	}
}
