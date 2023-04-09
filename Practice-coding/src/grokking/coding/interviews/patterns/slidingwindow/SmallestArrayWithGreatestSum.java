package grokking.coding.interviews.patterns.slidingwindow;

/**
 * 2 loops - variable size
     * Given an array of positive integers and a number ‘S,’
     * find the length of the smallest contiguous sub-array whose sum is greater than or equal to ‘S’.
     * Return 0 if no such sub-array exists.
     * Array = [2, 1, 5, 2, 3, 2], S = 7
     * Array = [2, 1, 5, 2, 8], S = 7
     * Array = [3, 4, 1, 1, 6], S = 8
     *
     * Return if sum is not
 */
public class SmallestArrayWithGreatestSum {

    public static int findMinSubArray(int[] arr, int S) {
        if (arr == null || arr.length == 0 || S == 0) {
            return 0;
        }

        int start = 0, end = 0, currSum = 0, minSize = Integer.MAX_VALUE;
        while (start < arr.length && end <= arr.length && start <= end) {
            if (currSum >= S) {
                int currLen = end - start;

                if (currLen < minSize) {
                    minSize = currLen;
                }

                currSum -= arr[start];
                start++;
            }
            else if (end < arr.length) {
                currSum += arr[end];
                end++;
            }
        }

        return minSize;
    }

    private static int findMinSubArray1(int[] a, int S) {
        int n = a.length;

        int lengthOfSmallestSubarray = Integer.MAX_VALUE;
        int windowSum = 0;

        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < n; windowEnd++) {
            windowSum += a[windowEnd]; // Add the next element to the window

            while(windowSum >= S) { // Shrink the window as small as possible until the 'windowSum' is smaller than 'K'
                lengthOfSmallestSubarray = Math.min(lengthOfSmallestSubarray, windowEnd-windowStart+1);
                windowSum -= a[windowStart]; // Discard the element at 'windowStart' since it is going out of the window
                windowStart++; // Shrink the window
            }
        }

        return lengthOfSmallestSubarray == Integer.MAX_VALUE ? 0 : lengthOfSmallestSubarray;
    }

    public static void main(String args[]) {
        int[] inputArr1 = { 3, 4, 1, 1, 6 };
        int K1 = 6;

        int ans = findMinSubArray1(inputArr1, K1);
        System.out.println("Answer: " + ans);
    }
}
