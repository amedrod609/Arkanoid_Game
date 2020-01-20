package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Actor {

	protected int x, y; // Actor's coords
	protected int vx; // X axis movement speed
	protected int vy; // Y axis movement speed
	protected Color color; // Class color
	protected int width, height; // Hitbox
	protected List<BufferedImage> sprites = new ArrayList<BufferedImage>(); // Sprite list for animations
	protected BufferedImage currentSprite = null; // Image sprite
	protected int timeUnit = 0;
	protected int spriteChangeSpeed = 0;

	protected boolean markedForRemoval = false; // Flag for removing a hit object

	/*
	 * 
	 */
	public Actor() {
	}
	/**
	 * 
	 * @param spriteName
	 */
	Actor(String spriteName) {
		// Load image using SpritesRepository class
		this.spriteChangeSpeed = 1;
		loadImageFromSpriteNames(new String[] { spriteName });
	}
	/**
	 * 
	 * @param spriteNames
	 */
	public Actor(String spriteNames[]) {
		this.spriteChangeSpeed = 1;
		loadImageFromSpriteNames(spriteNames);
	}
	/**
	 * 
	 * @param spriteNames
	 * @param spriteChangeSpeed
	 */
	public Actor(String spriteNames[], int spriteChangeSpeed) {
		this.spriteChangeSpeed = spriteChangeSpeed;
		loadImageFromSpriteNames(spriteNames);
	}

	public void loadImageFromSpriteNames(String spriteNames[]) {
		// Get images from Sprites Repository class
		for (String s : spriteNames) {
			this.sprites.add(SpritesRepository.getInstance().getSprite(s));
		}
		// Assign the fist sprite
		if (this.sprites.size() > 0) {
			this.currentSprite = this.sprites.get(0);
		}
		adjustHeightAndWidth();
	}

	/**
	 * 
	 * @param actorCollisioned
	 */
	public void collisionWith(Actor actorCollisioned) {
	}

	/**
	 * 
	 */
	private void adjustHeightAndWidth() {
		// Get image measurements
//		height = this.currentSprite.getHeight();
//		width = this.currentSprite.getWidth();
		
		if (this.sprites.size() > 0) {
			this.height = this.sprites.get(0).getHeight();
			this.width = this.sprites.get(0).getWidth();
		}
		for (BufferedImage b : sprites) {
			if (b.getWidth()> this.width) {
				this.width = b.getWidth();
			}
			if (b.getHeight() > this.height) {
				this.height = b.getHeight();
			}
		}
	}

	public void act() {
		if (this.sprites != null && this.sprites.size() > 1) {
			
			timeUnit++;
			
			//Function for changing the sprite
			if (timeUnit % spriteChangeSpeed == 0) {
				
				timeUnit = 0;
				
				int currentSpriteIndex = sprites.indexOf(this.currentSprite);
				
				int nextSpriteIndex = (currentSpriteIndex + 1) % sprites.size();
				
				this.currentSprite = sprites.get(nextSpriteIndex);
			}
		}
	}

	/**
	 * Paint actor on screen
	 * 
	 * @param g
	 */
	public void paint(Graphics2D g) {
		g.drawImage(this.currentSprite, this.x, this.y, null);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the vx
	 */
	public int getVx() {
		return vx;
	}

	/**
	 * @param vx the vx to set
	 */
	public void setVx(int vx) {
		this.vx = vx;
	}

	/**
	 * @return the vy
	 */
	public int getVy() {
		return vy;
	}

	/**
	 * @param vy the vy to set
	 */
	public void setVy(int vy) {
		this.vy = vy;
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

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return currentSprite;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.currentSprite = image;
		this.adjustHeightAndWidth();
	}

	/**
	 * @return the markedForRemoval
	 */
	public boolean isMarkedForRemoval() {
		return markedForRemoval;
	}

	/**
	 * @param markedForRemoval the markedForRemoval to set
	 */
	public void setMarkedForRemoval(boolean markedForRemoval) {
		this.markedForRemoval = markedForRemoval;
	}

}
