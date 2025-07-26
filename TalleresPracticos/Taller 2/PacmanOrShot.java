import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayDeque;

public class PacmanOrShot {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.isEmpty()) {
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] grid = new char[R][C];
        int pr = -1, pc = -1;
        int gr = -1, gc = -1;

        // Read grid and locate Pacman (P) and Ghost (G)
        for (int r = 0; r < R; r++) {
            String row = br.readLine();
            for (int c = 0; c < C; c++) {
                char ch = row.charAt(c);
                grid[r][c] = ch;
                if (ch == 'P') {
                    pr = r;
                    pc = c;
                } else if (ch == 'G') {
                    gr = r;
                    gc = c;
                }
            }
        }

        String S = br.readLine().trim();

        final int N = R * C;
        final int INF = Integer.MAX_VALUE / 2;
        int[] dist = new int[N];
        Arrays.fill(dist, INF);

        int startIdx = gr * C + gc;
        dist[startIdx] = 0;

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(startIdx);

        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int r = cur / C;
            int c = cur % C;
            int d = dist[cur];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }
                if (grid[nr][nc] == '#') {
                    continue;
                }
                int idx = nr * C + nc;
                if (dist[idx] == INF) {
                    dist[idx] = d + 1;
                    queue.add(idx);
                }
            }
        }

        long t = 0;
        if (dist[pr * C + pc] <= t) {
            System.out.println("Yes");
            return;
        }

        for (char move : S.toCharArray()) {
            t++;
            int nr = pr, nc = pc;
            switch (move) {
                case 'U':
                    nr = (pr - 1 + R) % R;
                    break;
                case 'D':
                    nr = (pr + 1) % R;
                    break;
                case 'L':
                    nc = (pc - 1 + C) % C;
                    break;
                case 'R':
                    nc = (pc + 1) % C;
                    break;
            }
            if (grid[nr][nc] != '#') {
                pr = nr;
                pc = nc;
            }
            if (dist[pr * C + pc] <= t) {
                System.out.println("Yes");
                return;
            }
        }

        System.out.println("No");
    }
}