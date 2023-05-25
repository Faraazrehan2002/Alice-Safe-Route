import java.util.*;

public class ForestNavigation {
    public static void main(String[] args) {
        int[][] forest = {
            {0, 0},
            {0, 0},
        };
        int N = 2;
        int M = 2;
        
        int result = safeRoute(forest, N, M);
    }

    public static int safeRoute(int[][] matrix, int N, int M) {
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        int[][] visited = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0 });
        visited[0][0] = 1;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll(); //deque the head
                int x = curr[0];
                int y = curr[1];

                if (x == N - 1 && y == M - 1) {
                    // Reached the destination
                    System.out.println(1);
                    printPath(visited, N, M);
                    return 1; // Safe route exists
                }

                for (int[] dir : directions) {
                    int newRow = x + dir[0];
                    int newCol = y + dir[1];

                    if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < M && visited[newRow][newCol] == 0
                            && matrix[newRow][newCol] == 0) {
                        visited[newRow][newCol] = steps;
                        queue.add(new int[] { newRow, newCol });
                    }
                }
            }
        }

        // No safe route found
        System.out.println(0);
        printPath(visited, N, M);
        return 0;
    }

    public static void printPath(int[][] visited, int N, int M) {
        int x = N - 1;
        int y = M - 1;
        int steps = visited[x][y];
        int stepcount = 0;
        List<String> path = new ArrayList<>();

        while (steps > 0) {
            path.add(0, x + "," + y);
            steps--;

            if (x > 0 && visited[x - 1][y] == steps) {
                x--;
            } else if (x < N - 1 && visited[x + 1][y] == steps) {
                x++;
            } else if (y > 0 && visited[x][y - 1] == steps) {
                y--;
            } else if (y < M - 1 && visited[x][y + 1] == steps) {
                y++;
            }
        }

        path.add(0, "0,0");
        for (String step : path) {
            System.out.println(step);
            stepcount++;
        }
        System.out.println(stepcount);
    }
}
