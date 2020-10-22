package com.roadmap.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.roadmap.service.RoadmapService;

@Service
public class RoadmapServiceImpl implements RoadmapService  {
	
	public static final String YES="yes";
	public static final String NO="no";
	
	public String determineRoadConnected(HashMap<String, String> validRecordsMap,String origin,String destination,HashSet<String> citiesUnique)  {
		  
		   HashMap<String,Integer> cityNodesMap=new HashMap<String,Integer>();
		   	
		   int count=0;
		   int originVerticesNumber=-1;
		   int destinationVerticesNumber=-1;
		   for(String city:citiesUnique) {
			   System.out.println("city  and count  "+ city  + "       "+count);
			   if(city.equals( origin)) {
	        		originVerticesNumber=count;
	        	}
			   else if(city.equals( destination)) {
				   destinationVerticesNumber=count;
	        	}
			   
			   cityNodesMap.put(city, count++);
		   }
		   
		   String response=NO;
		   if( originVerticesNumber==-1 || destinationVerticesNumber==-1) {
			   return NO;
		   }
		
		   
		   int numerOfCities = citiesUnique.size(); 
	    	
	    	
	        ArrayList<ArrayList<Integer> > adjacent  
	                    = new ArrayList<ArrayList<Integer> >(numerOfCities); 
	        
	          
	        for (int i = 0; i < numerOfCities; i++) 
	        	adjacent.add(new ArrayList<Integer>()); 
	        

	        for(Map.Entry<String, String> entry: validRecordsMap.entrySet()) {

	        	addRoute(adjacent, cityNodesMap.get( entry.getKey()),cityNodesMap.get( entry.getValue())); 
	        	
	        }
	        
	      
	        for (int i = 0; i < adjacent.size(); i++) { 
	        	
	        	if(originVerticesNumber==i || destinationVerticesNumber==i ) {

					
					int tempCount=0;
					for (int j = 0; j < adjacent.get(i).size(); j++) { 

					    tempCount++;
					} 
					if(tempCount>1) {
						return YES;
					}
	        	}	
				
			} 
	        
			return response;
		}

	
		static void addRoute(ArrayList<ArrayList<Integer> > adjacent, 
	            int u, int v) 
		{      
			adjacent.get(u).add(v); 
			adjacent.get(v).add(u); 
	
		} 
	
	
	
}
