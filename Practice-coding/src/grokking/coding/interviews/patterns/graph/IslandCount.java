package grokking.coding.interviews.patterns.graph;

/**
 * IsLand Count
 * Closed Island - visit all 0's at corner and do bfs. Then do a second iteration
 * with all the 0 and non-visited.
 */
public class IslandCount {

    // down, left, up, right
    private static int[] dirX = new int[] { 1, 0, -1, 0 };
    private static int[] dirY = new int[] { 0, -1, 0, 1};
    private static int[][] visited;

    public static int numIsland(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            visited = new int[grid.length][grid[0].length];
            int N = grid.length;
            int M = grid[0].length;

            int totalIslands = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] == 1 || grid[i][j] == 1) {
                        continue;
                    }
                    else {
                        bfsHelper(grid, visited, i, j);
                        totalIslands++;
                    }
                }
            }

            return totalIslands;
    }

    private static void bfsHelper(int[][] grid, int[][] visited, int row, int col) {
        visited[row][col] = 1;

        for (int i = 0; i < dirX.length; i++) {
            int newX = row + dirX[i];
            int newY = col + dirY[i];

            if (!isValid(grid, newX, newY)) {
                continue;
            }

            if (visited[newX][newY] == 1 || grid[newX][newY] == 1) {
                continue;
            }

            bfsHelper(grid, visited, newX, newY);
        }
    }

    private static boolean isValid(int[][] grid, int newX, int newY) {
        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] grid =
                {{1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1 },
                {1,0,1,1,1,0,1 },
                {1,0,1,0,1,0,1},
                {1,0,1,1,1,0,1},
                {1,0,0,0,0,0,1},
                {1,1,1,1,1,1,1}};

        System.out.println(numIsland(grid));
    }
}
