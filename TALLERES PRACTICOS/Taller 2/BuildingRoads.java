import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class BuildingRoads {
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        int[] compMin = new int[n + 1];
        int[] compMax = new int[n + 1];
        boolean[] exists = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            int r = find(i);
            if (!exists[r]) {
                compMin[r] = i;
                compMax[r] = i;
                exists[r] = true;
            } else {
                compMin[r] = Math.min(compMin[r], i);
                compMax[r] = Math.max(compMax[r], i);
            }
        }

        ArrayList<Integer> reps = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (exists[i])
                reps.add(i);
        }
        // Ordenar por compMin
        Collections.sort(reps, (a, b) -> Integer.compare(compMin[a], compMin[b]));

        int k = reps.size() - 1;
        System.out.println(k);
        for (int idx = 0; idx < k; idx++) {
            int r1 = reps.get(idx);
            int r2 = reps.get(idx + 1);
            System.out.println(compMax[r1] + " " + compMin[r2]);
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb)
            return;
        if (rank[ra] < rank[rb]) {
            parent[ra] = rb;
        } else if (rank[ra] > rank[rb]) {
            parent[rb] = ra;
        } else {
            parent[rb] = ra;
            rank[ra]++;
        }
    }
}
