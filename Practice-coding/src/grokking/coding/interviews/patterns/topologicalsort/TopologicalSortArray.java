package grokking.coding.interviews.patterns.topologicalsort;

import java.util.*;
/**
 * Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering of its vertices such that for every directed edge (U, V) from vertex U to vertex V,
 * U comes before V in the ordering.
 * need not be unique result
 *
 * 1. Select an unvisited vertex.
 * 2. Visit all its downstream vertices
 * 3. then mark this as visited and push into a stack
 * 4. Iterate over other unvisited vertices (repeat from 1-3)
 * 5. Pop all vertices from stack and push into an array
 */

public class TopologicalSortArray {

        public static List<Integer> sort(int vertices, int[][] edges) {
            if (vertices <= 0) {
                return new ArrayList<>(0);
            }

            List<Integer> sortedOrder = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();

            boolean[] visited = new boolean[vertices];
            for (boolean val : visited) {
                val = false;
            }

            for (int i = 0; i < vertices; i++) {
                if (visited[i]) {
                    continue;
                }

                stack = dfs(i, edges, visited, stack);
            }

            while(!stack.isEmpty()) {
                sortedOrder.add(stack.pop());
            }

            return sortedOrder;
        }

        private static Stack<Integer> dfs(int vertex, int[][] edges, boolean[] visited, Stack<Integer> stack) {
             if (visited[vertex]) {
                 return stack;
             }

             for (int i = 0; i < edges.length; i++) {
                 if (edges[i][0] == vertex) {
                     dfs(edges[i][1], edges, visited, stack);
                 }
             }

             visited[vertex] = true;
             stack.add(vertex);
             return stack;
        }

        public static void main(String[] args) {
            List<Integer> result = sort(4,
                    new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
            System.out.println(result);

           result = sort(5, new int[][] { new int[] { 4, 2 }, new int[] { 4, 3 }, new int[] { 2, 0 },
                    new int[] { 2, 1 }, new int[] { 3, 1 } });
            System.out.println(result);

            result = sort(7, new int[][] { new int[] { 6, 4 }, new int[] { 6, 2 }, new int[] { 5, 3 },
                    new int[] { 5, 4 }, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
            System.out.println(result);
        }
}
