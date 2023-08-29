package it.sincrono.services.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Commessa;

@Component
public class CommessaValidatorList {
	
	@Autowired
	CommessaValidatorUpdate commessaValidatorUpdate;
	
	
	public Boolean validate(List<Commessa> commesse) {
		
		Boolean check=true;
		
		for(Commessa commessa: commesse) {
			
			if(!commessaValidatorUpdate.validate(commessa)) {
				
				
				check=false;
				
			}
			
		}
		
		return check;
	}

}
