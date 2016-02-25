package project6;

import java.util.ArrayList;

/**
 * abstract class representing a node in a bayes net. Contains a list of edges 
 * leading into and out from the node.
 */
public abstract class absBayesNode {
	private ArrayList<Edge> edgesTo;
	private ArrayList<Edge> edgesFrom;
	private ArrayList<Integer> cpt;
	/**
	 * adds a CPT value 
	 */
	public void addCPT(int newCPT){
		
	}
	/**
	 * add the given edge to edgesTo
	 * @param anEdge
	 */
	public void addEdgeTo(Edge anEdge) {
		edgesTo.add(anEdge);
	}
	
	/**
	 * add the given edge to edgesFrom
	 * @param anEdge
	 */
	public void addEdgeFrom(Edge anEdge) {
		edgesFrom.add(anEdge);
	}
	
	/**
	 * @return edgesFrom
	 */
	public ArrayList<Edge> getEdgesFrom() {
		return edgesFrom;
	}
	
	/**
	 * @return edgesTo
	 */
	public ArrayList<Edge> getEdgesTo() {
		return edgesTo;
	}
}
