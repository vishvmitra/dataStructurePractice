package grokking.coding.interviews.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * You are visiting a farm to collect fruits. The farm has a single row of fruit trees. You will be given two baskets, and your goal is to pick as many fruits as possible to be placed in the given baskets.
 *
 * You will be given an array of characters where each character represents a fruit tree. The farm has following restrictions:
 *
 * Each basket can have only one type of fruit. There is no limit to how many fruit a basket can hold.
 * You can start with any tree, but you can’t skip a tree once you have started.
 * You will pick exactly one fruit from every tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
 */
public class FruitsIntoBasket {

    public static int findLength(String input) {
        // TODO: Write your code here
        char[] inputArr = input.toCharArray();

        if (input == null || inputArr.length <= 2) {
            return inputArr.length;
        }

        Map<Character, Integer> map = new HashMap<>();
        int windowStart = 0, windowEnd = 0, maxLength = Integer.MIN_VALUE;

        for (windowEnd = 0; windowEnd < inputArr.length; windowEnd++) {
            map.put(inputArr[windowEnd], map.getOrDefault(inputArr[windowEnd], 0) + 1);

            while (map.keySet().size() > 2) {
                    map.put(inputArr[windowStart], map.getOrDefault(inputArr[windowStart], 0) - 1);

                    if (map.get(inputArr[windowStart]) <= 0) {
                        map.remove(inputArr[windowStart]);
                    }
                    windowStart++;
            }
            int currSize = windowEnd - windowStart + 1;
            maxLength = Math.max(maxLength, currSize);
        }

        return maxLength;
    }

    public static void main(String args[]) {

        int ans = findLength("abcac");
        System.out.println("Answer: " + ans);
    }
}
