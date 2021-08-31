package Avaliacao2_PAA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class EstradasEscuras_1152 { // uri 1152 - Árvore geradora mínima (Algoritmo de Kruskal)

    static int n, m;
    static ArrayList<Aresta> listaDeArestas;

    //aqui o algoritmo de kruskal foi instanciado com outras classes que esstão definicdas no código como a de AcharUniao
    //que é onde irá buscar uma união entre as variáveis Do e para, a fim de definir um único valor
    static long kruskal() {

        long ans = 0;
        Collections.sort(listaDeArestas);//operar para retornar a coleção
        AcharUniao acharUniao = new AcharUniao(n);//instancia da busca da união dos valores

        int ind = 0;
        while (acharUniao.conjuntoDeNumeros > 1 && ind < listaDeArestas.size()) {
            Aresta cur = listaDeArestas.get(ind++);
            int Do = cur.Do, para = cur.para;
            if (!acharUniao.isSameSet(Do, para)) {
                acharUniao.uniao(Do, para);
                ans += cur.custo;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0)
                break;
            listaDeArestas = new ArrayList<>(m);
            long total = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                total += c;
                listaDeArestas.add(new Aresta(a, b, c));
                listaDeArestas.add(new Aresta(b, a, c));
            }
            sb.append(total - kruskal()).append("\n");
        }
        System.out.print(sb);
    }

    //a lista de arestas foi usada para representar um gráfico como uma lista de suas arestas.
    static class Aresta implements Comparable<Aresta> {
        int Do, para, custo;

        public Aresta(int f, int t, int c) {
            Do = f;
            para = t;
            custo = c;
        }

        public int compareTo(Aresta o) {
            return custo - o.custo;
        }
    }


    static class AcharUniao {
        int[] definir, classificar;
        int conjuntoDeNumeros;

        public AcharUniao(int n) {
            conjuntoDeNumeros = n;
            definir = new int[n];
            classificar = new int[n];
            for (int i = 0; i < classificar.length; i++) {
                definir[i] = i;
                classificar[i] = 1;
            }
        }

        public int achar(int x) {
            return (definir[x] == x) ? x : (definir[x] = achar(definir[x]));
        }

        public void uniao(int a, int b) {
            int x = achar(a), y = achar(b);
            if (isSameSet(a, b))
                return;
            if (classificar[x] > classificar[y])
                definir[y] = x;
            else {
                definir[x] = y;
                if (classificar[x] == classificar[y])
                    classificar[y]++;
            }
            conjuntoDeNumeros--;
        }

        public boolean isSameSet(int a, int b) {
            return achar(a) == achar(b);
        }
    }
}
