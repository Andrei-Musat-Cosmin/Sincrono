package it.sincrono.services.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Commessa;

@Component
public class CommessaValidatorList {
	
	@Autowired
	CommessaValidator commessaValidator;
	
	
	public Boolean validate(List<Commessa> commesse,Boolean isNew) {
		
		Boolean check=true;
		
		for(Commessa commessa: commesse) {
			
			if(!commessaValidator.validate(commessa, isNew)) {
				
				
				check=false;
				
			}
			
		}
		
		return check;
	}

}
