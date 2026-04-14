package gamelogic.player;

import java.awt.Color;
import java.awt.Graphics;

import gameengine.PhysicsObject;
import gameengine.graphics.MyGraphics;
import gameengine.hitbox.RectHitbox;
import gamelogic.Main;
import gamelogic.level.Level;
import gamelogic.tiles.Tile;

public class Player2 extends PhysicsObject{
	public float walkSpeed = 500;
	public float jumpPower = 1800;
	public Color color;
	public boolean isJumping = false;

	public Player2(float x, float y, Level level, Color color,Main main) {
		super(x, y, 100, 100, main.currentLevel);
		this.hitbox = new RectHitbox(this, 10, 10, width - 10, height - 10);
		this.color=color;
	}

	@Override
	public void update(float tslf) {
		super.update(tslf);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		MyGraphics.fillRectWithOutline(g, (int)getX(), (int)getY(), width, height);
		
		if(Main.DEBUGGING) {
			for (int i = 0; i < closestMatrix.length; i++) {
				Tile t = closestMatrix[i];
				if(t != null) {
					g.setColor(Color.RED);
					g.drawRect((int)t.getX(), (int)t.getY(), t.getSize(), t.getSize());
				}
			}
		}
		
		hitbox.draw(g);
	}
	
}
