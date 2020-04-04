package cscie88a.streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StateOperationsTest {

    @Test
    public void testGroupByRegularOneArg() {
        StateOperations.groupByRegularOneArg(20);
    }

    @Test
    public void testGroupByRegularThreeArg(){
        StateOperations.groupByRegularThreeArg(20);
    }

    @Test
    public void testGroupByConcurrentOneArg(){
        StateOperations.groupByConcurrentOneArg(20);
    }

    @Test
    public void testGroupByConcurrentThreeArg(){
        StateOperations.groupByConcurrentThreeArg(20);
    }

    @Test
    public void testgroupByFirstCharRegular(){
        Map<String, List<String>> output = StateOperations.groupByFirstCharRegular();

        List<String> expectedC = List.of("Cat", "cat", "cot");
        List<String> expectedT = List.of("The", "trice", "two", "thereabouts");

        assertTrue(output.get("c").size() == 3);
        assertTrue(output.get("t").size() == 4);

        assertTrue(output.get("c").containsAll(expectedC));
        assertTrue(output.get("t").containsAll(expectedT));

        System.out.println(output.get("c"));
        System.out.println(output.get("t"));
    }

    @Test
    public void testgroupByFirstCharConcurrent(){
        Map<String, List<String>> output = StateOperations.groupByFirstCharConcurrent();

        List<String> expectedC = List.of("Cat", "cat", "cot");
        List<String> expectedT = List.of("The", "trice", "two", "thereabouts");

        assertTrue(output.get("c").size() == 3);
        assertTrue(output.get("t").size() == 4);

        assertTrue(output.get("c").containsAll(expectedC));
        assertTrue(output.get("t").containsAll(expectedT));

        System.out.println(output.get("c"));
        System.out.println(output.get("t"));
    }

}