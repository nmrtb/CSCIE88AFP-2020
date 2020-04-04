package cscie88a.streams;

import cscie88a.basics4.ActionResult;
import cscie88a.fp1.AbstractAnimalFP;
import cscie88a.fp1.DogFP;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		DogFP stitch = new DogFP("Stitch");
		stitch.setHasCurrentShots(true);
		IAdoptable lambdaMethodRef = stitch::checkForAdoptionStatusInstance;

		ActionResult result = service.tryToAdopt( lambdaMethodRef );
		assertEquals(ActionResult.SUCCESS, result);

		stitch.setHasCurrentShots(false);
		result = service.tryToAdopt( lambdaMethodRef );
		assertEquals(ActionResult.FAILURE, result);
	}

	@Test
	public void testIdentityConstraint(){
		AdoptionService service = new AdoptionService();
		DogFP adoptableTrue = new DogFP("Adoptable");
		DogFP adoptableFalse = new DogFP("Adoptable");

		Map<Boolean, List<IAdoptable>> supplierWithIdentityConstraint =
				Map.of(true, new ArrayList(), false, new ArrayList());
	}

}
