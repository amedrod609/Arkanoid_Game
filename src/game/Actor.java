package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Actor {

	protected int x, y; // Actor's coords
	protected int vx; //X axis movement speed
	protected int vy; // Y axis movement speed
	protected Color color; // Class color
	protected int width, height; // Hitbox
	protected BufferedImage image; // Image sprite
	protected boolean markedForRemoval = false; //Flag for removing a hit object

	public Actor() {
		this.image = null;
	}

	/**
	 * @param x
	 * @param y
	 * @param color
	 * @param width
	 * @param height
	 * @param image
	 */
	public Actor(String spriteName) {
		// Load image using SpritesRepository class
		this.image = SpritesRepository.getInstance().getSprite(spriteName);
		adjustHeightAndWidth();
	}
	
	/**
	 * 
	 * @param actorCollisioned
	 */
	public void collisionWith(Actor actorCollisioned) {}

	/**
	 * 
	 */
	private void adjustHeightAndWidth() {
		// Get image measurements
		height = this.image.getHeight();
		width = this.image.getWidth();
	}

	public abstract void act();
	/**
	 * Paint actor on screen
	 * @param g
	 */
	public void paint(Graphics2D g) {
		g.drawImage(this.image, this.x, this.y, null);
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
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
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
