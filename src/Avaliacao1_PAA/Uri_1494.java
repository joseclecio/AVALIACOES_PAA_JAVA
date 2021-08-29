package Avaliacao1_PAA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Uri_1494 {
    static final int MODULO = (int) 1e9 + 7; //módulo de 10+7
    static int[] representanteNumeral; //vetor para representar um numero
    static long[][] combina; //matriz para comparação
    static long[][][] memoria; //memoria onde será armazenada os numeros após a comparação
    static int N, maxPos, maxNeg; //declaração de variaveis do tipo inteiro


    //nesse metodo irei obter os digitos positivos como uma sequencia de base 10 sem nenhum tipo de zero a esquerda
    //a declaração de atributos serão o id; o resto sento positivo; o resto sendo negativo e o modulo do numero a ser calculado
    static long digitosPositivos(int idx, int restoPositivo, int restoNegativo, int modulo) {

        //no if será comparado de o idx será igual a sequencia de base 10, caso for igual irá retornar o modulo
        //se o valor for igual a 0, subtraia 1, caso contrário, subtraia 0, logo retornará a memoria
        if (idx == 10)
            return modulo == 0 ? 1 : 0;

        //nesse if caso a matriz da mamoria foir diferente de -1, irá retornar a memorria e por fim sera criada uma
        //variavel count iniciada com zero
        if (memoria[idx][restoPositivo][modulo] != -1)
            return memoria[idx][restoPositivo][modulo];
        long count = 0;

        //o for abaixo irá da posição k até o vetor representanteNumeral contendo o idx
        for (int k = 0; k <= representanteNumeral[idx]; ++k)

            //o if abaixo demonstra se o k for menor ou igual ao resto positivo e o representanteNumeral contendo o idx
            // menos k for menor ou igual ao resto negativo, irá colocar o representanteNumeral contendo o idx
            // menos k na variavel inteira nk
            if (k <= restoPositivo && representanteNumeral[idx] - k <= restoNegativo) {
                int nk = representanteNumeral[idx] - k;

                //se o idx for igual a zero então faz o calculo com o modulo representando o número de onze anagramas múltiplos de N
                if (idx == 0)
                    count = (count + (restoPositivo == 0 ? 0 : (combina[restoPositivo - 1][k] *
                            combina[restoNegativo][nk] % MODULO * digitosPositivos(idx + 1,
                            restoPositivo - k, restoNegativo - nk, conserto(modulo)) % MODULO))) % MODULO;
                else
                    count = (count + combina[restoPositivo][k] * combina[restoNegativo][nk] % MODULO *
                            digitosPositivos(idx + 1, restoPositivo - k, restoNegativo - nk,
                                    conserto(modulo + (k - nk) * idx)) % MODULO) % MODULO;
            }

        //por fim irá retornar a memoria retornando o calculo contido na variavel count
        return memoria[idx][restoPositivo][modulo] = count;

    }

    //esse metodo tem por objetivo verificar se o numero é multiplo de 11 retornando o resto.
    static int conserto(int x) {
        while (x < 0)
            x += 11;
        return x % 11;
    }

    //nesse metodo terá a tentativa gulosa, onde toma decisões com base nas informações disponíveis na iteração corrente
    //primeiro será instanciada uma nova matriz de ocmbinação contendo 101 em cada, logo depois a matriz combina terá o número 1
    //em suas posições da matriz
    static void calculo() {
        combina = new long[101][101];
        combina[0][0] = 1;

        //nesse for tera um metodo reverso para a combinação ser exata, ou seja, primeiro tem que ser de 1 até 100
        //onde a matriz combina pegará o numero 1 e acresentar no segundo item da matriz e no primeiro terá a posição i
        //logo em seguida terá o outro for que vai da posição 1 até a posição i, fazendo com que tenha um metodo reverso
        //por fim será combinado os valores das posições de i e de j na matriz combina, tendo o calculo da mesma.
        for (int i = 1; i <= 100; ++i) {
            combina[i][0] = 1;
            for (int j = 1; j <= i; ++j)
                combina[i][j] = (combina[i - 1][j - 1] + combina[i - 1][j]) % MODULO;
        }

    }

    //metodo mais para a execução do programa
    public static void main(String[] args) throws IOException {

        calculo(); //chamada do metodo calculo
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        //o while irá fazer um loop a partir do tipo de objeto Scanner a ser digitado pelo usuario
        while (sc.ready()) {
            String line = sc.next();
            N = line.length();
            representanteNumeral = new int[10];
            for (int i = 0; i < N; ++i)
                representanteNumeral[line.charAt(i) - '0']++;
            maxPos = (N + 1) >> 1; //aqui tem uma deslocação de todos os bits para a direita positiva
            maxNeg = N >> 1; //aqui tem uma deslocação de todos os bits para a direita negativa
            memoria = new long[10][maxPos + 1][11]; //a memoria armazena todos os dados  da posição maxima
            for (int i = 0; i < 10; ++i)
                for (int j = 0; j < maxPos + 1; ++j)
                    Arrays.fill(memoria[i][j], -1);

            out.println(digitosPositivos(0, maxPos, maxNeg, 0));
        }
        out.flush(); //se verdadeiro, o buffer de saída será esvaziado
        out.close(); //fecha oa while
    }

    //classe Scanner para a captura de dados digitadas pelo usuario apartir de um bloco inicializador estático
    static class Scanner {
        StringTokenizer st; //permite que divida uma string em tokens onde irá construir um tokenizer de
        // string para a declaração especificada.
        BufferedReader br; //armazenará em buffer a entrada da declaração especificada

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        //nesse metodo o StringTokenizer tem que serigual a null ou se hasMoreTokens não tiver tokens
        // disponíveis na string deste tokenizer
        //StringTokenizer recebe uam instancia de tokens disponíveis na string
        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
