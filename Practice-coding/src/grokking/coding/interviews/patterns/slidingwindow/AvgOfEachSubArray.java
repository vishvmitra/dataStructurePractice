package grokking.coding.interviews.patterns.slidingwindow;

/**
    Given an array, find the average of each subarray of ‘K’ contiguous elements in it.
    Array: [1, 3, 2, 6, -1, 4, 1, 8, 2], K=5
 */

public class AvgOfEachSubArray {

    public static float[] findAverages1(int[] inputArr, int K) {
        if (inputArr == null || inputArr.length == 0 || K == 0) {
            return new float[0];
        }

        if (K >= inputArr.length) {
            float sum = (float) findSum(inputArr, 0, inputArr.length)/(float)inputArr.length;
            return new float[] { sum };
        }

        // normal case K < inputArr.length
        float[] result = new float[inputArr.length - K + 1];
        int start = 0, end = 0, i = 0;
        long currSum = 0;

        while (start < inputArr.length && end < inputArr.length && start <= end) {
            if ((end - start + 1) < K) {
                currSum += inputArr[end];
                end++;
            }
            else if ((end - start + 1) >= K) {
                currSum += inputArr[end];
                end++;
                result[i] = (float) currSum / K;
                i++;
                currSum -= inputArr[start];
                start++;
            }
        }

        return result;
    }

    public static double[] findAverage2(int[] arr, int K) {
        double[] result = new double[arr.length - K + 1];
        double windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // add the next element
            // slide the window, we don't need to slide if we've not hit the required window size of 'k'
            if (windowEnd >= K - 1) {
                result[windowStart] = windowSum / K; // calculate the average
                windowSum -= arr[windowStart]; // subtract the element going out
                windowStart++; // slide the window ahead
            }
        }

        return result;
    }

    private static long findSum(int[] arr, int start, int end) {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }

        return sum;
    }

    public static void main(String args[]) {
        int[] inputArr1 = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        int K1 = 5;

        double[] ans1 = findAverage2(inputArr1, K1);
        for (double val : ans1) {
            System.out.print(val + ",");
        }
        System.out.println();

        float[] ans2 = findAverages1(inputArr1, K1);
        for (float val : ans2) {
            System.out.print(val + ",");
        }
        System.out.println();
    }
}
