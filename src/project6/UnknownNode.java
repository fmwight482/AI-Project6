package project6;

import java.util.ArrayList;
import java.util.Random;

public class UnknownNode extends absBayesNode implements IBayesNode {
	private boolean hasVal;
	private boolean value;

	/**
	 * Default Constructor
	 */
	public UnknownNode() {
		super();
		hasVal = false;
	}
	
	/**
	 * constructor with random number generator passed in
	 * @param aRand
	 */
	public UnknownNode(Random aRand) {
		super(aRand);
		hasVal = false;
	}
	
	/**
	 * @throws BayesNetException 
	 * 
	 */
	@Override
	public boolean getVal() throws BayesNetException {
		if (hasVal) {
			return value;
		}
		else {
			boolean thisValue = false;
			double prob = getProbability();
			
			if (rand.nextDouble() <= prob) {
				value = true;
			}
			hasVal = true;
			return value;
		}

	}
	
	public double getProbability() throws BayesNetException {
		double prob = 0;
		int count = 0;
		int parentVal = 0;
		// get parent values from edgesFrom
		// construct a value from the truth of the parents
		for (Edge e : edgesFrom) {
			if (e.getParent().getVal()) {
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
