package com.tecnositaf.centrobackend.utilities;


import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;


/**
 * CommonsUtility class is a set of static functions which can be useful 
 * for the whole project.
 * @author niccolo mondino
 *
 */
public class CommonsUtility {
	
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILURE = "FAILURE";
		
	private CommonsUtility() {}

	public static <T> boolean isNull(T object) {
		return object==null;
	}
	
	public static <T> T fromJsonToObject(String json, Class<T> clazz) {
		T output = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			output = mapper.readValue(json, clazz);
			return output;
		} catch (IOException e) {
			throw new RuntimeException("[ERROR] FAILURE DURING PARSING JSON STRING - json => " + json);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static <T> List<T> fromJsonToList(String json, Class<T> classDto, Class<? extends List> classCollection) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CollectionType javaType = mapper.getTypeFactory().constructCollectionType(classCollection, classDto);
			return mapper.readValue(json, javaType);
			
		} catch (JsonProcessingException e) {
			throw new RuntimeException("[ERROR] FAILURE DURING PARSING JSON STRING - json => " + json);
		}
	}
	
	
	
}
