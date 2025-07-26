import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class HelgaHufflepuffsCup {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int numNodes = Integer.parseInt(st.nextToken());
        int numTipos = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[numNodes + 1];
        for (int i = 1; i <= numNodes; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < numNodes - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        int tipoMaxSeg, maxK;
        String line = br.readLine();
        while (line == null || line.trim().isEmpty()) {
            line = br.readLine();
        }
        st = new StringTokenizer(line);
        tipoMaxSeg = Integer.parseInt(st.nextToken());
        if (st.hasMoreTokens()) {
            maxK = Integer.parseInt(st.nextToken());
        } else {
            line = br.readLine();
            while (line == null || line.trim().isEmpty()) {
                line = br.readLine();
            }
            maxK = Integer.parseInt(new StringTokenizer(line).nextToken());
        }

        int[] parent = new int[numNodes + 1];
        List<Integer>[] children = new ArrayList[numNodes + 1];
        boolean[] visited = new boolean[numNodes + 1];
        for (int i = 1; i <= numNodes; i++) {
            children[i] = new ArrayList<>();
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        visited[1] = true;

        List<Integer> order = new ArrayList<>(numNodes);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            order.add(u);
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    children[u].add(v);
                    stack.push(v);
                }
            }
        }
        java.util.Collections.reverse(order);

        long[][][] dp = new long[numNodes + 1][3][maxK + 1];

        for (int u : order) {
            long[][] state = new long[3][maxK + 1];
            if (maxK >= 1) {
                state[0][1] = 1;
            }
            state[1][0] = ((tipoMaxSeg - 1) % MOD + MOD) % MOD;
            state[2][0] = ((numTipos - tipoMaxSeg) % MOD + MOD) % MOD;

            for (int v : children[u]) {
                long[] tot0 = new long[maxK + 1];
                long[] tot1 = new long[maxK + 1];
                long[] tot2 = new long[maxK + 1];
                for (int k = 0; k <= maxK; k++) {
                    tot0[k] = dp[v][1][k];
                    tot1[k] = (dp[v][0][k] + dp[v][1][k] + dp[v][2][k]) % MOD;
                    tot2[k] = (dp[v][1][k] + dp[v][2][k]) % MOD;
                }

                long[][] newState = new long[3][maxK + 1];
                for (int s = 0; s < 3; s++) {
                    long[] tot = (s == 0 ? tot0 : (s == 1 ? tot1 : tot2));
                    for (int usedU = 0; usedU <= maxK; usedU++) {
                        long waysU = state[s][usedU];
                        if (waysU == 0)
                            continue;
                        for (int usedV = 0; usedV + usedU <= maxK; usedV++) {
                            long waysV = tot[usedV];
                            if (waysV == 0)
                                continue;
                            newState[s][usedU + usedV] = (newState[s][usedU + usedV] + waysU * waysV) % MOD;
                        }
                    }
                }
                state = newState;
            }

            dp[u] = state;
        }

        long answer = 0;
        for (int s = 0; s < 3; s++) {
            for (int k = 0; k <= maxK; k++) {
                answer = (answer + dp[1][s][k]) % MOD;
            }
        }

        System.out.println(answer);
    }
}