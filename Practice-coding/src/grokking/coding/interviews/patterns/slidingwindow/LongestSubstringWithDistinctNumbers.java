package grokking.coding.interviews.patterns.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring, which has all distinct characters.
 */
public class LongestSubstringWithDistinctNumbers {

    public static int findLongestSubstringWithDistinctNumbers(String input) {
        if (input == null) {
            return 0;
        }

        if (input.length() <= 1) {
            return input.length();
        }

        char[] inputArr = input.toCharArray();
        Set<Character> set = new HashSet<>();
        int windowStart = 0, windowEnd = 0, maxLength = Integer.MIN_VALUE;

        for (windowEnd = 0; windowEnd < inputArr.length; windowEnd++) {
            char currChar = inputArr[windowEnd];

            while (set.contains(currChar)) {
                    set.remove(inputArr[windowStart]);
                    windowStart++;
            }

            set.add(currChar);
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String args[]) {

        int ans = findLongestSubstringWithDistinctNumbers("abccdef");
        System.out.println("Answer: " + ans);
    }
}
