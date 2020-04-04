package cscie88a.hw5;

import cscie88a.hw4.AnimalType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
		System.out.println("Oldest: " + oldest);
	}

	@Test
	// Calls method for visual checking
	public void TestGetAverageAge(){
		Double average = StreamHW5.getAverageAge();
		System.out.println("Average: " + average);
	}

	@Test
	// Calls method for visual checking
	public void TestGetAnimalNamesAsList(){
		List<String> testList = StreamHW5.getAnimalNamesAsList();
		System.out.println("List of names: " + testList);
	}

	@Test
	// Calls method for visual checking
	public void TestGetAnimalGroupCounts(){
		Map<AnimalType, Long> result = StreamHW5.getAnimalGroupCounts();
		System.out.println(result);
	}

	@Test
	// Calls method for visual checking
	public void TestGetAnimalGroupCountsCustomPool(){
		Map<AnimalType, Long> result = StreamHW5.getAnimalGroupCountsCustomPool();
		System.out.println(result);
	}

	@Test
	// Calls method for visual checking
	public void TestRandomizeString(){

	StreamHW5.randomizeString("CSCIE88A Rocks!")
				.limit(10)
				.forEach((string) -> System.out.println(string));
	}

	@Test
	public void TestInterference() {
		List<String> example = new ArrayList<String>();
		example.add("Example 1");
		example.add("Example 2");
		example.add("Example 3");
		System.out.println(example);

		// Interference
		example.add("Interference Example 4");
		System.out.println(example);

		// Non-inteference
		example.set(3, "Example 4");
		System.out.println(example);
	}

	@Test
	public void TestStatelessness() {
		List<Integer> example = new ArrayList<Integer>();
		example.add(1);
		example.add(2);
		example.add(3);
		System.out.println(example);

		// Stateful
		int counterStateful = 0;

		for (int i = 0; i < example.size(); i++) {
			counterStateful++;
		}
		System.out.println(counterStateful);

		// Stateless
		long counterStateless = example.stream().count();
		System.out.println(counterStateless);
	}

	@Test
	public void TestAssociateive() {
		List<Integer> example = new ArrayList<Integer>();
		example.add(1);
		example.add(2);
		example.add(3);
		System.out.println(example);

		// Associative
		int outputAssociative1 = example.get(0) * example.get(1) * example.get(2);
		int outputAssociative2 = example.get(2) * example.get(1) * example.get(0);
		System.out.println(outputAssociative1 == outputAssociative2);

		// Non-associative
		float outputNonAssociative1 = (float)example.get(0) / example.get(1) / example.get(2);
		float outputNonAssociative2 = (float)example.get(2) / example.get(1) / example.get(0);
		System.out.println(outputNonAssociative1 == outputNonAssociative2);
		System.out.println(outputNonAssociative1);
		System.out.println(outputNonAssociative2);
	}


}
