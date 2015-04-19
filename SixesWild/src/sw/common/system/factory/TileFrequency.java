/**
 *  @file   TileFrequency.java
 *  @author Tony Vu
 *  @since  Apr 11, 2015
 */
package sw.common.system.factory;

import java.util.HashMap;
import java.util.Random;

/** The model to describe the frequency of each value and multiplier appearing in the level */
public class TileFrequency {
	
	/** Default max value and multiplier for Sixes Wild */
	int maxVal = 6;
	int maxMul = 3;
	
	/** Probability of each value to appear in the level */
	HashMap<Integer, Double> valFreq = new HashMap<Integer, Double>();
	
	/** Probability of each multiplier value to appear in the level */
	HashMap<Integer, Double> mulFreq = new HashMap<Integer, Double>();
	
	/** Random number generator */
	static Random rand = new Random();
	
	public TileFrequency() {
		double val  = 100.0 / maxVal; // Equal frequency for each value
		double mult = 100.0 / maxMul; // Equal frequency for each multiplier
		double sum;                 // Keep track of percentile for each subsequent value
		
		// Set freq for each value		
		sum = 0;
		for (int i = 0; i <= maxVal; i++) {			
			valFreq.put(i, sum);
			sum += val;
		}
		
		// Set freq for each multiplier value		
		sum = 0;
		for (int i = 0; i <= maxMul; i++) {			
			mulFreq.put(i, sum);
			sum += mult;
		}
	}
	
	public TileFrequency(HashMap<Integer, Double> valFreq, HashMap<Integer, Double> mulFreq) {
		this.valFreq = valFreq;
		this.mulFreq = mulFreq;
	}
	
	/**
	 * @param i the Tile value
	 * @return frequency of i appearing in the level
	 */
	public double getValFreq(int i) {
		double f = 0.0;
		try {
			f = valFreq.get(i);
		} catch (IndexOutOfBoundsException e) {
			//e.printStackTrace();			
		}			
		return f;		 
	}
	
	/**
	 * @param i the Tile multiplier value
	 * @return frequency of i appearing in the level
	 */
	public double getMulFreq(int i) {
		double f = 0.0;
		try {
			f = mulFreq.get(i);
		} catch (IndexOutOfBoundsException e) {
			//e.printStackTrace();			
		}			
		return f;
	}
	
	
	/**
	 * @return the next value to appear
	 */
	int nextVal() {
		double f0, f1;
		double freq = rand.nextDouble() * 100;
		
		for (int i = 1; i <= maxVal; i++) {
			f1 = getValFreq(i);
			f0 = getValFreq(i-1);
			if (freq > f0 && freq < f1) {
				return i;
			}
		}
		return 0;
	}	
	
	/**
	 * @return the next multiplier value to appear
	 */
	int nextMul() {
		double f0, f1;
		double freq = rand.nextDouble() * 100;
		
		for (int i = 1; i <= maxMul; i++) {
			f1 = getMulFreq(i);
			f0 = getMulFreq(i-1);
			if (freq > f0 && freq < f1) {
				return i;
			}
		}
		return 0;
	}
	
}

