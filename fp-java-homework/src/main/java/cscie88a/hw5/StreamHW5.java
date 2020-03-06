package cscie88a.hw5;

import cscie88a.hw4.AnimalType;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
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

        Double average = resultStream
                            .mapToInt(StreamAnimal::getAge)
                            .average()
                            .getAsDouble();

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
        int number = 100; // 100 per spec
        Stream<StreamAnimal> resultStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(number);
        Collector<StreamAnimal, ?, Map<AnimalType, Long>> countingAnimalTypes
                = Collectors.groupingBy(StreamAnimal::getAnimalType, Collectors.counting());

        Map<AnimalType, Long> result = resultStream
                                            .parallel() // per spec
                                            .peek((a) -> System.out.println("Thread: " + Thread.currentThread()))
                                            .collect(countingAnimalTypes);

        return result;
    }

    public static Map<AnimalType, Long> getAnimalGroupCountsCustomPool() {
        int number = 100; // 100 per spec
        Stream<StreamAnimal> resultStream = AnimalGenerator.generateStreamOfAnimalsFromCollection(number);
        Collector<StreamAnimal, ?, Map<AnimalType, Long>> countingAnimalTypes
                = Collectors.groupingBy(StreamAnimal::getAnimalType, Collectors.counting());

        ForkJoinPool customThreadPool = new ForkJoinPool(8);
        Map<AnimalType, Long> result =
                customThreadPool.submit(
                    () -> resultStream
                            .parallel()
                            .peek((a) -> System.out.println("Thread: " + Thread.currentThread()))
                            .collect(countingAnimalTypes)
                ).join();

        return result;

        //explain and demo the difference with 5.1 implementation
    }

    public static Stream<String> randomizeString(String inputString) {

        Stream<String> resultStream = Stream.iterate(
                inputString,
                previousString -> replaceChar(previousString));

        return resultStream;
    }

    private static String replaceChar(String inputString) {
        Random random = new Random();

        int len = inputString.length();
        int indexToReplace = random.nextInt(len);
        char newChar = (char)(random.nextInt(26) + 'a');;

        StringBuilder result = new StringBuilder(inputString);
        result.setCharAt(indexToReplace, newChar);

        return result.toString();
    }
}
