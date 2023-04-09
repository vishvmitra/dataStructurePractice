package grokking.coding.interviews.patterns.twopointers;

public class RemoveDuplicates {

    public static int[] removeDuplicates(int[] arr,  int k) {
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

        int[] inputArr = new int[]{1, 2, 3, 4, 6};
    }
}
