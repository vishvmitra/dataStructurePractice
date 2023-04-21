package grokking.coding.interviews.patterns.dp.knapsack01;

import java.util.ArrayList;

public class MinimumDifferenceSubsetSum {

    int[][] cache;
    public int minDiffSubsetSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int range = findSum(arr);
        cache = new int[arr.length + 1][range + 1];

        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                if (i == 0) {
                    cache[i][j] = 1;
                }
                else if (j == 0) {
                    cache[i][j] = 0;
                }
                else {
                    cache[i][j] = -1;
                }
            }
        }

        knapsack(arr, arr.length, range, cache);

        for (int i = range/2; i >= 0; i--) {
            if (cache[cache.length - 1][i] == 1) {
                return range/2 - i;
            }
        }

        return -1;
    }

    private int knapsack(int[] values, int index, int currCapacity, int[][] cache) {
        if (currCapacity == 0) {
            return 1;
        }

        if (index == 0 || index >= values.length + 1) {
            return 0;
        }

        if (cache[index][currCapacity] != -1) {
            return cache[index][currCapacity];
        }

        if (values[index - 1] > currCapacity) {
            cache[index][currCapacity] = knapsack(values, index - 1, currCapacity, cache);
        }
        else {
            cache[index][currCapacity] = (knapsack(values, index - 1, currCapacity, cache)
                    + knapsack(values, index - 1, currCapacity - values[index - 1], cache));
            cache[index][currCapacity] = cache[index][currCapacity] > 1 ? 1 : cache[index][currCapacity];
        }

        return cache[index][currCapacity];
    }

    private int findSum(int[] arr) {
        int sum = 0;

        for (int val : arr) {
            sum += val;
        }

        return sum;
    }

    public static void main(String[] args) {
        MinimumDifferenceSubsetSum ps = new MinimumDifferenceSubsetSum();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.minDiffSubsetSum(num));

        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.minDiffSubsetSum(num));

        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.minDiffSubsetSum(num));
    }
}
