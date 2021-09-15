package cities;

import java.io.*;
import java.util.*;

public class cities_tx {

	public static void main(String[] args) {
		
		  ArrayList<String> cityList=new ArrayList<>(); ArrayList<Edge> edgeList=new
		  ArrayList<>(); int totalDistance=0;
		  
		  try { FileReader fr = new FileReader("assn9_data.csv"); BufferedReader br =
		  new BufferedReader(fr); String cityData;
		  
		  try { while((cityData=br.readLine())!=null) { String[]data =
		  cityData.split(","); cityList.add(data[0]); for(int i=1;i<data.length;i+=2) {
		  Edge edge=new Edge(data[0], data[i], Integer.valueOf(data[i+1]));
		  edgeList.add(edge);
		  
		  }
		  }
		  br.close(); } 
		  catch (IOException e) { 
		  System.out.println("I/O exception");; 
		  } 
		  } 
		  catch (FileNotFoundException e) {
		  System.out.println("File not Found");; }
		  System.out.println(edgeList.get(33).city1+" "+edgeList.get(33).city2+" "
		  +edgeList.get(33).distance); Kruskal k=new Kruskal(cityList,edgeList);
		  
		  
		  for (int i=0;i<k.mst.size();i++) {
		  System.out.println("Edge No. "+(i+1)+": "+k.mst.get(i).city1+" to "+k.mst.get
		  (i).city2+"; distance: "+k.mst.get(i).distance); totalDistance=
		  totalDistance+k.mst.get(i).distance; } System.out.println(
		  "-----------------------------------------------------------");
		  System.out.println("\nTotal Distance of this Minimum Spanning Tree: "
		  +totalDistance);
		 

	}

}
