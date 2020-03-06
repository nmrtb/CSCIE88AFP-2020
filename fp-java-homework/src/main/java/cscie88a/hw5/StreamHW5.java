package cscie88a.hw5;

import cscie88a.hw4.AnimalType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
        AnimalGenerator.generateStreamOfAnimalsFromCollection(20) // 20 per spec
                .filter(HealthyAnimalFilter.filter)
                .map(animal -> animal.getName())
                .forEach(name -> System.out.println(name));
    }

    public static Optional<StreamAnimal> getOldestAnimal() { //TODO why optional? can I make it always return a StreamAnimal
        return AnimalGenerator.generateStreamOfAnimalsFromCollection(20) // 20 per spec }
                .reduce((maxSoFar, nextAnimal) -> maxSoFar.getAge() > nextAnimal.getAge() ? maxSoFar : nextAnimal);    }

    public static Double getAverageAge() {
        Stream<StreamAnimal> resultStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(20); // 20 per spec }

        Double average = resultStream.reduce(0.0,
                            (a, b) -> a + b.getAge(),
                            (a, b) -> a + b);
        // TODO currently only giving the total
//        average = average / resultStream.count();
        // TODO Explain the difference of reduce 2 arg vs 3 arg versions
        return average;
    }

    public static List<String> getAnimalNamesAsList() {
        int number = 20; // 20 per spec
        Stream<StreamAnimal> resultStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(number);
        List<String> resultList = resultStream.collect(
                () -> new ArrayList<>(),
                (array, item) -> array.add(item.getName()),
                (item1, item2) -> item1.addAll(item2)
        );

        return resultList;
    }


    public static Map<AnimalType, Long> getAnimalGroupCounts() {
        int number = 100; // 20 per spec
        Stream<StreamAnimal> resultStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(number);
        Collector<StreamAnimal, ?, Map<AnimalType, Long>> countingAnimalTypes
                = Collectors.groupingBy(StreamAnimal::getAnimalType, Collectors.counting());

        Map<AnimalType, Long> result = resultStream.collect(countingAnimalTypes);

        return result;
    }
}
