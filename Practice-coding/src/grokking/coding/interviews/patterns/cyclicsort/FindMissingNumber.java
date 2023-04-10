package grokking.coding.interviews.patterns.cyclicsort;

import java.util.Arrays;

/**
 * This pattern describes an interesting approach to deal with problems involving arrays containing numbers in a given range.
 * For example, take the following problem:
 *
 * You are given an unsorted array containing n numbers taken from the range 1 to n. The array can have duplicates,
 * which means that some numbers will be missing. Find all the missing numbers.
 *
 * To efficiently solve this problem, we can use the fact that the input array contains numbers in the range of 1 to n.
 * For example, to efficiently sort the array, we can try placing each number at its correct place,
 * i.e., placing 1 at index '0', placing 2 at index ‘1’, and so on. Once we are done with the sorting, we can iterate
 * the array to find all indices missing the correct numbers. These will be our required numbers.
 */
public class FindMissingNumber {

    public static int[] sortedArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == (i + 1)) {
                continue;
            }

            int temp = arr[arr[i] - 1];
            arr[arr[i] - 1] = arr[i];
            arr[i] = temp;
        }

        return arr;

    }

    public static void main(String[] args) {
        int[] arr = new int[] { 3, 1, 5, 4, 2 };
        arr = sortedArray(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        arr = sortedArray(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[] { 1, 5, 6, 4, 3, 2 };
        arr = sortedArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}
