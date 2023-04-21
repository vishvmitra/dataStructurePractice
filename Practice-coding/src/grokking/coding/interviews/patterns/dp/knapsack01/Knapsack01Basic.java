package grokking.coding.interviews.patterns.dp.knapsack01;

/**
 * Items: {1, 3, 4 , 5}
 * Value : {1, 4, 5, 7}
 * Capacity = 7
 *
 */
public class Knapsack01Basic {

    public int knapsack01(int[] wt, int[] values, int capacity) {
        // base case validation
        if (wt == null || values == null || wt.length == 0 || values.length == 0 || capacity == 0) {
            return 0;
        }

        int[][] cache = new int[values.length + 1][capacity + 1];
        return knapsack(wt, values, wt.length - 1, capacity, cache);
    }

    private int knapsack(int[] wt, int[] values, int index, int currCapacity, int[][] cache) {
        if (index == 0 || currCapacity == 0) {
            cache[index][currCapacity] = 0;
            return 0;
        }

        if (cache[index][currCapacity] != -1) {
            return cache[index][currCapacity];
        }

        if (wt[index - 1] > currCapacity) {
            cache[index][currCapacity] = knapsack(wt, values, index - 1, currCapacity, cache);
        }
        else {
            cache[index][currCapacity] = Math.max(knapsack(wt, values, index - 1, currCapacity, cache),
                                          values[index - 1] + knapsack(wt, values, index - 1, currCapacity - wt[index - 1], cache));
        }

        return cache[index][currCapacity];
    }

    // pure recursion
    private int recursion(int[] wt, int[] values, int index, int currCapacity) {
        // base case
        // smallest valid input
        if (index == 0 || currCapacity == 0) {
            return 0;
        }

        if (wt[index - 1] > currCapacity) {
            return recursion(wt, values, index -1, currCapacity);
        }
        else {
            return Math.max(recursion(wt, values, index -1, currCapacity),
                    values[index - 1] + recursion(wt, values, index - 1, currCapacity - wt[index - 1]));
        }
    }

    public static void main(String[] args) {

    }
}
