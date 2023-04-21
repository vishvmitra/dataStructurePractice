package grokking.coding.interviews.patterns.dp.knapsack01;

/**
 * arr[] = { 2, 3, 7, 8, 10}
 * sum = 11
 *
 */
public class SubsetSum {

    public boolean isSubsetPresentWithGivenSum(int[] arr, int sum) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int[][] cache = new int[arr.length + 1][sum + 1];

        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                if (i == 0) {
                    cache[i][j] = 1;
                }
                else if (j == 0) {
                    cache[i][j] = 0;
                }

                cache[i][j] = -1;
            }
        }

        int val = knapsack(arr, arr.length, sum, cache);
        return val == 1 ? true : false;
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

    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        int arr[] = new int[] { 2, 3, 7, 8, 10};
        int targetSum = 11;
        System.out.println(ss.isSubsetPresentWithGivenSum(arr, targetSum));

        arr = new int[]  {1, 2, 3, 4};
        targetSum = 11;
        System.out.println(ss.isSubsetPresentWithGivenSum(arr, targetSum));

        arr = new int[]{1, 1, 3, 4, 7};
        targetSum = 8;
        System.out.println(ss.isSubsetPresentWithGivenSum(arr, targetSum));

        arr = new int[] {2, 3, 4, 6};
        targetSum = 11;
        System.out.println(ss.isSubsetPresentWithGivenSum(arr, targetSum));
    }
}
