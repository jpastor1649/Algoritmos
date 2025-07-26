
// author: jpastor
// Complexities:
// Time complexity: O(n log log n + t * n), t = cases
// Memory complexity: O(n* t)
import java.util.Arrays;
import java.util.Scanner;

public class FreqPrimeRanges {
    public static void main(String[] args) { // O(t * (n log log n + n)), M(n)
        int N = 100000;
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt(); // O(1), M(1)
        boolean[] primes = sieveErathostenes(N); // O(n log log n), M(n)
        for (int i = 0; i < t; i++) { // O(t)
            long count = 0;
            int n = scan.nextInt(); // O(1), M(1)
            int k = scan.nextInt(); // O(1), M(1)
            if (k == 0) { // O(1)
                count = (long) (n - 1) * n / 2; // O(1)
            } else {
                count = count_range(n, k, primes); // O(n)
            }
            System.out.println(count); // O(1)
        }
        scan.close();
    }

    public static boolean[] sieveErathostenes(int n) { // O(n log log n), M(n)
        boolean[] primes = new boolean[n + 1]; // O(n), M(n)
        Arrays.fill(primes, true); // O(n)
        primes[0] = false; // O(1)
        primes[1] = false; // O(1)

        for (int i = 2; i < Math.sqrt(n) + 1; i++) { // O(sqrt(n))
            if (primes[i]) { // O(1)
                for (int j = i * i; j < n + 1; j += i) { // O(n log log n)
                    primes[j] = false; // O(1)
                }
            }
        }
        return primes;
    }

    public static long count_range(int n, int k, boolean[] primes) { // O(n), M(1)
        long total = 0; // O(1), M(1)
        long count = 0; // O(1), M(1)
        int pt1 = 1; // O(1), M(1)

        for (int pt2 = 2; pt2 < n + 1; pt2++) { // O(n)
            while (pt1 < n && count < k) { // O(n)
                pt1 += 1; // O(1)
                if (primes[pt1]) {
                    count += 1; // O(1)
                }
            }

            if (count >= k) { // O(1)
                total += (n - pt1 + 1); // O(1)
            }

            if (primes[pt2]) {
                count -= 1; // O(1)
            }
        }
        return total; // O(1)
    }
}
