package graph;

/**
 * <p> Edge class. This class represent edges in graph. Each edge is directed and contains
 * two values of start and end of the edge. </p>
 * @author Lucas S
 * @since 17/07/19
 *
 */

public class Edge {
	
	private int from;
	private int to;
	
	/**
	 * Constructor
	 * @param from Start of edge.
	 * @param to End of edge.
	 */
	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	/**
	 * Constructor to create copy of edge.
	 * @param other Other edge.
	 */
	public Edge(Edge other) {
		this.from = other.getFrom();
		this.to = other.getTo();
	}
	
	/**
	 * 
	 * @return vertex from.
	 */
	public int getFrom() {
		return this.from;
	}
	
	/**
	 * 
	 * @return vertex to.
	 */
	public int getTo() {
		return this.to;
	}
	
	/**
	 * 
	 * Set edge From destination..
	 */
	public void setFrom(int from) {
		this.from = from;
	}
	
	/**
	 * 
	 * Set edge TO destination
	 */
	public void setTo(int to) {
		this.to = to;
	}
	
	/**
	 *  Method comparing both 'from' and 'to' values.
	 * @return return true if edges equals. Otherwise false.
	 */
	public boolean equals(Object o) {
		Edge other = (Edge) o;
		return this.from == other.getFrom() && this.to == other.getTo();
	}
	
	/**
	 * @return "[from | to].
	 */
	public String toString() {
		return from + "-->" + to;
	}
	

}
