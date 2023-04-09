package grokking.coding.interviews.patterns.twopointers;

/**
 * Given an array of sorted numbers and a target sum,
 * find a pair in the array whose sum is equal to the given target.
 */
public class PairWithTargetSum {

    public static int[] targetSum(int[] arr,  int k) {
        int start = 0, end = arr.length - 1;

        int[] result = new int[2];

        while (start <= end && start < arr.length && end >= 0) {
            int currSum = arr[start] + arr[end];
            if (currSum == k) {
                result[0] = start;
                result[1] = end;
                return result;
            }
            else if (currSum < k) {
                start++;
            }
            else {
                end--;
            }
        }

        return new int[] { -1, -1 }; // pair not found
    }

    public static void main(String[] args) {
        // Input: [1, 2, 3, 4, 6], target=6
        // Output: [1, 3]

        int[] inputArr = new int[] { 1, 2, 3, 4, 6 };
        int[] res = targetSum(inputArr, 6);

        for (int val : res) {
            System.out.print(val + ",");
        }

        System.out.println();

        int[] inputArr2 = new int[] { 2, 5, 9, 11 };
        int[] res2 = targetSum(inputArr2, 11);

        for (int val : res2) {
            System.out.print(val + ",");
        }
        System.out.println();

        int[] inputArr3 = new int[] { 1, 2, 5, 9, 11, 13 };
        int[] res3 = targetSum(inputArr3, 11);

        for (int val : res3) {
            System.out.print(val + ",");
        }
        System.out.println();
    }
}
