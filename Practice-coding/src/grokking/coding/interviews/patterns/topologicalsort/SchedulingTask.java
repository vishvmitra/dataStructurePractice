package grokking.coding.interviews.patterns.topologicalsort;

import java.sql.Struct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be completed
 * before it can be scheduled. Given the number of tasks and a list of prerequisite pairs, find out if it is possible to
 * schedule all the tasks.
 *
 */
public class SchedulingTask {

    HashSet<Integer> set;
    public boolean isSchedulingPossible(int tasks, int[][] prerequisites) {
        if (tasks <= 0) {
            return false;
        }

        if (tasks == 1) {
            return true;
        }

        Stack<Integer> stack = new Stack();
        boolean[] visited = new boolean[tasks];
        boolean value = false;
        this.set = new HashSet();

        for (int i = 0; i < tasks; i++) {
            if (visited[i]) {
                continue;
            }

            value = dfs(i, prerequisites, visited, stack);
            if (!value) return false;
        }

        if (stack.size() != tasks) {
            return false;
        }

        return true;
    }

    private boolean dfs(int vertex, int[][] prerequisites, boolean[] visited, Stack<Integer> stack) {
        if (visited[vertex]) {
            return true;
        }

        if (set.contains(vertex)) {
            return false;
        }

        set.add(vertex);
        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i][0] == vertex) {
                for (int j = 0; j < prerequisites.length; j++) {
                    if (prerequisites[j][0] == prerequisites[i][1] && prerequisites[i][0] == prerequisites[j][1]) {
                        return false;
                    }
                }

                boolean value = dfs(prerequisites[i][1], prerequisites, visited, stack);
                if (!value) return false;
            }
        }

        visited[vertex] = true;
        stack.add(vertex);
        return true;
    }

    public static void main(String[] args) {
        SchedulingTask st = new SchedulingTask();
       boolean result = st.isSchedulingPossible(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println("Tasks execution possible: " + result);

        result = st.isSchedulingPossible(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println("Tasks execution possible: " + result);

        result = st.isSchedulingPossible(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 },
                new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println("Tasks execution possible: " + result);
    }
}
