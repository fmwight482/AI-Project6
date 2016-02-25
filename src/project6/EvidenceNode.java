package project6;

/**
 * class representing an evidence variable node in a bayes net
 */
public class EvidenceNode extends absBayesNode implements IBayesNode {
	
	/**
	 * Returns if this node is true given the value of each parent
	 * @return thisValue
	 */
	public boolean isTrue(){
		//get parent values from edges
		boolean thisValue = false;
		//look through CPT to find a true condition
		return thisValue;
	}
	
}
