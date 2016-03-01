package project6;

import java.util.Random;

/**
 * class representing a query variable node in a bayes net
 */
public class QueryNode extends absBayesNode implements IBayesNode {
	
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
		//get parent values from edgesFrom
		//construct a value from the truth of the parents
		for(Edge e : edgesFrom){			
			if(e.getParent().getVal()){
				parentVal= 1 << count;//set the bit associated with the parent
			}			
			count++;
		}//end for loop
		//look through CPT to find a true condition in cpt
		for(double val : cpt){
			
		}
		//value = thisValue;
		return thisValue;
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
