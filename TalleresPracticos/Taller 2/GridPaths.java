import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class GridPaths {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] g = new char[n][];
        for (int i = 0; i < n; i++) {
            g[i] = br.readLine().toCharArray();
        }
        int mod = 1000000007;
        long[] dp = new long[n];
        if (g[0][0] == '.')
            dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == '*') {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] = (dp[j] + dp[j - 1]) % mod;
                }
            }
        }
        System.out.print(dp[n - 1]);
    }
}