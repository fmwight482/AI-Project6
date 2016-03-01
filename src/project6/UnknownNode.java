package project6;

import java.util.ArrayList;
import java.util.Random;

public class UnknownNode extends absBayesNode implements IBayesNode {
	
	/**
	 * boolean value of this node
	 */
	private boolean value;
	
	/**
	 * evaluates to true if value has been set
	 */
	private boolean isSet;

	/**
	 * Default Constructor
	 */
	public UnknownNode(String aName) {
		super(aName);
	}
	
	/**
	 * constructor with random number generator passed in
	 * @param aRand
	 */
	public UnknownNode(Random aRand, String aName) {
		super(aRand, aName);
	}
	
	@Override
	public boolean getVal() throws BayesNetException {
		boolean thisValue = false;
		double prob = getProbability();
		
		if (rand.nextDouble() <= prob) {
			value = true;
		}
		return value;
	}
	
	public boolean isTrue() throws BayesNetException {
		if (isSet) {
			return value;
		}
		else {
			isSet = true;
			return getVal();
		}
	}
	
	public double getProbability() throws BayesNetException {
		double prob = 0;
		int count = 0;
		int parentVal = 0;
		// get parent values from edgesFrom
		// construct a value from the truth of the parents
		for (Edge e : edgesFrom) {
			if (e.getParent().isTrue()) {
				// set the bit associated with the parent
				parentVal = 1 << count;
			}
			count++;
		} // end for loop
		
		if (parentVal >= 0 && parentVal <= cpt.size()) {
			prob = cpt.get(parentVal);
		}
		else {
			throw new BayesNetException("parentVal = " + parentVal);
		}
		return prob;
	}
	
}
