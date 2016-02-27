package project6;

/**
 * Interface representing a node in a bayes net
 * @author Rick
 *
 */
public interface IBayesNode {
	public boolean isTrue();
	
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
