package Avaliacao2_PAA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ReservandoHotel_1640 { //uri 1640 - Busca de menor Caminho (Algoritmo de dijkstra)

    static final int INF = (int) 1e9;//modulo de 10 iniciado na variavel inteira estatica INF

    //algoritmo de dijkstra como método estatico
    static int dijkstra(ArrayList<Aresta>[] listaAdjacente, boolean[] hotel, int V, int T) {

        //utilização de uma matriz de adjacencia
        int[][] distancia = new int[601][V];//iniciação da matriz com 601 valores e juntamente com o vertice

        for (int i = 0; i <= 600; ++i)
            Arrays.fill(distancia[i], INF);

        PriorityQueue<Estado> pq = new PriorityQueue<Estado>();
        distancia[600][0] = 0;
        pq.add(new Estado(0, 600, 0));

        while (!pq.isEmpty()) {
            Estado cur = pq.remove();
            if (cur.cidade == T)
                return cur.custo;
            if (cur.custo > distancia[cur.resistencia][cur.cidade])
                continue;
            //Vai para outra cidade
            for (Aresta nxt : listaAdjacente[cur.cidade]) {
                int nxtStamina = cur.resistencia - nxt.tempo;
                if (nxtStamina >= 0 && cur.custo < distancia[nxtStamina][nxt.cidade])
                    pq.add(new Estado(nxt.cidade, nxtStamina, distancia[nxtStamina][nxt.cidade] = cur.custo));
            }

            //Fique aqui
            if (hotel[cur.cidade] && cur.custo + 1 < distancia[600][cur.cidade])
                pq.add(new Estado(cur.cidade, 600, distancia[600][cur.cidade] = cur.custo + 1));

        }
        return -1;
    }

    //metodo main contendo um while para executar um loop e  fazer a resolução a partir da lista adjacente
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            int C = sc.nextInt();
            if (C == 0)
                break;
            int H = sc.nextInt();
            boolean[] hotel = new boolean[C];
            while (H-- > 0)
                hotel[sc.nextInt() - 1] = true;
            ArrayList<Aresta>[] listaAdjacente = new ArrayList[C];
            for (int i = 0; i < C; ++i)
                listaAdjacente[i] = new ArrayList<Aresta>();
            int M = sc.nextInt();
            while (M-- > 0) {
                int u = sc.nextInt() - 1, v = sc.nextInt() - 1, t = sc.nextInt();
                listaAdjacente[u].add(new Aresta(v, t));
                listaAdjacente[v].add(new Aresta(u, t));
            }

            out.println(dijkstra(listaAdjacente, hotel, C, C - 1));
        }
        out.flush();
        out.close();
    }

    static class Estado implements Comparable<Estado> {
        int cidade, resistencia, custo;

        Estado(int x, int y, int z) {
            cidade = x;
            resistencia = y;
            custo = z;
        }

        public int compareTo(Estado s) {
            return custo - s.custo;
        }
    }

    static class Aresta {
        int cidade, tempo;

        Aresta(int x, int y) {
            cidade = x;
            tempo = y;
        }
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String proximo() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(proximo());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(proximo());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = proximo();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }


    }
}
