package project6;

/**
 * class representing an evidence variable node in a bayes net
 */
public class EvidenceNode extends absBayesNode implements IBayesNode {
	
	/**
	 * Returns if this node is true from the value of each parent
	 * @return thisValue
	 */
	public boolean isTrue(){
		boolean thisValue = false;
		//get parent values from edgesFrom
		//construct a value from the truth of the parents
		for(Edge parent : edgesFrom){
			
		}//end for loop
		
		//look through CPT to find a true condition in cpt
		return thisValue;
	}
	
}
