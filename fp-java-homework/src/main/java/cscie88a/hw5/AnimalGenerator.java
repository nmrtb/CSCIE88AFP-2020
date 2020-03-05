package cscie88a.hw5;

import cscie88a.hw4.AnimalType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class AnimalGenerator {

    public static Stream<StreamAnimal> generateStreamOfAnimalsFromCollection(int numberOfItems) {
//        (1) creates a List of StreamAnimal objects - with the specified number of object
        List<StreamAnimal> animalList = new ArrayList<>(numberOfItems);
//        (2) generate approximately equal amount of Cats, Dogs and Hedgehogs
//          - randomly set their hasCurrentShots attribute to FALSE or TRUE
//          - assign them names with appended number of the generated item; for example: "GeneratedCat1", "GeneratedDog2", "GeneratedCat3", ... (does not matter, as long as they are unique)
        Random random = new Random();
        for (int i = 0; i<numberOfItems; i++) {
            StreamAnimal animal =
                    new StreamAnimal(AnimalType.CAT,            // randomly a hedgehog, cat or dog
                              "Cat" + i,                      // named as ANIMALTYPE and appending a number
                                    random.nextBoolean(),                       // randomly set to TRUE or FALSE
                                    random.nextInt(20));    // random integer between 0 and 20
            animalList.add(animal);
        }
//        (3) Generates a stream from this collection
        Stream<StreamAnimal> resultStream = animalList.stream();

        return resultStream;
    }
}
