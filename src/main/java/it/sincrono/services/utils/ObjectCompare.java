package it.sincrono.services.utils;

import it.sincrono.entities.Commessa;

import it.sincrono.entities.Contratto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;



@Component
public class ObjectCompare {
	
	
	public boolean Compare(Contratto contratto,Contratto currentContratto) throws JsonProcessingException {
		
		boolean check=false;
		
		
		if(contratto.equals(currentContratto)){
			
			check=true;
			
		}
		
				   
		
		return check;
	}
	
	public boolean Compare(Commessa commessa,Commessa currentCommessa) throws JsonProcessingException {
		
		boolean check=false;
		
		if(commessa.equals(currentCommessa)) {
			
			check=true;
		}
				   
		
		return check;
	}

}
