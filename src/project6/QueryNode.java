package project6;

import java.util.Random;

/**
 * class representing a query variable node in a bayes net
 */
public class QueryNode extends absBayesNode implements IBayesNode {
	/**
	 * boolean value of this node
	 */
	private boolean value;
	
	/**
	 * evaluates to true if value has been set
	 */
	private boolean isSet;
	
	/**
	 * Standard constructor for QueryNode
	 */
	public QueryNode(String aName) {
		super(aName);
	}
	
	/**
	 * constructor with random number generator passed in
	 * @param aRand
	 */
	public QueryNode(Random aRand, String aName) {
		super(aRand, aName);
	}
	
	/**
	 * Returns if this node is true from the value of each parent
	 * @return thisValue
	 * @throws BayesNetException
	 */
	public boolean getVal() throws BayesNetException{
		boolean thisValue = false;
		int count = 0;
		int parentVal =0;
		double prob = getProbability();
		
		if (rand.nextDouble() <= prob) {
			value = true;
			isSet = true;
		}
		//value = thisValue;
		return thisValue;
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
}
