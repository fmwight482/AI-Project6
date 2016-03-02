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
		double prob = getLocalProbability();
		
		if (rand.nextDouble() <= prob) {
			value = true;
			isSet = true;
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
	
	public double getLocalProbability() throws BayesNetException {
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
	
	public double getTotalProbability() throws BayesNetException {
		double prob = 0;
		
		if (cpt.isEmpty()) {
			System.err.println("Cannot get probability for node because cpt table is empty");
		}
		else if (cpt.size() == 1) {
			prob = cpt.get(0);
		}
		else {
			// get list of probabilities of parent nodes
			ArrayList<Double> probs = new ArrayList<Double>();
			for (Edge e : edgesFrom) {
				probs.add(e.getParent().getTotalProbability());
			}
			// compare number of probabilities with size of cpt table to determine compatability
			if (cpt.size() != Math.pow(2, probs.size())) {
				throw new BayesNetException("cpt table of size " + cpt.size() + " is not compatable with "
						+ probs.size() + " parent nodes");
			}
			for (int i=0; i<cpt.size(); i++) {
				// probability = given probability + (pGivenState * pOfState)
				// pOfState = pBit1 * pBit2 * pBit3 * ... * pBitN
				double pGivenState = cpt.get(i);
				double pOfState = 1;
				int anInt = i;
				for (int j=0; j<probs.size(); j++) {
					int bit = anInt % 2;
					if (bit == 1) {
						pOfState = pOfState * probs.get(j);
					}
					else {
						pOfState = pOfState * (1 - probs.get(j));
					}
					anInt = anInt / 2;
				}
				//System.out.println("probability of state " + Integer.toBinaryString(i) + " in " + getName() + " = " + pOfState);
				prob += pOfState * pGivenState;
			}
			// if numbers line up, find and sum the probabilities for each cpt state
		}
		//System.out.println("Prob for node " + getName() + " = " + prob);
		return prob;
	}
}
