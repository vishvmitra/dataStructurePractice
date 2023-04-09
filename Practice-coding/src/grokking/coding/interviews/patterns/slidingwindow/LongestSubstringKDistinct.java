package grokking.coding.interviews.patterns.slidingwindow;

import java.util.HashMap;

/**
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 * String="araaci", K=2
 * Output: 4 "araa"
 */
public class LongestSubstringKDistinct {

    public static int longestSubstringKDistinct(String string, int k) {
        if (string == null || string.length() == 0 || k == 0) {
            return 0;
        }

        if (k >= string.length()) {
            return string.length();
        }

        int windowStart = 0, windowEnd = 0, maxSize = Integer.MIN_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();

        for (windowEnd = 0; windowEnd < string.length(); windowEnd++) {
            if (map.keySet().size() < k) {
                if (!map.containsKey(string.charAt(windowEnd))) {
                    map.put(string.charAt(windowEnd), 1);
                }
                else {
                    int count = map.get(string.charAt(windowEnd));
                    map.put(string.charAt(windowEnd), count + 1);
                }
            }
            else {
               int currSize = windowEnd - windowStart + 1;
               if (currSize > maxSize) {
                   maxSize = currSize;
               }

                if (map.containsKey(string.charAt(windowEnd))) {
                    int count = map.get(string.charAt(windowEnd));
                    map.put(string.charAt(windowEnd), count + 1);
                }
                else {
                    // add end and remove start
                    map.put(string.charAt(windowEnd), 1);
                }

                while (map.keySet().size() >= k) {
                    char c = string.charAt(windowStart);
                    int count  = map.get(c);
                    if (count == 1) {
                        map.remove(c);
                    }
                    else {
                        map.put(c, map.get(c) - 1);
                    }
                    windowStart++;
                }
            }
        }

        return maxSize;
    }

    public static void main(String args[]) {

        int ans = longestSubstringKDistinct("cbbebi", 10);
        System.out.println("Answer: " + ans);
    }
}
