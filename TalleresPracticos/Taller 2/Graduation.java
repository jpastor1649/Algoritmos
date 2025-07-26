import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Graduation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] a = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] inDeg = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int dep = a[i];
            if (dep != 0) {
                inDeg[dep]++;
            }
        }

        int[] height = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (height[i] != 0)
                continue;
            ArrayList<Integer> chain = new ArrayList<>();
            int cur = i;
            while (height[cur] == 0) {
                chain.add(cur);
                if (a[cur] == 0 || height[a[cur]] != 0) {
                    break;
                }
                cur = a[cur];
            }
            int last = chain.get(chain.size() - 1);
            int baseH;
            if (a[last] == 0) {
                baseH = 1;
            } else {
                baseH = height[a[last]] + 1;
            }
            height[last] = baseH;
            for (int idx = chain.size() - 2; idx >= 0; idx--) {
                int node = chain.get(idx);
                height[node] = height[chain.get(idx + 1)] + 1;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (u, v) -> Integer.compare(height[v], height[u]));

        for (int i = 1; i <= n; i++) {
            if (inDeg[i] == 0) {
                pq.offer(i);
            }
        }

        int semesters = 0;
        int taken = 0;
        while (taken < n) {
            if (pq.isEmpty()) {
                break;
            }
            semesters++;
            int canTake = Math.min(k, pq.size());
            ArrayList<Integer> batch = new ArrayList<>(canTake);
            for (int j = 0; j < canTake; j++) {
                batch.add(pq.poll());
            }
            taken += batch.size();
            for (int course : batch) {
                int dep = a[course];
                if (dep != 0) {
                    inDeg[dep]--;
                    if (inDeg[dep] == 0) {
                        pq.offer(dep);
                    }
                }
            }
        }

        System.out.println(semesters);
    }
}