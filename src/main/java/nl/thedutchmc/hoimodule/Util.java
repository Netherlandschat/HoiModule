package nl.thedutchmc.hoimodule;

import java.util.Random;

public class Util {

	/**
	 * Generate a random integer between two bounds
	 * @param min Lower bound, inclusive
	 * @param max Upper bound, inclusive
	 * @return Returns a random integer between the provided bounds
	 */
	public static int getRandomInt(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
}
