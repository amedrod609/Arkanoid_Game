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
		
		if (actorCollisioned instanceof Brick || actorCollisioned instanceof Player) {
			this.vy *= -1;
			this.vx *= -1;
			System.out.println("Vy: " + vy);
			System.out.println("\ny: " + y);
			
			System.out.println("Collision detected");
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
