package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;

public class Ball extends Actor implements KeyListener, MouseListener {

	public static Ball instance = null;

	public static final int LAUNCH_TIME = 5;
	
	private long TIME_AT_CREATION = 0;
	
	private boolean launchBall = false;
	
	private PuntoAltaPrecision ballLocation = null;
	
	private TrayectoriaRecta trajectory = null;
	
	private float distance = 5;
	
	private float aceleration = 1.0005f;
	
	private static int MAX_VELOCIDAD = 14;
	/**
	 * 
	 */
	public Ball() {
		super();
		
		TIME_AT_CREATION = System.currentTimeMillis();
		
		// TODO Auto-generated constructor stub
	}



	public void act() {
		
		Player player = Arkanoid_Game.getInstance().getPlayer();
		
		long millisNow = System.currentTimeMillis();
		if (millisNow - TIME_AT_CREATION > LAUNCH_TIME * 1000 && !launchBall) { // LAUNCH
			newPoint();
			
			
		}
		
		if (!launchBall) {
			this.x = player.getX() + player.getWidth() / 2 - this.width / 2;
			this.y = player.getY() - this.getHeight() - 1;
		} else {


			//Collision with lateral borders
			if (x <= 0 || x>= Arkanoid_Game.getInstance().getWidth() - this.width ) {
				trajectory.reflejarHorizontalmenteRespectoAPunto(ballLocation);
			}
			
			//Collision with upper border
			if (y <= 0 ) {
				trajectory.reflejarVerticalmenteRespectoAPunto(ballLocation);
			}
			
			//Collision with the lower border and losing a life
			if (y >= Arkanoid_Game.getInstance().getHeight()-this.height) {
				if (player.getLivesLeft() > 0) {
					Arkanoid_Game.getInstance().ballMissed();
				}
				else {
					this.setMarkedForRemoval(true);
					
					Actor gameOver = new Actor("game-over.png") {
					};
					gameOver.setX(Arkanoid_Game.getInstance().getWidth() / 2 - gameOver.getWidth() / 2);
					gameOver.setY(Arkanoid_Game.getInstance().getHeight() / 2 - gameOver.getHeight() / 2);
					
					Arkanoid_Game.getInstance().nextIterarionActors.add(gameOver);
				}
			}
			ballLocation = trajectory.getPuntoADistanciaDePunto(ballLocation, distance);
			
			
			this.x = Math.round(ballLocation.x);
			this.y = Math.round(ballLocation.y);
			
			if (x<0) {
				x = 0;
			}
			if (y<0) {
				y = 0;
			}
			if (this.distance < MAX_VELOCIDAD) {
				this.distance *= this.aceleration;
			}
		}
	}
	
	public void newPoint() {
		
		ballLocation = new PuntoAltaPrecision(this.x, this.y);
		trajectory = new TrayectoriaRecta(1.2f, ballLocation, false);
		launchBall = true;
		SoundsRepository.getInstance().playSound("Arkanoid-SFX-07.wav");
		System.out.println("Ball launched");
		
	}
	
	@Override
	public void collisionWith(Actor actorCollisioned) {
		super.collisionWith(actorCollisioned);

		// If the collision is with a Brick
		if (actorCollisioned instanceof Brick) {
			System.out.println("Collision detected: object " + actorCollisioned.toString());
			trajectory.reflejarVerticalmenteRespectoAPunto(ballLocation);
			
			SoundsRepository.getInstance().playSound("Arkanoid-SFX-08.wav");
			

		}
		//If the collision is with the player
		if (actorCollisioned instanceof Player && trajectory != null) {
			System.out.println("Colisión con player");
			trajectory.reflejarVerticalmenteRespectoAPunto(ballLocation);
			SoundsRepository.getInstance().playSound("Arkanoid-SFX-07.wav");
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
	

	/**
	 * @return the launchTime
	 */
	public static int getLaunchTime() {
		return LAUNCH_TIME;
	}

	/**
	 * @return the launchBall
	 */
	public boolean isLaunchBall() {
		return launchBall;
	}

	/**
	 * @param launchBall the launchBall to set
	 */
	public void setLaunchBall(boolean launchBall) {
		this.launchBall = launchBall;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			newPoint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		newPoint();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
