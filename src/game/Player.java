package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Actor implements KeyListener {

	boolean up, down, left, right; //Booleans to control arrow key pressing
	public static final int PLAYER_SPEED = 4;
	/**
	 * 
	 */
	public Player() {
		super("nave-50x15.png");
		
	}


	@Override
	public void act() {
		this.x += vx; 
		
		if (this.x < 0) {
			this.x = 0;
		}
		
		if (this.x > Arkanoid_Game.getInstance().getWidth()- this.getWidth()) {
			this.x = Arkanoid_Game.getInstance().getWidth()- this.getWidth();
		}
		
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
  		case KeyEvent.VK_UP : up = true; break;
		case KeyEvent.VK_LEFT : left = true; break;
		case KeyEvent.VK_RIGHT : right = true; break;
		case KeyEvent.VK_DOWN : down = true;break;
  	}
  	updateSpeed();
	}


	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN : down = false;break;
			case KeyEvent.VK_UP : up = false; break;
			case KeyEvent.VK_LEFT : left = false; break; 
			case KeyEvent.VK_RIGHT : right = false;break;
	}
	updateSpeed();
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	protected void updateSpeed() {
		this.vx = 0; this.vy = 0;
		if (down) vy = PLAYER_SPEED;
		if (up) vy = -PLAYER_SPEED;
		if (left) vx = -PLAYER_SPEED;
		if (right) vx = PLAYER_SPEED;
	}
	
	


}
