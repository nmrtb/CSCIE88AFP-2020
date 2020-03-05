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
        Random random = new Random();

        int numberOfCats = 0;
        int numberOfDogs = 0;
        int numberOfHedgehogs = 0;

        for (int i = 0; i<numberOfItems; i++) {
            AnimalType newAnimal = generateAnimalType();

            String newName;

            if (newAnimal == AnimalType.CAT) {
                numberOfCats++;
                newName = "GeneratedCat" + numberOfCats;
            } else if (newAnimal == AnimalType.DOG){
                numberOfDogs++;
                newName = "GeneratedDog" + numberOfDogs;
            } else {
                numberOfHedgehogs++;
                newName = "GeneratedHedgehog" + numberOfHedgehogs;
            }

            StreamAnimal animal =
                    new StreamAnimal(newAnimal,                     // randomly a hedgehog, cat or dog
                                    newName,                        // named as ANIMALTYPE and appending a number
                                    random.nextBoolean(),           // randomly set to TRUE or FALSE
                                    random.nextInt(20));    // random integer between 0 and 20
            animalList.add(animal);
        }
//        (3) Generates a stream from this collection
        Stream<StreamAnimal> resultStream = animalList.stream();

        return resultStream;
    }

    private static AnimalType generateAnimalType() {
        AnimalType result;

        Random random = new Random();
        int number = random.nextInt(3); // generates between 0 and 2

        if (number == 0) {
            result = AnimalType.CAT;
        } else if (number == 1){
            result = AnimalType.DOG;
        } else {
            result = AnimalType.HEDGEHOG;
        }

        return result;
    }
}
