package it.davidenastri.persistence.service;

import it.davidenastri.persistence.entity.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
   public Plant getPlantByName(String name){
       return new Plant();
   }
}