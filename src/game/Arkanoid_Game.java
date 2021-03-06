package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Arkanoid_Game extends Canvas {

	// Canvas limits
	public static final int HEIGHT = 800;
	public static final int WIDTH = 600;
	// FPS desired (PCMR INTENSIFIES)
	public static final int SPEED_FPS = 60;
	// BufferedStrategy for double buffered image load
	private BufferStrategy strategy;
	private long usedTime;
	// Main Brick list
	List<Actor> actors = new ArrayList<Actor>();

	// Actors to be added in the next iteration
	List<Actor> nextIterarionActors = new ArrayList<Actor>();
	// Actors to be deleted in the next iteration
	List<Actor> actorsForRemoval = new ArrayList<Actor>();

	// Player object
	Player player = null;
	Ball ball = null;

	// Singleton Pattern
	private static Arkanoid_Game instance = null;

	/**
	 * Main constructor: create window, panel
	 */
	public Arkanoid_Game() {
		JFrame window = new JFrame("Arkanoid");
		JPanel panel = (JPanel) window.getContentPane();
		setBounds(0, 0, WIDTH, HEIGHT);
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(new BorderLayout());
		panel.add(this, BorderLayout.CENTER);
		window.setBounds(0, 0, WIDTH, HEIGHT);
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Tell the JFrame not to repaint when the OS requires it
		window.setIgnoreRepaint(true);
		// Number of buffers used
		this.createBufferStrategy(2);
		// Reference to the Double buffer strategy
		strategy = getBufferStrategy();
		// This is supposed to be for controlling the game through the keyboard
		this.requestFocus();

	}

	// Create desired actors and store them in a list
	public void initWorld() {
		SoundsRepository.getInstance();
		SoundsRepository.getInstance().playSound("Arkanoid-start-of-stage.wav");
		
		// Create a brick and store it into a List
		int counterY = 5;
		int counterX = 5;
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 12; j++) {
				Brick b = new Brick();

				b.setX(counterX);
				b.setY(counterY);

				actors.add(b);
				counterX += Brick.getInstance().getWidth() + 2;

				if (i == 0) {
					b.setColor(Color.RED);
				}
				if (i == 1) {
					b.setColor(Color.YELLOW);
				}
				if (i == 2) {
					b.setColor(Color.BLUE);
				}
				if (i == 3) {
					b.setColor(Color.PINK);
				}
				if (i == 4) {
					b.setColor(Color.GREEN);
				}
				if (i == 5) {
					b.setColor(Color.CYAN);
				}

			}
			counterX = 5;
			counterY += Brick.getInstance().getHeight() + 2;
		}

		// Create new Ball
		Ball ball = new Ball();
		ball.setX(this.getWidth()/ 2 - ball.width / 2);
		ball.setY(670 - ball.height);
		actors.add(ball);
		
		//Ball pointer
		this.ball = ball;

		// Create new player
		Player player = new Player();
		player.setX(this.getWidth()/ 2 - (player.width / 2));
		player.setY(700);
		actors.add(player);

		this.player = player;
		this.addKeyListener(player);
		this.addKeyListener(ball);
		this.addMouseListener(ball);
		this.addMouseListener(player);
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				player.setX(e.getX());
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public void updateWorld() {
		
		// Check if actor is marked for removal in the main list
		// if so, store it into a list
		for (Actor actor : this.actors) {
			if (actor.isMarkedForRemoval()) {
				actorsForRemoval.add(actor);
			}
		}
		// Remove marked actor of the main list
		for (Actor actor : actorsForRemoval) {
			this.actors.remove(actor);
		}

		// Clear list for the next iteration
		actorsForRemoval.clear();

		// Add stored actors for the next iteration and clear the list
		this.actors.addAll(nextIterarionActors);
		this.nextIterarionActors.clear();

		// Detect collisions

		for (Actor a : this.actors) {
			if (a instanceof Ball) {
				// Create a rectangle that would act as a hitbox
				// Rectangle dimensions are the same as the actor's dimensions
				Rectangle rect1 = new Rectangle(a.getX(), a.getY(), a.getWidth(), a.getHeight());

				// For every actor we go over the list checking if the hitboxes intersects
				for (Actor b : this.actors) {
					if (!b.equals(a) && !b.isMarkedForRemoval() && !a.isMarkedForRemoval()) {
						Rectangle rect2 = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
						if (rect1.intersects(rect2)) {
							a.collisionWith(b);
							b.collisionWith(a);

							if (a instanceof Ball && b instanceof Brick) {
								break;
							}
						}
					}
				}
			}

		}

		// Main actors list act
		for (Actor actor : actors) {
			actor.act();
		}

	}
	
	public void ballMissed() {
		//Delete current ball
		ball.setMarkedForRemoval(true);
		
		System.out.println("Ball is ded");
		//Create a new ball, mirror of the other
		Ball ball = new Ball();
		ball.setX(this.getWidth()/ 2 - ball.width / 2);
		ball.setY(670 - ball.height);
		nextIterarionActors.add(ball);
		// Changue de ball in the pointer
		this.ball = ball;
		//Add new listeners for the new ball
		this.addKeyListener(ball);
		this.addMouseListener(ball);
		//Player lost a live
		player.setLivesLeft(player.getLivesLeft()-1);
		
		
	}

	public void paintWorld() {
		Toolkit.getDefaultToolkit().sync();
		// Paint with double buffer strategy
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		// Paint background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Paint objects

		for (Actor a : actors) {
			a.paint(g);
		}
		
		//Paint the spare lives
		//X coordinate for the images
		int xCoordLives = 10;
		//New image to load
		BufferedImage img = SpritesRepository.getInstance().getSprite("nave-25x7.png");
		
		//Draw as much images as lives are left
		for (int i = 0; i < player.getLivesLeft(); i++) {
			g.drawImage(img, xCoordLives, 750, null);
			xCoordLives += img.getWidth() + 2;
		}
		strategy.show();

	}

	/**
	 * Main game's loop.
	 */
	public void game() {
		initWorld();

		while (this.isVisible()) {
			long startTime = System.currentTimeMillis();
			// Update the current state and paint it
			updateWorld();
			paintWorld();

			usedTime = System.currentTimeMillis() - startTime;
			// Sleep the program the time needed to show the specified FPS
			try {
				int millisToSleep = (int) (1000 / SPEED_FPS - usedTime);
				if (millisToSleep < 0) {
					millisToSleep = 0;
				}
				Thread.sleep(millisToSleep);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Singleton pattern method
	public static Arkanoid_Game getInstance() {
		if (instance == null) {
			instance = new Arkanoid_Game();
		}
		return instance;
	}


	
	public Ball getBall() {
		Ball b = new Ball();
		for (Actor actor : actors) {
			if (actor instanceof Ball) {
				b = (Ball) actor;
			}
		}
		return b;
	}
	
	public Player getPlayer() {
		Player p = new Player();
		for (Actor actor : actors) {
			if (actor instanceof Player) {
				p = (Player) actor;
			}
		}
		return p;
	}
	
	public static void main(String[] args) {
		Arkanoid_Game.getInstance().game();

	}

}
