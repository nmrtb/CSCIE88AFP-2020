package cscie88a.hw5;

import cscie88a.hw4.AnimalType;

import java.util.stream.Stream;

public class StreamHW5 {

    public static void testForHealthyAnimals(int numberOfItems) { // TODO do I need number here?

        Stream<StreamAnimal> resultStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(numberOfItems)
                .filter(HealthyAnimalFilter.filter);

        resultStream.forEach(animal -> System.out.println(animal));
    }

    public static void testForHealthyCats(int numberOfItems) { // TODO do I need number here?

        Stream<StreamAnimal> resultStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(numberOfItems)
                .filter(HealthyAnimalFilter.filter)
                .filter(animal -> animal.getAnimalType() == AnimalType.CAT);

        resultStream.forEach(animal -> System.out.println(animal));
    }

    public static void getNamesOfHealthyAnimals() {
        AnimalGenerator.generateStreamOfAnimalsFromCollection(20)
                .filter(HealthyAnimalFilter.filter)
                .map(animal -> animal.getName())
                .forEach(name -> System.out.println(name));
    }
}
