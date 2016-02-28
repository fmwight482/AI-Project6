package project6;

import java.util.Random;

/**
 * class representing a query variable node in a bayes net
 */
public class QueryNode extends absBayesNode implements IBayesNode {
	
	/**
	 * Standard constructor for QueryNode
	 */
	public QueryNode() {
		super();
	}
	
	/**
	 * constructor with random number generator passed in
	 * @param aRand
	 */
	public QueryNode(Random aRand) {
		super(aRand);
	}
	
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
		for(double val : cpt){
			
		}
		//value = thisValue;
		return thisValue;
	}
}
