package project6;

/**
 * class representing an evidence variable node in a bayes net
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
	 * 
	 * @return value
	 */
	public boolean getVal(){
		return value;
	}
	
}
