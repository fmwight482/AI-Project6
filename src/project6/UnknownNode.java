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
	public boolean isTrue() throws BayesNetException {
		if (hasVal) {
			return value;
		} else {
			boolean thisValue = false;
			double prob = 0;
			int count = 0;
			int parentVal = 0;
			// get parent values from edgesFrom
			// construct a value from the truth of the parents
			for (Edge e : edgesFrom) {
				if (e.getParent().isTrue()) {
					parentVal = 1 << count;// set the bit associated with the
											// parent
				}
				count++;
			} // end for loop
			count = 0;
			
			if (parentVal >= 0 && parentVal <= cpt.size()) {
				prob = cpt.get(parentVal);
			}
			else {
				throw new BayesNetException("parentVal = " + parentVal);
			}
			
			if (rand.nextDouble() <= prob) {
				value = true;
			}
			hasVal = true;
			return value;
		}

	}
	
	public boolean getVal() {
		return value;
	}
	
	public double getProbability() {
		double val = 0;
		if (cpt.isEmpty()) {
			System.err.println("Cannot get probability for node because cpt table is empty");
		}
		else if (cpt.size() == 1) {
			val = cpt.get(0);
		}
		else {
			// get list of probabilities of parent nodes
			ArrayList<Double> probs = new ArrayList<Double>();
			for (Edge e : edgesFrom) {
				probs.add(e.getParent().getProbability());
			}
			// compare number of probabilities with size of cpt table to determine compatability
			if (cpt.size() != Math.pow(2, probs.size())) {
				
			}
			// if numbers line up, find and sum the probabilities for each cpt state
		}
		return val;
	}
	
}
