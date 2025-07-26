import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class CycleFinding {
    static class Edge {
        int u, v;
        long w;

        Edge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            edges.add(new Edge(u, v, w));
        }

        long[] dist = new long[n + 1];
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = 0L;
            parent[i] = -1;
        }

        int x = -1;
        for (int i = 1; i <= n; i++) {
            x = -1;
            for (Edge e : edges) {
                int u = e.u, v = e.v;
                long w = e.w;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    x = v;
                }
            }
            if (x == -1) {
                break;
            }
        }

        if (x == -1) {
            System.out.println("NO");
            return;
        }

        for (int i = 0; i < n; i++) {
            x = parent[x];
        }

        List<Integer> cycle = new ArrayList<>();
        int v = x;
        do {
            cycle.add(v);
            v = parent[v];
        } while (v != x);
        cycle.add(x);

        StringBuilder sb = new StringBuilder();
        sb.append("YES\n");
        for (int i = cycle.size() - 1; i >= 0; i--) {
            sb.append(cycle.get(i)).append(' ');
        }
        System.out.println(sb.toString().trim());
    }
}