package cscie88a.streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
* Feb 27, 2020 marinapopova
*/

public class BasicReduceOperations {

	private static final Logger logger = LoggerFactory.getLogger(BasicReduceOperations.class);

	/**
	 * Sequential reduction by loop - non-parallelizable
	 */
	public static void sumByLoop() {
		int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int sum = 0;
		for (int x : numbers) {
			sum += x;
		}
		logger.info("sum = {}", sum);
	}

	/**
	 * the same reduction but via reduce() 2-arg method
	 */
	public static void sumByReduction() {
		IntStream numbersStream =  IntStream.range(0, 10);
		int sum = numbersStream.reduce(
				0,
				(x,y) -> x + y
		);
		logger.info("sum = {}", sum);
	}

	/**
	 * the same reduction but via a specialized IntStream.sum() method
	 */
	public static void sumBySpecializedSum() {
		IntStream numbersStream =  IntStream.range(0, 10);
		int sum = numbersStream.sum();
		logger.info("sum = {}", sum);
	}


	/**
	 * reduce() vs collect()
	 * Immutable reduction - via reduce() with 2 args
	 * @param listSize
	 */
	public static void concatStringsAsReduce(int listSize){
		logger.info("concatStringsAsReduce():");
		List<String> testList = StreamGeneration.getTestList(listSize);
		String joinedString = testList.stream()
				.parallel()
				.reduce("",
						(s1, s2) -> s1 + " " + s2);
		logger.info("Final joinedString = {}", joinedString);
	}

	/**
	 * reduce() vs collect()
	 * Mutable reduction - via collect()
	 * @param listSize
	 */
	public static void concatStringAsCollect(int listSize){
		logger.info("concatStringAsCollect():");
		List<String> testList = StreamGeneration.getTestList(listSize);
		StringBuilder stringBuilderContainer = testList.stream()
				.parallel()
				.collect(StringBuilder::new,
						(sb, s1) -> sb.append(" ").append(s1),
                        (sb1, sb2) -> sb1.append(sb2.toString())
                        );
		String joinedString = stringBuilderContainer.toString();
		logger.info("Final joinedString = {}", joinedString);
	}

	/**
	 * Example of a mutable reduction into a container using generic collect()
	 * adapted from JavaDocs
	 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#StreamOps
	 */
	public static void reduceIntoArrayGenericCollect(int listSize){
		logger.info("reduceIntoArrayGenericCollect():");
		List<String> testList = StreamGeneration.getTestList(listSize);
		List<String> strings = testList.stream()
				.collect(
						() -> new ArrayList<>(),
						(c, e) -> c.add(e.toString()),
						(c1, c2) -> c1.addAll(c2));
		strings.forEach(logger::info);
	}


	/**
	 * Example of a mutable reduction into a container using specialized Collector
	 * adapted from JavaDocs
	 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#StreamOps
	 */
	public static void reduceIntoArrayViaCollectors(int listSize){
		logger.info("reduceIntoArrayViaCollectors():");
		List<String> testList = StreamGeneration.getTestList(listSize);
		List<String> strings = testList.stream().collect(Collectors.toList());
		strings.forEach(logger::info);
	}

	/**
	 * Immutable reduction to the result of the SAME type - reduce 2 arg
	 * @param listSize
	 */
	public static void reduceSameType(int listSize){
		logger.info("reduceSameType():");
		List<String> testList = StreamGeneration.getTestList(listSize);
		String joinedString = testList.stream()
				.parallel()
				.reduce(
					"", 
					(s1, s2) -> s1 + " " + s2
				);
		logger.info("Final joinedString = {}", joinedString);
	}

	/**
	 * Immutable reduction to the result of a DIFFERENT type - reduce 3 arg
	 * @param listSize
	 */
	public static void reduceDiffType(int listSize){
		logger.info("reduceDiffType():");
		List<String> testList = StreamGeneration.getTestList(listSize);
		Integer sumOfLenghts = testList.stream()
				.parallel()
				.reduce(
					0, 
					(sumSoFar, nextItem) -> sumSoFar + nextItem.length(),
					(sum1, sum2) -> sum1 + sum2
				);
		logger.info("sumOfLenghts = {}", sumOfLenghts);
	}

}
