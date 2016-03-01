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
	public boolean getVal() throws BayesNetException;
	
	/**
	 * calculate the probability that the node is true
	 * @return a double denoting the probability that this node is true
	 * @throws BayesNetException 
	 */
	public double getProbability() throws BayesNetException;
	
	/**
	 * If the value of this node has not yet been generated, generate it first. 
	 * @return the boolean value of this node
	 */
	public boolean isTrue();
	
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
