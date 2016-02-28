package project6;

import java.util.ArrayList;
import java.util.Random;

/**
 * abstract class representing a node in a bayes net. Contains a list of edges 
 * leading into and out from the node.
 */
public abstract class absBayesNode {
	private ArrayList<Edge> edgesTo;
	protected ArrayList<Edge> edgesFrom;
	protected ArrayList<Double> cpt;
	private Random rand;
	
	/**
	 * standard constructor
	 */
	public absBayesNode() {
		edgesTo = new ArrayList<Edge>();
		edgesFrom = new ArrayList<Edge>();
		cpt = new ArrayList<Double>();
	}
	
	/**
	 * constructor with Random number generator passed in
	 * @param aRand
	 */
	public absBayesNode(Random aRand) {
		edgesTo = new ArrayList<Edge>();
		edgesFrom = new ArrayList<Edge>();
		cpt = new ArrayList<Double>();
		rand = aRand;
	}
	
	/**
	 * adds the given CPT value to cpt list
	 * @param newCPT
	 */
	public void addCPT(double newCPT){
		cpt.add(newCPT);
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
