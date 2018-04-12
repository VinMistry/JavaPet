package presentation;

import animals.*;

public class AnimalFactoryMaker {
	public Animal getPredator(String animal, String name){ 
		if(animal.equalsIgnoreCase("FROG")){
			return new Frog(name);
		}
		else if(animal.equalsIgnoreCase("SHARK")){
			return new Shark(name);
		}
		else if(animal.equalsIgnoreCase("CAT")){
			return new Cat(name);
		}
		return null;
	}
}
