package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Player extends Actor implements KeyListener, MouseListener{

	private boolean up, down, left, right, control, shift, nKey, mKey; // Booleans to control arrow key pressing
	private boolean startMovingBall = false;
	public static final int PLAYER_SPEED = 8;
	private int livesLeft = 2; // As the programmers we are, we all start counting beginning with 0, so the actual number of lives 
								//remaining are one more than the number specified here :)
	

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

		if (this.x > Arkanoid_Game.getInstance().getWidth() - this.getWidth()) {
			this.x = Arkanoid_Game.getInstance().getWidth() - this.getWidth();
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
			Ball.getInstance().setLaunchBall(true);
			break;
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
	
//	private void FiveSecondsCounter() {
//		Ball.getInstance();
//		long nowMillis = System.currentTimeMillis();
//		if ((nowMillis - Ball.TIME_AT_CREATION) >= Ball.LAUNCH_TIME * 1000) {
//			startMovingBall = true;
//		}
//	}

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

	/**
	 * @return the startMovingBall
	 */
	public boolean isStartMovingBall() {
		return startMovingBall;
	}

	/**
	 * @param startMovingBall the startMovingBall to set
	 */
	public void setStartMovingBall(boolean startMovingBall) {
		this.startMovingBall = startMovingBall;
	}

	/**
	 * @return the livesLeft
	 */
	public int getLivesLeft() {
		return livesLeft;
	}

	/**
	 * @param livesLeft the livesLeft to set
	 */
	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}
	
	
	
	

}
