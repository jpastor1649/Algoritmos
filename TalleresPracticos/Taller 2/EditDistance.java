import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class EditDistance {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        int n = s.length(), m = t.length();
        if (n < m) {
            String tmp = s;
            s = t;
            t = tmp;
            int k = n;
            n = m;
            m = k;
        }
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];
        for (int j = 0; j <= m; j++)
            prev[j] = j;
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        for (int i = 1; i <= n; i++) {
            curr[0] = i;
            char ca = a[i - 1];
            for (int j = 1; j <= m; j++) {
                int cost = ca == b[j - 1] ? 0 : 1;
                int del = prev[j] + 1;
                int ins = curr[j - 1] + 1;
                int rep = prev[j - 1] + cost;
                curr[j] = del < ins ? (del < rep ? del : rep) : (ins < rep ? ins : rep);
            }
            int[] tmp = prev;
            prev = curr;
            curr = tmp;
        }
        System.out.println(prev[m]);
    }
}