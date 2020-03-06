package cscie88a.hw5;

import cscie88a.hw4.AnimalType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StreamHW5Tests {

	@Test
	// Calls method for visual checking
	public void testTestForHealthyAnimals(){
		StreamHW5.testForHealthyAnimals(20);
	}

	@Test
	// Calls method for visual checking
	public void testTestForHealthyCats(){
		StreamHW5.testForHealthyCats(20);
	}

	@Test
	// Calls method for visual checking
	public void testGetNamesOfHealthyAnimals(){
		StreamHW5.getNamesOfHealthyAnimals();
	}

	@Test
	// Calls method for visual checking
	public void TestGetOldestAnimal(){
		Optional<StreamAnimal> oldest = StreamHW5.getOldestAnimal();
		System.out.println(oldest);
	}

	@Test
	// Calls method for visual checking
	public void TestGetAverageAge(){
		Double average = StreamHW5.getAverageAge();
		System.out.println(average);
	}

	@Test
	// Calls method for visual checking
	public void TestGetAnimalNamesAsList(){
		List<String> testList = StreamHW5.getAnimalNamesAsList();
		System.out.println(testList);
	}
	@Test
	// Calls method for visual checking
	public void TestGetAnimalGroupCounts(){
		Map<AnimalType, Long> result = StreamHW5.getAnimalGroupCounts();
		System.out.println(result);
	}

}
