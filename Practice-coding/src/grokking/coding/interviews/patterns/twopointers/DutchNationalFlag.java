package grokking.coding.interviews.patterns.twopointers;

import java.util.Arrays;

public class DutchNationalFlag {

    public static int[] sortArray(int[] arr) {

        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        if (arr.length <= 1) {
            return arr;
        }

        int low = 0, high = arr.length - 1;

        for (int i = 0; i <= high; ) {
            if (arr[i] == 1) {
                i++;
            }
            else if (arr[i] == 0) {
                arr = swap(arr, i , low);
                low++;
                i++;
            }
            else {
                arr = swap(arr, i, high);
                high--;
            }
        }

        return arr;
    }

    private static int[] swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    public static void main(String[] args) {
        // Input: [1, 2, 3, 4, 6], target=6
        // Output: [1, 3]

        int[] arr = new int[] { 1, 0, 2, 1, 0 };
        arr = sortArray(arr);
        System.out.print(Arrays.toString(arr));

        System.out.println();

        int[] arr2 = new int[] { 2, 2, 0, 1, 2, 0 };
        arr = sortArray(arr2);
        System.out.print(Arrays.toString(arr2));
    }
}
