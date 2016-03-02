package project6;

import java.util.ArrayList;
import java.util.Random;

/**
 * class representing an evidence variable node in a bayes net; either True or False
 */
public class EvidenceNode extends absBayesNode implements IBayesNode {
	private boolean value;
	
	/**
	 * standard constructor for EvidenceNode
	 * @param aVal
	 */
	public EvidenceNode(boolean aVal, String aName) {
		super(aName);
		value = aVal;
	}
	
	/**
	 * constructor with random number generator passed in
	 * @param aVal
	 * @param aRand
	 */
	public EvidenceNode(boolean aVal, Random aRand, String aName) {
		super(aRand, aName);
		value = aVal;
	}
	
	public double getLocalProbability() throws BayesNetException {
		double prob = 0;
		int count = 0;
		int parentVal = 0;
		// get parent values from edgesFrom
		// construct a value from the truth of the parents
		if (edgesFrom.size() == 0) {
			prob = cpt.get(0);
		}
		else {
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
		}
		//System.out.println("probability of EvidenceNode " + getName() + " = " + prob);
		return prob;
	}
	
	public double getTotalProbability() throws BayesNetException {
		if (value) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * 
	 * @return value
	 */
	@Override
	public boolean getVal(Boolean shouldReject) throws BayesNetException {
		System.out.println("getVal called on EvidenceNode " + getName());
		boolean thisValue = false;
		double prob = getLocalProbability();
		
		if (rand.nextDouble() <= prob) {
			thisValue = true;
		}
		
		if (value == thisValue) {
			// this sample is A-OK!
		}
		else {
			// this sample is REJECTED!!!
			//System.out.println("This should be rejected");
			shouldReject = true;
		}
		return thisValue;
		//return value;
	}
	
	public boolean isTrue() {
		return value;
	}

	@Override
	public boolean getLikelihoodWeightedValue() throws BayesNetException {
		//System.out.println("value of EvidenceNode " + getName() + " set at " + value);
		return value;
	}

	@Override
	public boolean getRejectionValue(BooleanRef shouldReject) throws BayesNetException {
		boolean thisValue = false;
		double prob = getLocalProbability();
		
		if (rand.nextDouble() <= prob) {
			thisValue = true;
		}
		
		if (value == thisValue) {
			// this sample is A-OK!
		}
		else {
			// this sample is REJECTED!!!
			//System.out.println("This should be rejected");
			shouldReject.setVal(true);
		}
		return thisValue;
	}
}
