package com.roadmap.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roadmap.service.RoadmapService;

import com.roadmap.exception.ResourceNotFoundException;

@RestController
public class RoadmapController {
	
	
	public static final String NO="no";

	@Autowired
	private Environment env;
	
	@Autowired
	RoadmapService roadmapService;
	
	
	HashSet<String> citiesUnique;

	@GetMapping("/connected")
	 public String getRoadMap(@RequestParam String origin, @RequestParam String destination) throws Exception  {  
       
		
		HashMap<String,String> validRecordsMap =readInputFileAndConstructList();
		
		String response = roadmapService.determineRoadConnected(validRecordsMap,origin,destination,citiesUnique);

		return response ;  
	 }


	private HashMap<String,String> readInputFileAndConstructList() throws Exception  {
		
		
		/*
		 * Note 1 : Reads file for the property "inputfile.path" in
		 * 			application.properties. If "inputfile.path" is NOT specified, it will read
		 * 			"city.txt" from  Project folder
		 * 
		 * Note 2 : Current Value in application.properties --> inputfile.path=c:/input/city.txt. 
		 *          This can be changed to read dynamic value.
		 * 
		 */
		
		String inputfilePath = env.getProperty("inputfile.path");
		
		//Note 3: If input file path is EMPTY. Then DEFAULT file "city.txt" in project folder will be Read.
		String filePath=((inputfilePath!=null && (!inputfilePath.isEmpty()))?inputfilePath:"city.txt");
		
		
		HashMap<String,String> validRecordsMap=new HashMap<String,String>();
		BufferedReader bufferedReader=null;
		
		try{
			bufferedReader =
			        new BufferedReader(new FileReader(filePath));
			
			String line;

			citiesUnique=new HashSet<String>();
			
			while ((line = bufferedReader.readLine()) != null){
				//Note 4 : If any line has more than ONE comma will be ignored.
				long count = line. chars(). filter(ch -> ch == ','). count();
				if(count!=1) {
					continue;
				}
				else {
					String[] splitted = line.split(",");
					citiesUnique.add(splitted[0].trim());
					citiesUnique.add(splitted[1].trim());
					validRecordsMap.put(splitted[0], splitted[1]);
				}
				
			}
	
		
		 }catch( FileNotFoundException e) {
			
	     	throw new ResourceNotFoundException("File Nor found :: " + e.getMessage());
	     }
		 catch( IOException e) {
			 throw new Exception("Exception Occured :: " + e.getMessage());		
	     }
		 finally {	
			 
			 if(bufferedReader!=null) {
				 try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new Exception("Exception Occured :: " + e.getMessage());		
				}
			 }
		}
		
		
		
		return  validRecordsMap;
		

	}



}