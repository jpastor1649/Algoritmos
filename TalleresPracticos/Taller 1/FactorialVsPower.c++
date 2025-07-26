/*
    Author : RAJON BARDHAN
    AUST CSE 27th Batch
    All my programming success are dedicated to my mom , dad , little sister madhobi , teachers , friends and love TANIA SULTANA RIMY

    Problem Name : FACVSPOW - Factorial vs Power ( SPOJ )
    ALGORITHM : Binary Search
*/

#include <bits/stdc++.h>
using namespace std;

#define pb push_back
#define ff first
#define ss second
#define mp make_pair
#define memo(a, b) memset(a, b, sizeof(a))
#define INF 1e9
#define EPS 1e-8
#define PI 3.14159265358979323846

typedef long long ll;
typedef unsigned long long ull;

/* int dx[] = {1,-1,0,0} , dy[] = {0,0,1,-1}; */                       // 4 Direction
/* int dx[] = {1,-1,0,0,1,1,-1,-1} , dy[] = {0,0,1,-1,1,-1,1,-1}; */   // 8 Direction
/* int dx[] = {1,-1,1,-1,2,2,-2,-2} , dy[] = {2,2,-2,-2,1,-1,1,-1}; */ // Knight Direction
/* int dx[] = {2,-2,1,1,-1,-1} , dy[] = {0,0,1,-1,1,-1}; */            // Hexagonal Direction

double fact[10000000 + 10];

int main()
{
    // freopen("input.txt","r",stdin);
    // freopen("output.txt","w",stdout);
    fact[1] = log10(1.0);
    for (int i = 2; i <= 10000000; i++)
        fact[i] = fact[i - 1] + log10((double)i);

    int T;
    scanf("%d", &T);
    while (T--)
    {
        int a;
        scanf("%d", &a);
        int lo = 1, hi = 10000000, n = -1;

        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            double f = fact[mid];
            double g = (double)mid * log10(a);
            if (f > g)
            {
                n = mid;
                hi = mid - 1;
            }
            else
                lo = mid + 1;
        }

        printf("%d\n", n);
    }
    return 0;
}