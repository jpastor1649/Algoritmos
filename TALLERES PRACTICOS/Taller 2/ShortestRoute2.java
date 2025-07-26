import java.io.*;

public class ShortestRoute2 {
    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();

        long[][] dist = new long[n][n];
        for (int i = 0; i < n; i++) {
            long[] di = dist[i];
            for (int j = 0; j < n; j++) {
                di[j] = (i == j ? 0 : INF);
            }
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            long c = in.nextLong();
            if (c < dist[a][b]) {
                dist[a][b] = dist[b][a] = c;
            }
        }

        for (int k = 0; k < n; k++) {
            long[] distK = dist[k];
            for (int i = 0; i < n; i++) {
                long dik = dist[i][k];
                if (dik == INF)
                    continue;
                long[] distI = dist[i];
                // j desde 0…n-1
                for (int j = 0; j < n; j++) {
                    long nd = dik + distK[j];
                    if (nd < distI[j]) {
                        distI[j] = nd;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder(q * 12);
        for (int i = 0; i < q; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            long ans = dist[a][b];
            sb.append(ans < INF ? ans : -1).append('\n');
        }
        System.out.print(sb);
    }

    static class FastReader {
        final int BS = 1 << 16;
        final byte[] buf = new byte[BS];
        int pos = 0, len = 0;
        InputStream in = System.in;

        int read() throws IOException {
            if (len == -1)
                return -1;
            if (pos >= len) {
                pos = 0;
                len = in.read(buf);
                if (len <= 0)
                    return -1;
            }
            return buf[pos++] & 0xFF;
        }

        int nextInt() throws IOException {
            int c, x = 0;
            do {
                c = read();
            } while (c <= ' ' && c != -1);
            boolean neg = c == '-';
            if (neg)
                c = read();
            for (; c >= '0' && c <= '9'; c = read()) {
                x = x * 10 + (c - '0');
            }
            return neg ? -x : x;
        }

        long nextLong() throws IOException {
            int c;
            long x = 0;
            do {
                c = read();
            } while (c <= ' ' && c != -1);
            boolean neg = c == '-';
            if (neg)
                c = read();
            for (; c >= '0' && c <= '9'; c = read()) {
                x = x * 10 + (c - '0');
            }
            return neg ? -x : x;
        }
    }
}
