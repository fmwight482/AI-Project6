package project6;

import java.util.ArrayList;

public abstract class absBayesNode {
	private ArrayList<Edge> edgesTo;
	private ArrayList<Edge> edgesFrom;
	
	public void addEdgeTo(Edge anEdge) {
		edgesTo.add(anEdge);
	}
	
	public void addEdgeFrom(Edge anEdge) {
		edgesFrom.add(anEdge);
	}
	
	public ArrayList<Edge> getEdgesFrom() {
		return edgesFrom;
	}
	
	public ArrayList<Edge> getEdgesTo() {
		return edgesTo;
	}
}
