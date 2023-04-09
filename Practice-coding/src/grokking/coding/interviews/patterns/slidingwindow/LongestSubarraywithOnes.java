package grokking.coding.interviews.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array containing 0s and 1s,
 * if you are allowed to replace no more than ‘k’ 0s with 1s,
 * find the length of the longest contiguous subarray having all 1s.
 *
 * Input: Array=[0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1], k=2
 * Output: 6
 * Explanation: Replace the '0' at index 5 and 8 to have the longest contiguous subarray of 1s having length 6.
 */
public class LongestSubarraywithOnes {


    public static int findLongestSubstringWithAllOnes(String input, int k) {
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
        System.out.println(findLongestSubstringWithAllOnes("01100011011",2));
        System.out.println(findLongestSubstringWithAllOnes("0100110110011", 3));
    }
}
