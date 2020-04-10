package com.tecnositaf.centrobackend.utilities;


import java.io.IOException;
import java.util.List;

//import java.util.Collection;
//import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
//import com.fasterxml.jackson.databind.type.CollectionType;
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;


/**
 * Commons class is a set of static functions which can be useful 
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
		/*
		 * Alternative catch
		 * catch (JsonProcessingException e) {
			throw new FailureException(Errors.PARSING_JACKSON_EXCEPTION,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		   }
		 */
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
		/*
		 * Alternative catch
		 * catch (JsonProcessingException e) {
				throw new FailureException(Errors.PARSING_JACKSON_EXCEPTION,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		   }
		 */
		
	}
	
	
	
}
