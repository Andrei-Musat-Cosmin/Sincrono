package it.sincrono.services.utils;

import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class ObjectCompare {
	
	
	public boolean Compare(Contratto contratto,Contratto currentContratto) throws JsonProcessingException {
		
		boolean check=false;
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		if(objectMapper.writeValueAsString(contratto).equals(objectMapper.writeValueAsString(currentContratto))){
			
			check=true;
			
		}
		
				   
		
		return check;
	}
	
	public boolean Compare(Commessa commessa,Commessa currentComnmessa) throws JsonProcessingException {
		
		boolean check=false;
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		if(objectMapper.writeValueAsString(commessa).equals(objectMapper.writeValueAsString(currentComnmessa))){
			
			check=true;
			
		}
		
				   
		
		return check;
	}

}
