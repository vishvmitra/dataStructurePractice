package grokking.coding.interviews.patterns.slidingwindow;

/**
 * Given an array of positive numbers and a positive number ‘k,’
 * find the maximum sum of any contiguous subarray of size ‘k’.
 * [2, 1, 5, 1, 3, 2], k=3
 */
public class MaxSumInWindow {

    public static int findMaxSumInWindowK(int[] inputArr, int k) {
        if (inputArr == null || inputArr.length == 0 || k == 0) {
            return 0;
        }

        int windowStart = 0, windowEnd = 0, currSum = 0, maxSum = Integer.MIN_VALUE;

        while (windowStart < inputArr.length && windowEnd < inputArr.length && windowStart <= windowEnd) {
            if (windowEnd - windowStart + 1 < k) {
                currSum += inputArr[windowEnd];
                windowEnd++;
            }
            else {
                currSum += inputArr[windowEnd];
                windowEnd++;

                if (currSum > maxSum) {
                    maxSum = currSum;
                }

                currSum -= inputArr[windowStart];
                windowStart++;
            }
        }

        return maxSum;
    }

    public static void main(String args[]) {
        int[] inputArr1 = { 2, 3, 4, 1, 5 };
        int K1 = 2;

        int ans = findMaxSumInWindowK(inputArr1, K1);
        System.out.println("Answer: " + ans);
    }
}
