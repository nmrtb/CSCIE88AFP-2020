package cscie88a.hw5;

import cscie88a.hw4.AnimalType;
import cscie88a.hw5.*;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalGeneratorTests {

	@Test
	// Test age range
	public void testGenerateStreamOfAnimalsFromCollectionAge(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(5);
		newStream.forEach(animal -> assertTrue(
				animal.getAge() >= 0 && animal.getAge() <=20)
		);
	}

	@Test
	// Test type
	public void testGenerateStreamOfAnimalsFromCollectionType(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(5);
		newStream.forEach(animal -> assertTrue(
						(animal.getAnimalType() == AnimalType.CAT ||
								animal.getAnimalType() == AnimalType.DOG ||
								animal.getAnimalType() == AnimalType.HEDGEHOG)
			)
		);
	}

	@Test
	// Test shots
	public void testGenerateStreamOfAnimalsFromCollectionShots(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(5);
		newStream.forEach(animal -> assertTrue(
						(animal.isHasCurrentShots() == true ||
								animal.isHasCurrentShots() == false)
				)
		);
	}

	@Test
	// Test name starts with correct string
	public void testGenerateStreamOfAnimalsFromCollectionNameStart(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(5);
		newStream.forEach(animal ->
				assertTrue(
						(animal.getName().startsWith("GeneratedDog") ||
								animal.getName().startsWith("GeneratedCat") ||
								animal.getName().startsWith("GeneratedHedgehog")))
		);
	}

	@Test
	// Test name ends with digit
	public void testGenerateStreamOfAnimalsFromCollectionNameEnd(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(5);
		newStream.forEach(animal ->
				Character.isDigit((animal.getName().charAt(animal.getName().length()-1))));
	}

	@Test
	// Test number of items
	public void testGenerateStreamOfAnimalsFromCollectionNumber(){
		int number = 5;
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(number);
		assertEquals(newStream.count(), number);
	}

	@Test
	// Prints stream for visual checking
	public void printGenerateStreamOfAnimalsFromCollection(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(10);
		newStream.forEach(animal -> System.out.println(animal));
	}

	@Test
	// Prints stream for visual checking
	public void printgetNewAnimal(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(10);
		newStream.forEach(animal -> System.out.println(animal));

		StreamAnimal newStreamAnimal = AnimalGenerator.getNewAnimal();
		System.out.println(newStreamAnimal);
		StreamAnimal newStreamAnimal2 = AnimalGenerator.getNewAnimal();
		System.out.println(newStreamAnimal2);

	}

	// TODO do all same tests for getNewAnimal

	@Test
//	 Prints stream for visual checking
	public void testGenerateStreamOfAnimals_lambda(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimals_lambda()
											.limit(10);

		newStream.forEach(animal -> System.out.println(animal));
	}

	// TODO do all same tests for testGenerateStreamOfAnimals_lambda

	@Test
//	 Prints stream for visual checking
	public void testGenerateStreamOfAnimals_methodRef(){
		Stream<StreamAnimal> newStream = AnimalGenerator.generateStreamOfAnimals_methodRef()
				.limit(10);

		newStream.forEach(animal -> System.out.println(animal));
	}

	// TODO do all same tests for testGenerateStreamOfAnimals_methodRef

}
