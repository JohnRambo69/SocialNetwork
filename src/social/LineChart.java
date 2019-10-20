package social;


import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class LineChart extends ApplicationFrame {

	   
	   public LineChart( String applicationTitle , String chartTitle, HashMap<Integer, Integer> map ) {
	      super( applicationTitle );        
	      JFreeChart lineChart = ChartFactory.createLineChart(
	    	         chartTitle,
	    	         "Stages","Number of people",
	    	         createDataset(map),
	    	         PlotOrientation.VERTICAL,
	    	         true,true,false);
	    	         
	    	      ChartPanel chartPanel = new ChartPanel( lineChart );
	    	      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	    	      setContentPane( chartPanel );
	   }
	   
	   private CategoryDataset createDataset(HashMap<Integer, Integer> map) {
		   DefaultCategoryDataset dataset = 
				      new DefaultCategoryDataset( );
	      for(Integer i : map.keySet()) {
	    	  dataset.addValue(map.get(i),"Number of people", i );  
	      }
	      return dataset; 
	   }
	   
	   public static void run(HashMap<Integer, Integer> map) {
		
		  LineChart chart = new LineChart("Simulation Statistics",
		 "Simulation how people change between products", map);
		 
			      chart.pack( );        
			      RefineryUtilities.centerFrameOnScreen( chart );        
			      chart.setVisible( true );  
	   }
	   
	}

