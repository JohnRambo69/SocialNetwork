package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Vertex class.  This class represent vertex in graph. Each vertex have a unique userID number which is integer value
 * and list of edges.</p>
 * @author LucasS
 * @since 17/07/19
 *
 */

public class Vertex {
	
	private ArrayList<Edge> edgeList;
	private ArrayList<Edge> edgeListOut;
	private int userID;
	private int product = 0;
	/**
	 *  Constructor
	 * @param userID  unique number
	 */
	public Vertex(int userID) {
		this.edgeList = new ArrayList<Edge>();
		this.edgeListOut = new ArrayList<Edge>();
		this.userID = userID;
	}
	
	/**
	 * Constructor to create copy of existing vertex.
	 * @param other Other vertex.
	 */
	public Vertex(Vertex other) {
		this.edgeList = other.getEdges();
		this.edgeListOut = other.getOutEdges();
		this.userID = other.getID();
	}
	
	/**
	 *  This method add edge to edge list if it isn't already there.
	 * @param edge The Edge to add
	 */
	public void addEdge(Edge edge) {
		if(!edgeList.contains(edge)) {
			edgeList.add(edge);
		}
	}
	
	/**
	 *  This method add out edge to edge list if it isn't already there.
	 * @param edge The Edge to add
	 */
	public void addOutEdge(Edge edge) {
		if(!edgeListOut.contains(edge)) {
			edgeListOut.add(edge);
		}
	}
	
	/**
	 *  This method check that vertex contains edge
	 * @param vert userID to check
	 * @return true or false
	 */
	public boolean hasNeighbor(int vert) {
		for(Edge e : edgeListOut) {
			if(e.getFrom() == vert)
				return true;
		}
		return false;
	}
	
	
	/**
	 *  This method remove edge from list.
	 * @param edge to remove
	 * 
	 */
	public void removeEdge(Edge edge) {
		if(edgeList.contains(edge))
			edgeList.remove(edge);
		else
			System.out.println("@@@Unable to remove.");
	}
	
	/**
	 *  
	 * @return total number of edges
	 */
	public int getTotalEdges() {
		return edgeList.size();
	}
	
	/**
	 * 
	 * @return List of all edges
	 */
	public ArrayList<Edge> getEdges(){
		ArrayList<Edge> out = new ArrayList<>(edgeList);
		return out;
	}
	
	/**
	 * 
	 * @return List of all out edges
	 */
	public ArrayList<Edge> getOutEdges(){
		ArrayList<Edge> out = new ArrayList<>(edgeListOut);
		return out;
	}
	
	/**
	 *  
	 * @return userId as string.
	 */
	@Override
	public String toString() {
		String out = String.valueOf(userID);
		return out +  this.getEdges().toString() + this.getOutEdges().toString();
	}
	
	/**
	 *  
	 * @return userId.
	 */
	public int getID() {
		return this.userID;
	}
	
	/**
	 *  This method swap edges in and out.
	 * 
	 */
	public void swapEdges() {
		

		ArrayList<Edge> temp = edgeList;
		edgeList = edgeListOut;
		edgeListOut = temp;
	}
	
	/**
	 * This method check is it two vertex are equals by userID. It casting object to Vertex object.
	 * @param o object Vertex.
	 * @return return true if both vertex are equals. 
	 * Otherwise return false.
	 */
	public boolean equals(Object o) {
		Vertex other = (Vertex) o;
		return this.userID == other.getID();
	}
	/**
	 * @return return hashCode by userID.
	 */
	@Override
	public int hashCode() {
		return Integer.hashCode(userID);
	}
	/**
	 * @return return product A.
	 */
	public int getProduct() {
		return product;
	}
	/**
	 * @param set productA.
	 */
	public void setProduct(int productA) {
		this.product = productA;
	}
	
	
}
