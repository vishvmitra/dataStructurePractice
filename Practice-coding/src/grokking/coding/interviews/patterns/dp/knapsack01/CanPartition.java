package grokking.coding.interviews.patterns.dp.knapsack01;

public class CanPartition {

    public static boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        double targetSum = (double)findSum(nums) / 2.0;

        if (targetSum == 0) {
            return true;
        }

        if (targetSum - (int)targetSum != 0) {
            return false;
        }

        int targetSumInt = (int)targetSum;
        int[][] cache = new int[nums.length + 1][targetSumInt + 1];

        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                if (i == 0) {
                    cache[i][j] = 0;
                }
                else if (j == 0) {
                    cache[i][j] = 1;
                }
                else {
                    cache[i][j] = -1;
                }
            }
        }

        int val = knapsack(nums, nums.length, targetSumInt, cache);
        return val == 1 ? true : false;
    }

    private static int knapsack(int[] nums, int index, int capacity, int[][] cache) {
        if (capacity == 0) {
            cache[index][capacity] = 1;
            return 1;
        }


        if(index == 0 || capacity == 0) {
            cache[index][capacity] = 0;
            return 0;
        }

        if (cache[index][capacity] != -1) {
                return cache[index][capacity];
        }

        if (nums[index - 1] > capacity) {
                cache[index][capacity] = knapsack(nums, index - 1, capacity, cache);
        }
        else {
                cache[index][capacity] = knapsack(nums, index - 1, capacity - nums[index - 1], cache)
                        + knapsack(nums, index - 1, capacity, cache);
                cache[index][capacity] = cache[index][capacity] > 1 ? 1 : cache[index][capacity];
        }

        return cache[index][capacity];
    }

    private static int findSum(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        return sum;
    }

    public static void main(String[] args) {
        CanPartition ps = new CanPartition();
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartition(num));

        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num));

        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num));
    }
}
