package project6;

/**
 * class representing an evidence variable node in a bayes net
 */
public class EvidenceNode extends absBayesNode implements IBayesNode {
	private boolean value;
	//TODO Constructor
	
	/**
	 * 
	 * @return value
	 */
	public boolean getVal(){
		return value;
	}
	
}
