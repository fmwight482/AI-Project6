package project6;

/**
 * class representing a query variable node in a bayes net
 */
public class QueryNode extends absBayesNode implements IBayesNode {
	/**
	 * Returns if this node is true from the value of each parent
	 * @return thisValue
	 */
	public boolean isTrue(){
		boolean thisValue = false;
		int count = 0;
		int parentVal =0;
		//get parent values from edgesFrom
		//construct a value from the truth of the parents
		for(Edge e : edgesFrom){			
			if(e.getParent().isTrue()){
				parentVal= 1 << count;//set the bit associated with the parent
			}			
			count++;
		}//end for loop
		//look through CPT to find a true condition in cpt
		for(int val : cpt){
			
		}
		//value = thisValue;
		return thisValue;
	}
}
