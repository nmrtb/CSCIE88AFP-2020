package cscie88a.hw5;

import cscie88a.hw4.AnimalType;
import org.junit.jupiter.api.Test;

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


}
