package cscie88a.hw4;

import cscie88a.hw2.AbstractAnimal;
import cscie88a.hw2.ActionResult;
import cscie88a.hw2.AnimalManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalManagerMethodRefTests {

	/**
	 * use static method reference as a Lambda function
	 */
	@Test
	public void testDoRun_static_method(){
		ITrainableFP lambdaMethodRef = AbstractAnimalFP::doTrickStatic;
		ActionResult result = AnimalManagerFP.trainToRun( lambdaMethodRef );
		assertEquals(ActionResult.FAILURE, result);
	}

	/**
	 * use instance method reference as a Lambda function
	 */
	@Test
	public void testDoRun_instance_method(){
		CatFP sneaky = new CatFP("Sneaky");
		ITrainableFP lambdaMethodRef = sneaky::doTrick;

		sneaky.setGoodMood(true);
		ActionResult result = AnimalManagerFP.trainToRun( lambdaMethodRef );
		assertEquals(ActionResult.SUCCESS, result);

		sneaky.setGoodMood(false);
		result = AnimalManagerFP.trainToRun( lambdaMethodRef );
		assertEquals(ActionResult.FAILURE, result);
	}

}
