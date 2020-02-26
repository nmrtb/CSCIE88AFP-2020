package cscie88a.hw4;

import cscie88a.hw2.ActionResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdoptionTests {

	@Test
	public void testMethodReference_static(){
		AdoptionService service = new AdoptionService();
		IAdoptable lambdaMethodRef = AbstractAnimalFP::checkForAdoptionStatusStatic;
		ActionResult result = service.tryToAdopt( lambdaMethodRef );
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test
	public void testMethodReference_specific_instance(){
		AdoptionService service = new AdoptionService();
		DogFP lilo = new DogFP("Lilo");
		lilo.setHasCurrentShots(true);
		IAdoptable lambdaMethodRef = lilo::checkForAdoptionStatusInstance;

		ActionResult result = service.tryToAdopt( lambdaMethodRef );
		assertEquals(ActionResult.SUCCESS, result);

		lilo.setHasCurrentShots(false);
		result = service.tryToAdopt( lambdaMethodRef );
		assertEquals(ActionResult.FAILURE, result);
	}

	@Test
	public void testIAdoptable1_lambda1(){

		AdoptionService service = new AdoptionService();

		ActionResult result = service.tryToAdopt( () -> true );
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test
	public void testIAdoptable2_lambda1(){

		AdoptionService service = new AdoptionService();

		ActionResult result = service.tryToAdopt2( (healthCheckDone) -> true, true );
		assertEquals(ActionResult.SUCCESS, result);

		result = service.tryToAdopt2( (healthCheckDone) -> false, false );
		assertEquals(ActionResult.FAILURE, result);

	}

	@Test
	public void testIAdoptable1_lambda2(){

		AdoptionService service = new AdoptionService();
		IAdoptable lambdaForI1 = () -> true;

		ActionResult result = service.tryToAdopt( lambdaForI1 );
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test
	public void testIAdoptable2_lambda2(){

		AdoptionService service = new AdoptionService();

		IAdoptable2 lambdaForI2 = (healthCheckDone) -> true;

		ActionResult result = service.tryToAdopt2( lambdaForI2, true );
		assertEquals(ActionResult.SUCCESS, result);

		lambdaForI2 = (healthCheckDone) -> false;

		result = service.tryToAdopt2( lambdaForI2, false );
		assertEquals(ActionResult.FAILURE, result);

	}

}
