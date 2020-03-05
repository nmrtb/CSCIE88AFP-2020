package cscie88a.hw5;

import java.util.function.Predicate;

public class HealthyAnimalFilter {

	public static Predicate<StreamAnimal> filter = new Predicate<StreamAnimal>() {
		@Override
		public boolean test(StreamAnimal animal) {
			if (animal.isHasCurrentShots() == true)
				return true;
			else
				return false;
		}
	};
}