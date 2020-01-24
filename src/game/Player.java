package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Player extends Actor implements KeyListener, MouseListener{

	boolean up, down, left, right, control, shift, nKey, mKey; // Booleans to control arrow key pressing
	boolean startMovingBall = true;
	public static final int PLAYER_SPEED = 4;
	

	/**
	 * 
	 */
	public Player() {
		super("nave-50x15.png");

	}

	@Override
	public void act() {
		if (startMovingBall) {
			ballFixedToPlayer();
		} else {
			if (control && shift && nKey) {
				this.x = Arkanoid_Game.getInstance().getBall().getX();
			} else {

				this.x += vx;

				if (this.x < 0) {
					this.x = 0;
				}

				if (this.x > Arkanoid_Game.getInstance().getWidth() - this.getWidth()) {
					this.x = Arkanoid_Game.getInstance().getWidth() - this.getWidth();
				}

			}

		}
		
	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_CONTROL:
			control = true;
			break;
		case KeyEvent.VK_SHIFT:
			shift = true;
			break;
		case KeyEvent.VK_N:
			nKey = true;
			break;
		case KeyEvent.VK_M:
			mKey = true;
			break;
		case KeyEvent.VK_SPACE:
			startMovingBall = false;
		}

		updateSpeed();
	}
	
	

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_CONTROL:
			control = false;
			break;
			case KeyEvent.VK_SHIFT : shift = false;break;
		case KeyEvent.VK_N : nKey = false;break;
		case KeyEvent.VK_M : mKey = false;break;
		}

		updateSpeed();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	
	
	

	private void ballFixedToPlayer() {
		Arkanoid_Game.getInstance().getBall().setX(this.x + (this.width/2) - Arkanoid_Game.getInstance().getBall().getWidth() / 2 );
		Arkanoid_Game.getInstance().getBall().setY(this.y - (this.height + this.height/2));
	}

	protected void updateSpeed() {
		this.vx = 0;
		this.vy = 0;
		if (down)
			vy = PLAYER_SPEED;
		if (up)
			vy = -PLAYER_SPEED;
		if (left)
			vx = -PLAYER_SPEED;
		if (right)
			vx = PLAYER_SPEED;

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		startMovingBall = false;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
