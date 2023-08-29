package it.sincrono.services.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Commessa;

@Component
public class CommessaValidatorList {
	
	@Autowired
	private CommessaValidatorUpdate commessaValidatorUpdate;
	
	@Autowired
	private CommessaValidator commessaValidator;
	
	
	public Boolean validate(List<Commessa> commesse,boolean checkValidator,boolean isNew) {
		
		Boolean check=true;
		
		for(Commessa commessa: commesse) {
			
			if(checkValidator) {
				
				if(!commessaValidatorUpdate.validate(commessa)) {
					
					
					check=false;
					
				}
				
			}else {
				
				if(!commessaValidator.validate(commessa,isNew)) {
					
					
					check=false;
					
				}
				
				
			}
			
		}
		
		return check;
	}

}
