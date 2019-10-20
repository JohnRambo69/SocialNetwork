package social;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import graph.CapGraph;
import graph.Edge;
import graph.Vertex;
import util.GraphLoader;

public class MainApp {
	
	public static int USER = 133;
	public static int FRIENDS = 10;
	public static double ATTRACTIVE_B = 2.0;
	public static double ATTRACTIVE_A = 5.0;
	public static Map<Integer, Integer> simulation(CapGraph graph) {
		// initialization variables.
		System.out.println("Total number of community: " + graph.getTotalVert());
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		int personCount = 0;
		int iterCount = 0;
		int totalPerson = 0;
		boolean equilibrium = false;
		Queue<Vertex> toVisit = graph.getVertexList();
			while(!equilibrium) {
				int stop = toVisit.size();
				equilibrium = true;
					while(stop > 0) {
						stop--;
						Vertex v = toVisit.poll();
							if(willSwap(v, graph)) {
								v.setProduct(GraphLoader.PRODUCT_B);
								equilibrium = false;
								personCount++;
							}
								else {
									toVisit.add(v);
								}
							
							}
					iterCount++;
					System.out.println("In " + iterCount + " iteration, person switch to other producy are: " + personCount);
					totalPerson += personCount;
					result.put(iterCount, personCount);
					personCount = 0;
					}
			
			System.out.println("TOTAL:" + totalPerson +" person choose another product.");
			return result;
			}
		
			private static boolean willSwap(Vertex v, CapGraph graph) {
				double b = ATTRACTIVE_B;
				double tempP = 0.0;
				ArrayList<Edge> edges = v.getEdges();
				 	for(Edge e : edges) {
				 		int x = graph.getVertex(e.getTo()).getProduct();			
				 		if(x == GraphLoader.PRODUCT_B) {
				 			tempP++;	
				 			double p = (double) tempP / (double) edges.size();	 	
							double total =  b / (ATTRACTIVE_A +b);
							 	if(p > total) 		
							 		return true;
				 		}
				 	}	
		return false;
	}
			public static void main(String[] args) {
				
				CapGraph cap = new CapGraph();
				GraphLoader.loadGraph(cap, "data/facebook_2000.txt");
				GraphLoader.setUp(cap, USER, FRIENDS);
				
				LineChart.run((HashMap) simulation(cap));
				
			}
}
