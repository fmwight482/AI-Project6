package project6;

/**
 * Interface representing a node in a bayes net
 * @author Rick
 *
 */
public interface IBayesNode {
	public boolean isTrue();
	
	/**
	 * adds the given CPT value to cpt list
	 * @param newCPT
	 */
	public void addCPT(int newCPT);
	
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
}
