package project6;

/**
 * Interface representing a node in a bayes net
 * @author Rick
 *
 */
public interface IBayesNode {
	
	/**
	 * Return true or false in accordance with the value of this node. 
	 * If the node does not have a set value, calculate the necessary probabilities 
	 * and randomly generate a boolean value from them. 
	 * @return
	 * @throws BayesNetException
	 */
	public boolean getVal(Boolean shouldReject) throws BayesNetException;
	
	/**
	 * calculate the probability that the node is true given that any parent nodes are 
	 * already defined as either true or false
	 * @return a double denoting the probability that this node is true
	 * @throws BayesNetException 
	 */
	public double getLocalProbability() throws BayesNetException;
	
	/**
	 * calculate the probability that the node is true using the probabilities that each 
	 * parent node is true
	 * @return a double denoting the probability that this node is true
	 * @throws BayesNetException
	 */
	public double getTotalProbability() throws BayesNetException;
	
	/**
	 * If the value of this node has not yet been generated, generate it first. 
	 * @return the boolean value of this node
	 * @throws BayesNetException 
	 */
	public boolean isTrue() throws BayesNetException;
	
	/**
	 * Generate a boolean value for this node using the likelihood weighting algorithm. 
	 * @param isRejected
	 * @return the boolean value of this node
	 * @throws BayesNetException
	 */
	public boolean getRejectionValue(BooleanRef shouldReject) throws BayesNetException;
	
	/**
	 * Generate a boolean value for this node using the likelihood weighting algorithm. 
	 * If the node is an EvidenceNode with a pre-set value, return that value instead
	 * @return the boolean value of this node
	 * @throws BayesNetException
	 */
	public boolean getLikelihoodWeightedValue() throws BayesNetException;
	
	/**
	 * adds the given CPT value to cpt list
	 * @param newCPT
	 */
	public void addCPT(double newCPT);
	
	/**
	 * add the given edge to edgesTo
	 * @param anEdge
	 */
	public void addEdgeTo(Edge anEdge);
	
	/**
	 * add the given edge to edgesFrom
	 * @param anEdge
	 */
	public void addEdgeFrom(Edge anEdge);
	
	/**
	 * @return the name of this node
	 */
	public String getName();
}
