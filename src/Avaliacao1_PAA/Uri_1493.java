package Avaliacao1_PAA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Uri_1493 {
    static ArrayList<Integer>[] parentes;

    //1. DP Solution
    static int[][] memoria;

    static int dp(int i, int j) {
        if (i == j)
            return 0;
        if (j == 1)
            return 1;
        if (memoria[i][j] != -1)
            return memoria[i][j];
        int ans = 0;
        for (int k = parentes[j].size() - 1; k >= 0; --k)
            if (dp(Math.min(i, parentes[j].get(k)), Math.max(i, parentes[j].get(k))) == 1) {
                ans = 1;
                break;
            }
        return memoria[i][j] = ans;
    }

    static int goDp(int N) {
        memoria = new int[N + 1][N + 1];
        for (int i = 1; i <= N; ++i)
            Arrays.fill(memoria[i], -1);
        int ans = N - 1;
        for (int i = 2; i < N; ++i)
            for (int j = i + 1; j <= N; ++j)
                ans += dp(i, j);
        return ans;
    }

    //2. Solução gananciosa // método guloso
    static int[] parent;

    static int encontrarParente(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = encontrarParente(parent[x]);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (sc.ready()) {
            int N = sc.nextInt(), E = sc.nextInt();
            parent = new int[N + 1];
            parentes = new ArrayList[N + 1];
            for (int i = 2; i <= N; ++i)
                parentes[i] = new ArrayList<Integer>();
            while (E-- > 0) {
                int u = sc.nextInt(), v = sc.nextInt();
                if (u == 1)
                    parent[v] = v;
                else
                    parentes[v].add(u);        //adicione todos os pais na solução dp
            }
            for (int i = 2; i <= N; ++i)
                for (int j = 0; j < parentes[i].size(); ++j) {
                    int u = parentes[i].get(j);
                    if (parent[i] == 0)
                        parent[i] = u;
                    else if (encontrarParente(i) != encontrarParente(u))
                        parent[i] = i;
                }
            int[] depSet = new int[N + 1];
            for (int i = 2; i <= N; ++i)
                depSet[encontrarParente(i)]++;

//			int ans = goDp(N);
            int ans = 0;
            for (int i = 2; i <= N; ++i)
                ans += depSet[i] * (N - 1 - depSet[i]);
            ans = (ans >> 1) + N - 1;

            out.println(ans);
        }
        out.flush();
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public boolean ready() throws IOException {
            return br.ready();
        }


    }
}
