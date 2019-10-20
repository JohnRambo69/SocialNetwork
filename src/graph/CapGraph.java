/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import util.GraphLoader;

/**
 * <p> This is main class of graph contains all method implementing Graph interface. </p>
 * @author LucasS
 * @since 17/07/19
 *
 */
public class CapGraph implements Graph {
	
	private HashMap<Integer, Vertex> graphList;
	private LinkedList<Edge> edgeList;
	private int vertexTotal;
	private int edgeTotal;
	
	public CapGraph() {
		graphList = new HashMap<>();
		this.edgeList = new LinkedList<Edge>();
		this.vertexTotal = 0;
		this.edgeTotal = 0;
	}

	/**
	 *  This method add vertex to graph.
	 * @param num Number of vertex - integer
	 */
	@Override
	public void addVertex(int num) {
		Vertex vert = new Vertex(num);
				graphList.put(num, vert);
				vertexTotal ++;
	}
	
	public void addVertex(Vertex v) {
		graphList.put(v.getID(),v);
		vertexTotal ++;
	}

	/**
	 * @param from number of vertex where edge going from.
	 * @param  to number of vertex where edge going to.
	 */
	@Override
	public void addEdge(int from, int to) {
		Edge edge = new Edge(from,to);
			edgeList.add(edge);
				Vertex x = graphList.get(to);
				Vertex v = graphList.get(from);
				v.addEdge(edge);
				x.addOutEdge(edge);
				edgeTotal ++;
	}
	
	/**
	 * This method returns vertex in graph, otherwise return null.
	 * @param userID Unique number of vertex.
	 * @return vertex.
	 */
	public Vertex getVertex(int userID) {
		return graphList.get(userID);
	}

	/**
	 * This method takes an int which is the node/user at the centre of the 
	 * desired egonet, and returns that user's egonet as a graph.
	 * @param center Unique number of centre vertex.
	 * @return Graph object with egonet from specific centre.
	 */
	@Override
	public Graph getEgonet(int center) {
		
		// Initialise variables.
		CapGraph result = new CapGraph();
		// Get all vertex in array list.
		Vertex root = this.getVertex(center);
			if(root == null) {
				return result;
			}
		ArrayList<Vertex> allVertex = this.getVertaxesForEgo(root);
		// Put all vertex to graph.
			for(Vertex v : allVertex) {
				result.addVertex(v.getID());
			}
			// Get all edges to array list.
			ArrayList<Edge> edges = this.getEdgesForEgo(allVertex);
			// convert vertex to integer
			ArrayList<Integer> checkEdge = new ArrayList<>();
				for(Vertex v : allVertex) {
					checkEdge.add(v.getID());
				}
			// Add all edges to graph
			for(Edge e: edges) {
				if(checkEdge.contains(e.getFrom()) && checkEdge.contains(e.getTo())) {
					result.addEdge(e.getFrom(), e.getTo());
				}
			}
		return result;
	}
	
	// Helper method.
	private ArrayList<Vertex> getVertaxesForEgo(Vertex vert){
		ArrayList<Vertex> out = new ArrayList<>();
		out.add(vert);
		for(Edge e : vert.getEdges()){
			Vertex v = this.getVertex(e.getTo());
			out.add(v);
			}
		return out;
	}
	
	// Helper method for getEgonet
	private ArrayList<Edge> getEdgesForEgo(ArrayList<Vertex> vertexes){
		ArrayList<Edge> result = new ArrayList<>();
			for(Vertex v : vertexes) {
			result.addAll(v.getEdges());
			}
		return result;
	}

	/** This method returns a graph of Strongly connected component.
	 * @return Graph of strongly connected components.
	 */
	@Override
	public List<Graph> getSCCs() {
		List<Graph> result = new ArrayList<>();
		
		List<Graph> result2 = new ArrayList<>();
		
		//Initialise variables
		Stack<Integer> vertices = new Stack<>();
			for(Integer x : this.graphList.keySet()){
				vertices.add(x);
			}

			Stack<Integer> finished = this.dfs(result2, vertices);
			//Transpose G
			this.swapEdges();
			vertices = this.dfs(result, finished);
			int prev = vertices.pop();
			CapGraph graph = new CapGraph();
			graph.addVertex(this.getVertex(prev));

		return result;
	}
	
	// getSCCs helper method
	private Stack<Integer> dfs(List<Graph> g,Stack<Integer> vertices){
		Stack<Integer> finished = new Stack<>();
		HashSet<Integer> visited = new HashSet<>();
		while(!vertices.isEmpty()) {
			int x = vertices.pop();
				if(!visited.contains(x)) {
					CapGraph graph = new CapGraph();
					graph.addVertex(this.getVertex(x));
					this.dfsVisit(graph,x,visited,finished);
					g.add(graph);
				}
				
		}
		return finished;
	}
	
	// dfs helper
	private void dfsVisit (CapGraph g,int v, HashSet<Integer> visited, Stack<Integer> finished) {
		visited.add(v);
		Vertex s = this.getVertex(v);
		ArrayList<Edge> neighbors = s.getEdges();
			for(Edge e : neighbors) {
				int nextVert = e.getTo();
					if(!visited.contains(nextVert)) {
						g.addVertex(this.getVertex(nextVert));
						this.dfsVisit(g, nextVert, visited, finished);
					}
			}
		finished.add(v);
	}
	
	// helper method for dfs.
	private void swapEdges() {
		for(Edge e : edgeList) {
			int x = e.getFrom();
			e.setFrom(e.getTo());
			e.setTo(x);
		}
		for(Vertex v : this.graphList.values()) {
			v.swapEdges();
		}
		
		
	}
	

	/** This method returns HashMap of integers  vertex  neighbours vertex.
	 * @return Returns HashMap of integer(vertex) and hashSet integer(accessible vertexes).
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		HashMap<Integer, HashSet<Integer>> out = new HashMap<Integer, HashSet<Integer>>();
		
		for(Vertex v : graphList.values()) {
			HashSet<Integer> e = new HashSet<>();
			List<Edge> edges = v.getEdges();
			for(Edge q : edges) {
				e.add(q.getTo());
			}
		out.put(v.getID(), e);	
		}
		return out;
	}
	
	/**
	 * This method return number of total vertex.
	 * @return Total number of vertex
	 */
	public int getTotalVert(){
		return vertexTotal;
	}
	
	/**
	 * This method return number of total edges.
	 * @return Total number of edges.
	 */
	public int getTotalEdge() {
		return edgeTotal;
	}
	/**
	 * Print out graph. 
	 * @return size graphList
	 */
	public LinkedList<Vertex> getVertexList(){
		LinkedList<Vertex> initVertex = new LinkedList<Vertex>();
		
			for(Vertex vert : graphList.values()) {
				initVertex.add(vert);
			}
			return initVertex;
	}
	
	/**
	 * Print out graph. 
	 * @param size Take number of lines to print out.
	 */
	public void pringGraph(int size) {
		int lines = 0;
		for(Vertex v : graphList.values()) {
			System.out.println(v);
			lines++;
			if(lines == size)
				break;
		}
	}

}
