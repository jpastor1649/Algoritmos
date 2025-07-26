import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountingRooms {
    static int n, m;
    static char[][] grid;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dims = br.readLine().trim().split("\\s+");
        n = Integer.parseInt(dims[0]);
        m = Integer.parseInt(dims[1]);

        grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[n][m];
        int roomCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '.') {
                    dfs(i, j);
                    roomCount++;
                }
            }
        }

        System.out.println(roomCount);
    }

    static void dfs(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m)
            return;
        if (visited[x][y] || grid[x][y] == '#')
            return;
        visited[x][y] = true;
        dfs(x + 1, y);
        dfs(x - 1, y);
        dfs(x, y + 1);
        dfs(x, y - 1);
    }
}
