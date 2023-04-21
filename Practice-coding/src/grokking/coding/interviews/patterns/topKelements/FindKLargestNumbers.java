package grokking.coding.interviews.patterns.topKelements;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
 * Input: [3, 1, 5, 12, 2, 11], K = 3
 * Output: [5, 12, 11]
 *
 * Tip: K largest => minHeap
 * K smallest => maxHeap
 *
 */

public class FindKLargestNumbers {

    public static List<Integer> findKLargestNumbers(int[] nums, int k) {

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Integer> result = FindKLargestNumbers.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);

        result = FindKLargestNumbers.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
    }
}
