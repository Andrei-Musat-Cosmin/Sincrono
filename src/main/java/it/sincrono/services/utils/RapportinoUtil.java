package it.sincrono.services.utils;

import org.springframework.stereotype.Component;

import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.RapportinoDto;


@Component
public class RapportinoUtil {
	
	
	
	public void calcoloRapportinoGiorniUtiliLavoro(RapportinoDto rapportinoDto) {
		
		rapportinoDto.setGiorniUtili(DateUtil.calcolaGiorniUtiliLavoro(rapportinoDto.getAnnoRequest()
				,rapportinoDto.getMeseRequest()));
		
		
		
	}
	
	
	public void calcoloRapportinoDtoGiorniLavorati(RapportinoDto rapportinoDto) {
		
		Double sommaOreLavorate=0.0;
		
		for(GiornoDto giornoDto: rapportinoDto.getMese().getGiorni()) {
			
			
			
			for(Double oreOrdinarie: giornoDto.getOreOrdinarie()) {
				
				
				sommaOreLavorate+=oreOrdinarie;
				
			}
			
			
		}
		
		
		rapportinoDto.setGiorniLavorati(sommaOreLavorate/8);
		
		
		
	}

}
