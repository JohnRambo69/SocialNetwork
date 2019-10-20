/**
 * @author UCSD MOOC development team
 * 
 * Utility class to add vertices and edges to a graph
 *
 */
package util;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import graph.CapGraph;
import graph.Edge;
import graph.Vertex;

public class GraphLoader {
    /**
     * Loads graph with data from a file.
     * The file should consist of lines with 2 integers each, corresponding
     * to a "from" vertex and a "to" vertex.
     */ 
	public static final int PRODUCT_A = 0;
	public static final int PRODUCT_B = 1;
	
    public static void loadGraph(graph.Graph g, String filename) {
        Set<Integer> seen = new HashSet<Integer>();
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // Iterate over the lines in the file, adding new
        // vertices as they are found and connecting them with edges.
        while (sc.hasNextInt()) {	
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            if (!seen.contains(v1)) {
                g.addVertex(v1);
                seen.add(v1);
            }
            if (!seen.contains(v2)) {
                g.addVertex(v2);
                seen.add(v2);
            }
            g.addEdge(v1, v2);
        }
        
        sc.close();
    }
    
    public static void setUp(CapGraph g, int vertex, int friends) {
    	Vertex v = g.getVertex(vertex);
    	v.setProduct(PRODUCT_B);
    	List<Edge> eList = v.getEdges();
    	Random r = new Random();
    	
    		for(int i = 0; i <= friends; i++) {
    			int rand = r.nextInt(eList.size() - 1);
    	    	Edge e = eList.get(rand);
    	    	g.getVertex(e.getTo()).setProduct(PRODUCT_B);
    		}	
    	System.out.println("Selected vertex is:" + vertex);
    	System.out.println("Total friends: " + eList.size());
    	
    }
    
    
}
