package com.roadmap.service;

import java.util.HashMap;
import java.util.HashSet;

public interface RoadmapService {

	
	public String determineRoadConnected(HashMap<String, String> validRecordsMap,String source,String destination,HashSet<String> citiesUnique);


}
