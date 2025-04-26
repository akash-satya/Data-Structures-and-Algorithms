import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

/**
 * This is a basic set of unit tests for PatternMatching.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs, tkapoor30
 * @version 1.0
 */
public class PatternMatchingStudentTest2 {

    private static final int TIMEOUT = 200;

    private String kmpPattern;
    private String kmpText;
    private String kmpNoMatch;
    private List<Integer> kmpAnswer;
    private List<Integer> kmpPatternEquivalencyAnswer;

    private String sellPattern;
    private String sellText;
    private String sellNoMatch;
    private List<Integer> sellAnswer;

    private String multiplePattern;
    private String multipleText;
    private List<Integer> multipleAnswer;

    private List<Integer> emptyList;

    private CharacterComparator comparator;

    private List<Integer> galilAnswer;


    @Before
    public void setUp() {
        kmpPattern = "ababa";
        kmpText = "ababaaababa";
        kmpNoMatch = "ababbaba";

        kmpAnswer = new ArrayList<>();
        kmpAnswer.add(0);
        kmpAnswer.add(6);

        kmpPatternEquivalencyAnswer = new ArrayList<>();
        kmpPatternEquivalencyAnswer.add(0);

        sellPattern = "sell";
        sellText = "She sells seashells by the seashore.";
        sellNoMatch = "sea lions trains cardinal boardwalk";

        sellAnswer = new ArrayList<>();
        sellAnswer.add(4);

        multiplePattern = "ab";
        multipleText = "abab";

        multipleAnswer = new ArrayList<>();
        multipleAnswer.add(0);
        multipleAnswer.add(2);

        emptyList = new ArrayList<>();

        comparator = new CharacterComparator();

        galilAnswer = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable() {
        /*
            pattern: ababa
            failure table: [0, 0, 1, 2, 3]
            comparisons: 4
         */
        int[] failureTable = PatternMatching
                .buildFailureTable(kmpPattern, comparator);
        int[] expected = {0, 0, 1, 2, 3};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test (timeout = TIMEOUT)
    public void testKMPMatch() {
        /*
            pattern: ababa
            text: ababaaababa
            indices: 0, 6
            expected total comparison: 18

            failure table: [0, 0, 1, 2, 3]
            comparisons: 4

        a | b | a | b | a | a | a | b | a | b | a
        --+---+---+---+---+---+---+---+---+---+---
        a | b | a | b | a |   |   |   |   |   |
        - | - | - | - | - |   |   |   |   |   |         comparisons: 5
          |   | a | b | a | b | a |   |   |   |
          |   |   |   |   | - |   |   |   |   |         comparisons: 1
          |   |   |   | a | b | a | b | a |   |
          |   |   |   |   | - |   |   |   |   |         comparisons: 1
          |   |   |   |   | a | b | a | b | a |
          |   |   |   |   | - | - |   |   |   |         comparisons: 2
          |   |   |   |   |   | a | b | a | b | a
          |   |   |   |   |   | - | - | - | - | -       comparisons: 5

         comparisons: 14
         */

        assertEquals(kmpAnswer,
                PatternMatching.kmp(kmpPattern, kmpText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 18.", 18, comparator.getComparisonCount());
    }

    @Test (timeout = TIMEOUT)
    public void testKMPNoMatch() {
        /*
            pattern: ababa
            text: ababbaba
            indices: -
            expected total comparison: 10

            failure table: [0, 0, 1, 2, 3]
            comparisons: 4

        a | b | a | b | b | a | b | a
        --+---+---+---+---+---+---+---
        a | b | a | b | a |   |   |
        - | - | - | - | - |   |   |     comparisons: 5
          |   | a | b | a | b | a |
          |   |   |   | - |   |   |     comparisons: 1

        comparisons: 6
         */
        assertEquals(emptyList,
                PatternMatching.kmp(kmpPattern, kmpNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 10.", 10, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPLongerText() {
        /*
            pattern: ababbaba
            text: ababa
            indices: -
            expected total comparison: 0
         */
        assertEquals(emptyList,
                PatternMatching.kmp(kmpNoMatch, kmpPattern, comparator));
        assertEquals(0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPPatternEqualsText() {
        /*
            pattern: ababa
            text: ababa
            indices: 0
            expected total comparison: 5 or 9 (if failure table is built)
         */
        assertEquals(kmpPatternEquivalencyAnswer,
                PatternMatching.kmp(kmpPattern, kmpPattern, comparator));
        assertTrue("Comparison count is different than expected",
                comparator.getComparisonCount() == 5
                        || comparator.getComparisonCount() == 9);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable() {
        /*
            pattern: sell
            last table: {s : 0, e : 1, l : 3}
         */
        Map<Character, Integer> lastTable = PatternMatching
                .buildLastTable(sellPattern);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('s', 0);
        expectedLastTable.put('e', 1);
        expectedLastTable.put('l', 3);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreMatch() {
        /*
            pattern: sell
            text: She sells seashells by the seashore.
            indices: 4
            expected total comparisons: 20
         */
        assertEquals(sellAnswer,
                PatternMatching.boyerMoore(sellPattern, sellText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 20.", 20, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreNoMatch() {
        /*
            pattern: sell
            text: sea lions trains cardinal boardwalk
            indices: -
            expected total comparisons: 9
         */
        assertEquals(emptyList,
                PatternMatching.boyerMoore(sellPattern,
                        sellNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 9.", 9, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreMultipleMatches() {
        /*
            pattern: ab
            text: abab
            indices: 0, 2
            expected total comparisons: 5
         */
        assertEquals(multipleAnswer,
                PatternMatching.boyerMoore(multiplePattern,
                        multipleText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 5.", 5, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreLongerText() {
        /*
            pattern: sea lions trains cardinal boardwalk
            text: sell
            indices: -
            expected total comparisons: 0
         */
        assertEquals(emptyList,
                PatternMatching.boyerMoore(sellNoMatch,
                        sellPattern, comparator));
        assertEquals(0, comparator.getComparisonCount());
    }

    @Test
    public void testRabinKarpMatch() {
        /*
            pattern: sell
            text: She sells seashells by the seashore.
            indices: 4
            expected total comparisons: 4
         */
        assertEquals(sellAnswer,
                PatternMatching.rabinKarp(sellPattern, sellText, comparator));
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpNoMatch() {
        /*
            pattern: sell
            text: sea lions trains cardinal boardwalk
            indices: -
            expected total comparisons: 0
         */
        assertEquals(emptyList,
                PatternMatching.rabinKarp(sellPattern,
                        sellNoMatch, comparator));
        assertEquals(0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpMultipleMatches() {
        /*
            pattern: ab
            text: abab
            indices: 0, 2
            expected total comparisons: 4
         */
        assertEquals(multipleAnswer,
                PatternMatching.rabinKarp(multiplePattern,
                        multipleText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpLongerText() {
        /*
            pattern: sea lions trains cardinal boardwalk
            text: sell
            indices: -
            expected total comparisons: 0
         */
        assertEquals(emptyList,
                PatternMatching.rabinKarp(sellNoMatch,
                        sellPattern, comparator));
        assertEquals(0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpEqualHash() {
        /*
            These are characters with ASCII values as shown, not the actual
            characters shown. Most do not have actual characters.

            pattern: 011
            text: 00(114)011
            indices: 2
            expected total comparisons: 5
         */
        List<Integer> answer = new ArrayList<>();
        answer.add(3);
        assertEquals(answer,
                PatternMatching.rabinKarp("\u0000\u0001\u0001",
                        "\u0000\u0000\u0072\u0000\u0001\u0001", comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 5.", 5, comparator.getComparisonCount());
    }

    /// ### MY TESTS
    @Test
    public void testKMP2() {
        kmpAnswer = PatternMatching.kmp("abab", "abab", comparator);
        List<Integer> kmpCorrectAnswer = new ArrayList<>();
        kmpCorrectAnswer.add(0);
        assertEquals(kmpCorrectAnswer, kmpAnswer);
        assertEquals(7, comparator.getComparisonCount());
    }

    @Test
    public void testKMP3() {
        List<Integer> kmpCorrectAnswer = new ArrayList<>();
        kmpCorrectAnswer.add(0);
        kmpCorrectAnswer.add(14);
        kmpCorrectAnswer.add(22);
        kmpAnswer = PatternMatching.kmp("abab", "ababdfaehfvndaababljfeabab", comparator);
        assertEquals(kmpCorrectAnswer, kmpAnswer);
        assertEquals(33, comparator.getComparisonCount());

    }

    @Test
    public void testGalilRule1() {

        galilAnswer = PatternMatching.boyerMooreGalilRule("abcdeabcd", "abcdeabcdabcdeabcdabcdeabcd", comparator);
        List<Integer> galilCorrectAnswer = new ArrayList<>();
        galilCorrectAnswer.add(0);
        galilCorrectAnswer.add(9);
        galilCorrectAnswer.add(18);
        assertEquals(galilCorrectAnswer, galilAnswer);
        assertEquals(37, comparator.getComparisonCount());

    }

    @Test
    public void testBoyerMooreExceptionHandling() {
        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.boyerMoore("", "a", comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.boyerMoore(null, "a", comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.boyerMoore("a", null, comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.boyerMoore("a", "ab", null);
        });
    }

    @Test
    public void testKMPExceptionHandling() {
        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.kmp("", "a", comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.kmp(null, "a", comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.kmp("a", null, comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.kmp("a", "ab", null);
        });
    }

    @Test
    public void testRabinKarpExceptionHandling() {
        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.rabinKarp("", "a", comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.rabinKarp(null, "a", comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.rabinKarp("a", null, comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.rabinKarp("a", "ab", null);
        });
    }

    @Test
    public void testBoyerMooreGalilExceptionHandling() {
        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.boyerMooreGalilRule("", "a", comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.boyerMooreGalilRule(null, "a", comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.boyerMooreGalilRule("a", null, comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.boyerMooreGalilRule("a", "ab", null);
        });
    }

    @Test
    public void testBoyerMoore2() {
        List<Integer> boyerCorrectAnswer = new ArrayList<>();
        boyerCorrectAnswer.add(0);
        kmpAnswer = PatternMatching.boyerMoore("bab", "babcaccccbbbcca", comparator);
        assertEquals(boyerCorrectAnswer, kmpAnswer);
        assertEquals(10, comparator.getComparisonCount());

    }

    @Test
    public void testBoyerMooreGalil2() {
        List<Integer> boyerGalilCorrectAnswer = new ArrayList<>();
        boyerGalilCorrectAnswer.add(0);
        kmpAnswer = PatternMatching.boyerMooreGalilRule("bab", "babcaccccbbbcca", comparator);
        assertEquals(boyerGalilCorrectAnswer, kmpAnswer);
        assertEquals(11, comparator.getComparisonCount());

    }

    @Test
    public void testBoyerMoore3() {
        List<Integer> boyerCorrectAnswer = new ArrayList<>();
        boyerCorrectAnswer.add(3);
        boyerCorrectAnswer.add(7);
        kmpAnswer = PatternMatching.boyerMoore("baaa", "aaabaaabaaaba", comparator);
        assertEquals(boyerCorrectAnswer, kmpAnswer);
        assertEquals(11, comparator.getComparisonCount());
    }

    @Test
    public void testBoyerMooreGalil3() {
        List<Integer> boyerGalilCorrectAnswer = new ArrayList<>();
        boyerGalilCorrectAnswer.add(3);
        boyerGalilCorrectAnswer.add(7);
        kmpAnswer = PatternMatching.boyerMooreGalilRule("baaa", "aaabaaabaaaba", comparator);
        assertEquals(boyerGalilCorrectAnswer, kmpAnswer);
        assertEquals(12, comparator.getComparisonCount());

    }

    @Test
    public void testRabinKarp2() {
        List<Integer> answer = new ArrayList<>();
        answer.add(3);
        assertEquals(answer,
                PatternMatching.rabinKarp("aaa",
                        "cacaaacbbccbbcc", comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 3.", 3, comparator.getComparisonCount());
    }


    @Test
    public void testRabinKarp3() {
        List<Integer> answer = new ArrayList<>();
        answer.add(7);
        assertEquals(answer,
                PatternMatching.rabinKarp("ccb",
                        "aacbaabccbabacc", comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 3.", 3, comparator.getComparisonCount());
    }

    @Test
    public void testRabinKarp4() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(1);
        answer.add(2);
        answer.add(3);
        answer.add(4);
        answer.add(5);
        answer.add(6);
        answer.add(7);
        answer.add(8);
        answer.add(9);
        assertEquals(answer,
                PatternMatching.rabinKarp("aaaa",
                        "aaaaaaaaaaaaa", comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 40.", 40, comparator.getComparisonCount());
    }

    @Test
    public void testRabinKarp5() {
        List<Integer> answer = new ArrayList<>();
        answer.add(9);
        assertEquals(answer,
                PatternMatching.rabinKarp("lack",
                        "sphinxofblackquartz", comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test
    public void testRabinKarp6() {
        List<Integer> answer = new ArrayList<>();
        answer.add(3);
        answer.add(7);
        assertEquals(answer,
                PatternMatching.rabinKarp("baaa",
                        "aaabaaabaaaba", comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 40.", 8, comparator.getComparisonCount());
    }


    @Test
    public void testBuildLastTableExceptionHandling() {
        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.buildLastTable(null);
        });
    }

    @Test
    public void testBuildFailureTableExceptionHandling() {
        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.buildFailureTable(null, comparator);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PatternMatching.buildFailureTable("a", null);
        });
    }

    @Test
    public void testBoyerGalilPatternLongerThanText() {
        /*
            pattern: sea lions trains cardinal boardwalk
            text: sell
            indices: -
            expected total comparisons: 0
         */
        assertEquals(emptyList,
                PatternMatching.boyerMooreGalilRule(sellNoMatch,
                        sellPattern, comparator));
        assertEquals(0, comparator.getComparisonCount());
    }
}