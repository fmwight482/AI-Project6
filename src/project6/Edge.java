package project6;

/**
 * class representing an edge between two nodes in a bayes net
 */
public class Edge {
	private IBayesNode parent;
	private IBayesNode child;
	
	/**
	 * Basic constructor for Edge
	 * @param aParent
	 * @param aChild
	 */
	public Edge(IBayesNode aParent, IBayesNode aChild) {
		parent = aParent;
		child = aChild;
	}
	
	/**
	 * @return parent
	 */
	public IBayesNode getParent() {
		return parent;
	}
	
	/**
	 * @return parent
	 */
	public IBayesNode getChild() {
		return child;
	}
}
