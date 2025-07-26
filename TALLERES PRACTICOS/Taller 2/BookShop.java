import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BookShop {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] h = new int[n];
        int[] s = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[x + 1];

        // 0/1 knapsack: iterate books, update dp from high to low
        for (int i = 0; i < n; i++) {
            int price = h[i], pages = s[i];
            for (int j = x; j >= price; j--) {
                int candidate = dp[j - price] + pages;
                if (candidate > dp[j]) {
                    dp[j] = candidate;
                }
            }
        }

        // Find the best pages for cost ≤ x
        int answer = 0;
        for (int j = 0; j <= x; j++) {
            if (dp[j] > answer) {
                answer = dp[j];
            }
        }

        System.out.println(answer);
    }
}