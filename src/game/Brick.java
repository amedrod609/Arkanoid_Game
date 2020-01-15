package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Brick extends Actor {

	private Color color;

	public static Brick instance = null;

	/**
	 * 
	 */
	public Brick() {
		super();
		this.width = (Arkanoid_Game.getInstance().getWidth() / 12) - 2;
		this.height = 30;
	}
	
	
	/**
	 * Behavior when a collision occurs
	 */
	@Override
	public void collisionWith(Actor actorCollisioned) {
		super.collisionWith(actorCollisioned);
		
		if (actorCollisioned instanceof Ball) {
			this.setMarkedForRemoval(true);
		}
	}



	/**
	 * 
	 * @return
	 */
	public static Brick getInstance() {
		if (instance == null) {
			instance = new Brick();
		}
		return instance;
	}

	/**
	 * 
	 */
	@Override
	public void act() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public void paint(Graphics2D g) {

		g.setColor(color);
		g.fillRoundRect(x, y, width, height, 7, 7);

	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
