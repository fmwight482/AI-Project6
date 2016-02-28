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
	public EvidenceNode(boolean aVal) {
		super();
		value = aVal;
	}
	
	/**
	 * constructor with random number generator passed in
	 * @param aVal
	 * @param aRand
	 */
	public EvidenceNode(boolean aVal, Random aRand) {
		super(aRand);
		value = aVal;
	}
	
	/**
	 * 
	 * @return value
	 */
	public boolean getVal(){
		return value;
	}
	
	/**
	 * 
	 * @return value
	 */
	@Override
	public boolean isTrue() {
		return value;
	}
}
