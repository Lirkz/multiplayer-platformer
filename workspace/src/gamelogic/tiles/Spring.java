package gamelogic.tiles;

import java.awt.image.BufferedImage;

import gameengine.hitbox.RectHitbox;
import gamelogic.level.Level;

public class Spring extends Tile{
	
    public Spring(float x, float y, int size, BufferedImage image, Level level) {
    	super(x, y, size, image, false, level);
    	this.hitbox = new RectHitbox(x*size , y*size, 0, 10, size, size);
    }
}
