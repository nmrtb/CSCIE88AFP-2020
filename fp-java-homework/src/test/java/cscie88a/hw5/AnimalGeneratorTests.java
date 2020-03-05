package cscie88a.hw5;

import cscie88a.hw4.AnimalType;
import cscie88a.hw5.*;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalGeneratorTests {

	@Test
	public void testGenerateStreamOfAnimalsFromCollection(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(5);
//		newStream.forEach(animal -> System.out.println(animal));
		newStream.forEach(animal -> assertTrue((
				(animal.getAge() >= 0 && animal.getAge() <=20)) &&
				(animal.getAnimalType() == AnimalType.CAT ||
						animal.getAnimalType() == AnimalType.DOG ||
						animal.getAnimalType() == AnimalType.HEDGEHOG) &&
				(animal.isHasCurrentShots() == true ||
						animal.isHasCurrentShots() == false)
				));
	}
}
