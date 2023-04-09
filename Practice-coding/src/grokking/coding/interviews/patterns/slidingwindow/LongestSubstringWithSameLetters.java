package grokking.coding.interviews.patterns.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string with lowercase letters only, if you are allowed to replace no more than k letters with any letter,
 * find the length of the longest substring having the same letters after replacement.
 * Input: String="aabccbb", k=2
 * Output: 5
 * Explanation: Replace the two 'c' with 'b' to have the longest repeating substring "bbbbb".
 */
public class LongestSubstringWithSameLetters {

    public static int findLongestSubstringWithSameLetters(String input, int k) {
        if (input == null || input.length() == 0) {
            return 0;
        }

        if (input.length() <= 1) {
            return input.length();
        }

        char[] inputArr = input.toCharArray();

        int windowStart = 0, windowEnd = 0, maxLength = Integer.MIN_VALUE, max_window = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();

        for (windowEnd = 0; windowEnd < inputArr.length; windowEnd++) {
            char currChar = inputArr[windowEnd];
            map.put(currChar, map.getOrDefault(currChar, 0) + 1);

            maxLength = Math.max(maxLength, map.get(currChar));

            if ((windowEnd - windowStart + 1 - maxLength) > k) {
                map.put(inputArr[windowStart], map.getOrDefault(inputArr[windowStart], 0) - 1);

                if (map.get(inputArr[windowStart]) <= 0) {
                    map.remove(inputArr[windowStart]);
                }

                windowStart++;
            }
            max_window = Math.max(max_window, windowEnd - windowStart + 1);
        }

        return max_window;
    }

    public static void main(String args[]) {
        System.out.println(findLongestSubstringWithSameLetters("aabccbb",2));
        System.out.println(findLongestSubstringWithSameLetters("abbcb", 1));
        System.out.println(findLongestSubstringWithSameLetters("abccde", 1));
    }
}
