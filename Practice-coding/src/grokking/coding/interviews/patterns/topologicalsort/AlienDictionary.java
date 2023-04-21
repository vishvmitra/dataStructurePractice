package grokking.coding.interviews.patterns.topologicalsort;

import java.util.*;

/**
 * There is a dictionary containing words from an alien language for which we don’t know the ordering of the letters.
 * Write a method to find the correct order of the letters in the alien language.
 * It is given that the input is a valid dictionary and there exists an ordering among its letters.
 *
 * Input: Words: ["ba", "bc", "ac", "cab"]
 * Output: bac
 *
 */
public class AlienDictionary {
        public static String findOrder(String[] words) {
            Map<Character, List<Character>> graph = createGraph(words);
            int vertices = graph.keySet().size();
            boolean[] visited = new boolean[vertices];
            Set<Character> charSet = new HashSet<Character>();
            Stack<Character> stack = new Stack();

            for (char key : graph.keySet()) {
                if(visited[key - 'a']){
                    continue;
                }

                boolean value = dfs(key, graph, visited, stack, charSet);
                if (!value) return "";
            }

            if (graph.keySet().size() == stack.size()) {
                return "";
            }

            StringBuilder sb = new StringBuilder();

            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            return sb.toString();
        }

        private static boolean dfs(Character letter, Map<Character, List<Character>> graph, boolean[] visited, Stack<Character> stack, Set<Character> set) {
            if (visited[letter - 'a']) {
                return true;
            }

            if (set.contains(letter)) {
                return false;
            }

            set.add(letter);

            if (!graph.containsKey(letter)) {
                return true;
            }

            for (Character downstreamChar : graph.get(letter)) {
                boolean value = dfs(downstreamChar, graph, visited, stack, set);
                if (!value) return false;
            }

            visited[letter - 'a'] = true;
            stack.add(letter);
            return true;
        }

        private static Map<Character, List<Character>> createGraph(String[] words) {
            Map<Character, List<Character>> map = new HashMap();
            for (String word : words) {

                char[] charArray = word.toCharArray();
                for (int i = 1; i < charArray.length; i++) {
                    if (map.containsKey(charArray[i - 1])) {
                        List<Character> values = map.get(charArray[i - 1]);
                        values.add(charArray[i]);
                        map.put(charArray[i - 1], values);
                    }
                    else {
                        List<Character> values = new ArrayList<>();
                        values.add(charArray[i]);
                        map.put(charArray[i - 1], values);
                    }
                }
            }

            return map;
        }

        public static void main(String[] args) {
            String result = AlienDictionary.findOrder(new String[] { "ba", "bc", "ac", "cab" });
            System.out.println("Character order: " + result);

            result = AlienDictionary.findOrder(new String[] { "cab", "aaa", "aab" });
            System.out.println("Character order: " + result);

            result = AlienDictionary.findOrder(new String[] { "ywx", "wz", "xww", "xz", "zyy", "zwz" });
            System.out.println("Character order: " + result);
        }
}
