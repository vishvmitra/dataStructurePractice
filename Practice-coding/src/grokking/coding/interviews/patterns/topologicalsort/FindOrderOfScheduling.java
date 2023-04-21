package grokking.coding.interviews.patterns.topologicalsort;

import java.util.*;

public class FindOrderOfScheduling {
    public static List<Integer> findOrder(int tasks, int[][] prerequisites) {
        if (tasks <= 0) {
            return new ArrayList<>(0);
        }

        if (tasks == 1) {
             new ArrayList<>() {
                 {
                     add(tasks);
                 }
             };
        }

        List<Integer> sortedOrder = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[tasks];
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < tasks; i++) {
            if (visited[i]) {
                continue;
            }

            boolean value = dfs(i, prerequisites, visited, stack, set);
            if (!value) return new ArrayList<>();
        }

        if (tasks != stack.size()) {
            return new ArrayList<>();
        }

        while (!stack.isEmpty()) {
            sortedOrder.add(stack.pop());
        }
        return sortedOrder;
    }

    private static boolean dfs(int vertex, int[][] prerequisites, boolean[] visited, Stack<Integer> stack, HashSet<Integer> set) {
        if (visited[vertex]) {
            return true;
        }

        if (set.contains(vertex)) {
            return false;
        }

        set.add(vertex);

        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i][0] == vertex) {
                boolean isNotCyclic = dfs(prerequisites[i][1], prerequisites, visited, stack, set);
                if(!isNotCyclic) return false;
            }
        }

        visited[vertex] = true;
        stack.add(vertex);
        return true;
    }

    public static void main(String[] args) {
        List<Integer> result = FindOrderOfScheduling.findOrder(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println(result);

        result = FindOrderOfScheduling.findOrder(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println(result);

        result = FindOrderOfScheduling.findOrder(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
                new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println(result);
    }
}