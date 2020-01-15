package game;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpritesRepository {

	//Image storage
	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	//Singleton pattern
	private static SpritesRepository instance = null;
	//Resources folder route
	private static String RESOURCES_FOLDER = "../res/";
	/**
	 * 
	 */
	public SpritesRepository() {
		super();
	}
	/**
	 * Singleton pattern method
	 * @return
	 */
	public static SpritesRepository getInstance() {
		if (instance == null) {
			instance = new SpritesRepository();
		}
		return instance;
	}
	/**
	 * This method load images from hard drive into a BufferedImage object
	 * @param resourceName
	 * @return
	 */
	private BufferedImage loadImage(String resourceName) {
		
		URL url = null;
		
		try {
			url = getClass().getResource(resourceName);
			return ImageIO.read(url);
		} catch (Exception e) {
			//Show exception and end the program if it occurs
			e.printStackTrace();
			System.exit(0);
		}
		//Only loaded if exception occurs
		return null; 
	}
	
	public BufferedImage getSprite ( String resourceName) {
		BufferedImage img = sprites.get(resourceName);
		
		if (img == null) {
			img = loadImage(RESOURCES_FOLDER + resourceName);
			sprites.put(resourceName, img);
		}
		return img;
	}
	
}
