import java.io.*;
import java.util.*;

public class ShortestRoute1 {
    static final long INF = Long.MAX_VALUE / 3;

    static int[] head;
    static int[] to;
    static long[] w;
    static int[] nxt;
    static int edgeCnt = 0;

    static int[] heapNode;
    static long[] heapDist;
    static int heapSize = 0;

    static void addEdge(int u, int v, long wt) {
        to[edgeCnt] = v;
        w[edgeCnt] = wt;
        nxt[edgeCnt] = head[u];
        head[u] = edgeCnt++;
    }

    static void heapPush(int node, long dist) {
        heapSize++;
        heapNode[heapSize] = node;
        heapDist[heapSize] = dist;
        int i = heapSize;
        while (i > 1) {
            int p = i >> 1;
            if (heapDist[p] <= heapDist[i])
                break;
            // swap
            long td = heapDist[p];
            heapDist[p] = heapDist[i];
            heapDist[i] = td;
            int tn = heapNode[p];
            heapNode[p] = heapNode[i];
            heapNode[i] = tn;
            i = p;
        }
    }

    static int heapPollNode() {
        int ret = heapNode[1];
        heapNode[1] = heapNode[heapSize];
        heapDist[1] = heapDist[heapSize];
        heapSize--;
        int i = 1;
        while (true) {
            int l = i << 1;
            int r = l | 1;
            if (l > heapSize)
                break;
            int smallest = (r <= heapSize && heapDist[r] < heapDist[l]) ? r : l;
            if (heapDist[i] <= heapDist[smallest])
                break;
            // swap
            long td = heapDist[i];
            heapDist[i] = heapDist[smallest];
            heapDist[smallest] = td;
            int tn = heapNode[i];
            heapNode[i] = heapNode[smallest];
            heapNode[smallest] = tn;
            i = smallest;
        }
        return ret;
    }

    static long heapPollDist() {
        return heapDist[1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        head = new int[n + 1];
        to = new int[m];
        w = new long[m];
        nxt = new int[m];
        Arrays.fill(head, -1);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            addEdge(a, b, c);
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        heapNode = new int[m + 5];
        heapDist = new long[m + 5];
        heapSize = 0;

        heapPush(1, 0);

        while (heapSize > 0) {
            long d = heapPollDist();
            int u = heapPollNode();
            if (d != dist[u])
                continue;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                long nd = d + w[e];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    heapPush(v, nd);
                }
            }
        }

        StringBuilder sb = new StringBuilder(n * 12);
        for (int i = 1; i <= n; i++) {
            sb.append(dist[i]).append(i == n ? '\n' : ' ');
        }
        System.out.print(sb);
    }
}
