package cscie88a.hw2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AllAnimalTests {

	private Cat sneaky;
	private Dog bolt;
	private Toy toy_squeaky;
	private Toy toy_not_squeaky;

	@BeforeEach
	void setUp() throws Exception {
		sneaky = new Cat("Sneaky", "blue", "gray");
		bolt = new Dog("Bolt", "brown", "whity");
		toy_squeaky = new Toy(true, true, true);
		toy_not_squeaky = new Toy(false, true, true);
	}

	@Test
	void testTakeMedicine() {
		assertEquals(ActionResult.SUCCESS, bolt.takeMedicine(true));
		assertEquals(ActionResult.FAILURE, bolt.takeMedicine(false));
		assertEquals(ActionResult.FAILURE, sneaky.takeMedicine(true));
		assertEquals(ActionResult.FAILURE, sneaky.takeMedicine(false));
	}

	@Test
	void testWhoAreYou() {
		assertEquals("I am Sneaky !", sneaky.whoAreYou());
		assertEquals("I am Bolt !", bolt.whoAreYou());
	}

	@Test
	public void testSayHi() {
		String humanName = "Marina";
		sneaky.sayHiToHuman(humanName);
		bolt.sayHiToHuman(humanName);
	}

	@Test
	public void testAbstractClassCreation() {
		// demo error creating AbstractAnimal directly
		// will NOT compile
		//AbstractAnimal unknownAnimal = new AbstractAnimal();
	}
	
	@Test
	public void testDoTrick() {
		ActionResult result = sneaky.doTrick("sit");
		assertEquals(ActionResult.FAILURE, result);

		result = bolt.doTrick("sit");
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test
	public void testDoTrickForTreat() {
		ActionResult result = sneaky.doTrickForTreat("sit", "yummyTreat");
		assertEquals(ActionResult.FAILURE, result);

		result = bolt.doTrickForTreat("sit", "yummyTreat");
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test 
	public void testPlayWithMe() {
		// unfriendly cat will not play with anyone
		sneaky.setFriendly(false);
		ActionResult result = sneaky.playWithMe(bolt);
		assertEquals(ActionResult.FAILURE, result);
		
		// a friendly cat will play with others
		sneaky.setFriendly(true);
		result = sneaky.playWithMe(bolt);
		assertEquals(ActionResult.SUCCESS, result);
		
		// dog will play with anybody, always
		result = bolt.playWithMe(sneaky);
		assertEquals(ActionResult.SUCCESS, result);
	}	@Test

	public void testPlayWithToy() {
		// squeaky toy
		assertEquals(ActionResult.SUCCESS, sneaky.playWithToy(toy_squeaky));
		assertEquals(ActionResult.SUCCESS, bolt.playWithToy(toy_squeaky));

		// non-squeaky toy
		assertEquals(ActionResult.FAILURE, sneaky.playWithToy(toy_not_squeaky));
		assertEquals(ActionResult.SUCCESS, bolt.playWithToy(toy_not_squeaky));
	}
}
