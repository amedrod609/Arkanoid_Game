package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball extends Actor {

	public static Ball instance = null;

	/**
	 * 
	 */
	public Ball() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collisionWith(Actor actorCollisioned) {
		super.collisionWith(actorCollisioned);

		// If the collision is with a Brik or a player
		if (actorCollisioned instanceof Brick || actorCollisioned instanceof Player) {
			System.out.println("Collision detected: object " + actorCollisioned.toString());

			// Invert the vx and vy
			vy = -vy;
		//	vx = -vx;
			System.out.println("Vy: " + vy);
			System.out.println("\ny: " + y);
			

		}
	}

	public void act() {
		this.x += this.vx;
		if (this.x < 0 || this.x > (Arkanoid_Game.getInstance().getWidth() - this.getWidth())) {
			vx = -vx;
		}
		this.y += this.vy;
		if (this.y < 0 || this.y > (Arkanoid_Game.getInstance().getHeight() - this.getHeight())) {
			vy = -vy;
		}
	}

	public static Ball getInstance() {
		if (instance == null) {
			instance = new Ball();
		}
		return instance;
	}

	public void paint(Graphics2D g) {

		width = 20;
		height = 20;

		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);

	}
}
