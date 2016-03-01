package project6;

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
	
	/**
	 * return the probability that this node is true
	 * @return 1 if true, 0 if false
	 */
	public double getLocalProbability(){
		if (value) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public double getTotalProbability(){
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
	public boolean getVal() {
		return value;
	}
	
	public boolean isTrue() {
		return value;
	}
}
