package cscie88a.hw5;

import cscie88a.hw4.AnimalType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import java.util.function.Supplier;

public class AnimalGenerator {

    static int numberOfCats = 0;
    static int numberOfDogs = 0;
    static int numberOfHedgehogs = 0;

    // Iterative creation
    public static Stream<StreamAnimal> generateStreamOfAnimalsFromCollection(int numberOfItems) {
        List<StreamAnimal> animalList = new ArrayList<>(numberOfItems);

        Random random = new Random();

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

        Stream<StreamAnimal> resultStream = animalList.stream();

        return resultStream;
    }

    // Supplier abstract method
    public static StreamAnimal getNewAnimal() {
        AnimalType newAnimal = generateAnimalType();

        Random random = new Random();

        String newName;

        if (newAnimal == AnimalType.CAT) {
            numberOfCats++;
            newName = "GeneratedCat" + numberOfCats;
        } else if (newAnimal == AnimalType.DOG){
            numberOfDogs++;
            newName = "GeneratedDog" + numberOfDogs;
        } else {
            numberOfHedgehogs++;
            newName = "GeneratedHedgehogs" + numberOfHedgehogs;
        }

        StreamAnimal animal =
                new StreamAnimal(newAnimal,             // randomly a hedgehog, cat or dog
                        newName,                        // named as ANIMALTYPE and appending a number
                        random.nextBoolean(),           // randomly set to TRUE or FALSE
                        random.nextInt(20));    // random integer between 0 and 20

        return animal;
    }

    // Using supplier as lambda
    public static Stream<StreamAnimal> generateStreamOfAnimals_lambda() {

        Stream<StreamAnimal> resultStream = Stream.generate(
                () -> getNewAnimal()
        );

        return resultStream;
    }

    // Using suppler as method ref to generate()
    public static Stream<StreamAnimal> generateStreamOfAnimals_methodRef() {
        Stream<StreamAnimal> resultStream = Stream.generate(
                AnimalGenerator::getNewAnimal
        );

        return resultStream;
    }

    // Private helper method
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
