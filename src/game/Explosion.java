package game;

public class Explosion extends Actor {
	
	private static String[] SPRITES = new String[] {"sprite-explosion1.png", "sprite-explosion2.png", "sprite-explosion3.png",
			"sprite-explosion4.png", "sprite-explosion5.png", "sprite-explosion6.png", 
			"sprite-explosion7.png", "sprite-explosion8.png", "sprite-explosion9.png"};
	/**
	 * @param spriteNames
	 * @param spriteChangeSpeed
	 */
	public Explosion() {
		super(SPRITES, 5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		super.act();

		if (this.currentSprite.equals(this.sprites.get(this.sprites.size()-1))) {
			this.setMarkedForRemoval(true);
		}
	}

}
