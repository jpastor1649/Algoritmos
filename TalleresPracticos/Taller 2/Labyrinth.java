import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Labyrinth {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dims = br.readLine().split("\\s+");
        int n = Integer.parseInt(dims[0]);
        int m = Integer.parseInt(dims[1]);

        char[][] grid = new char[n][m];
        int sx = -1, sy = -1, ex = -1, ey = -1;
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = row.charAt(j);
                if (grid[i][j] == 'A') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'B') {
                    ex = i;
                    ey = j;
                }
            }
        }

        int[][] dist = new int[n][m];
        int[][] prevX = new int[n][m];
        int[][] prevY = new int[n][m];
        char[][] prevDir = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = -1;
            }
        }

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        char[] dirChar = { 'U', 'D', 'L', 'R' };

        Deque<int[]> queue = new ArrayDeque<>();
        dist[sx][sy] = 0;
        queue.add(new int[] { sx, sy });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            if (x == ex && y == ey)
                break;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k], ny = y + dy[k];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m
                        && grid[nx][ny] != '#' && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    prevX[nx][ny] = x;
                    prevY[nx][ny] = y;
                    prevDir[nx][ny] = dirChar[k];
                    queue.add(new int[] { nx, ny });
                }
            }
        }

        if (dist[ex][ey] == -1) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            int length = dist[ex][ey];
            System.out.println(length);
            StringBuilder path = new StringBuilder();
            int cx = ex, cy = ey;
            while (cx != sx || cy != sy) {
                char c = prevDir[cx][cy];
                path.append(c);
                int px = prevX[cx][cy], py = prevY[cx][cy];
                cx = px;
                cy = py;
            }
            System.out.println(path.reverse());
        }
    }
}
