package gameengine.hitbox;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * 
 * @author Paul
 *
 */
public abstract class Hitbox implements Serializable {
	
	public static final boolean SHOW_HITBOXES = false; //used for debugging

	public abstract void update();
	
	public abstract void draw(Graphics graphics);
}
