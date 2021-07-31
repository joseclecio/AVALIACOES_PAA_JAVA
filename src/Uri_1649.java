import java.util.*;


public class Uri_1649 {

    //metodo main para execução do programa
    public static void main(String[] args) {

        //tratamento de exceção
        try {
            Scanner sc = new Scanner(System.in);
            int n, m, linhas, colunas, i, resposta; //declaração de mariaveis do tipo inteiro onde n é a enésima linha
            String[] quadro;

            //no while será começado com um loop onde será capturado pelo objeto Scanner o que o usuario irá digitar
            while (sc.hasNext()) {
                n = sc.nextInt();
                m = sc.nextInt();
                linhas = sc.nextInt();
                colunas = sc.nextInt();

                // no if são somados as linhas com as colunas e mais dois inteiros que o usuario irá digitar, sendo iguais a zero
                //se isso for verdade então para a execução
                if (n + m + linhas + colunas == 0) break;

                //aqui instancia um novo quadro sendo do tipo String contendo um digito do que o usuario digitou
                quadro = new String[n];

                //no for vai de zero até a posição n, logo o vetor de quadro recebe o que o usuario digitou
                for (i = 0; i < n; i++) {
                    quadro[i] = sc.next();
                }

                //aqui será dada a resposta do quadro pintado em seu limite de linhas e colunas
                resposta = getResposta(n, m, linhas, colunas, quadro);
                System.out.println(resposta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    //no método de resposta utiliza-se o Merge sort com a técnica de Dividir e Conquistar
    //serão declarados os atributos e um vetor String de quadros
    static int getResposta(int n, int m, int linhas, int colunas, String[] quadro) {
        int resposta = 0, i, j; //declaração da variavel resposta recebendo i e j
        String[] emBranco = new String[n]; //aqui é instanciado um vetor de String recebendo n

        //no for vai de zero até a enésima posição
        for (i = 0; i < n; i++) {
            StringBuilder cor = new StringBuilder(); //estanciamento do StringBuilder cor onde
            // é aumentada para acomodar os caracteres adicionais

            //o for vai da posição zero até a posição m, onde o objeto cor pega a string zero
            for (j = 0; j < m; j++) cor.append('0');
            emBranco[i] = cor.toString(); //o vetor emBranco pega a cor
        }

        //nesse for serão mostradas as pinturas com suas respectivas linhas, colunas em um quadro branco a partir do if
        for (i = 0; i < n - linhas + 1; i++) {
            for (j = 0; j < m - colunas + 1; j++) {
                if (emBranco[i].charAt(j) != quadro[i].charAt(j)) {
                    pintar(linhas, colunas, i, j, emBranco);
                    resposta++;
                }
            }
        }

        //aqui retorna a comparação entre todos os atributos juntamente com a resposta sendo -1
        return compara(n, m, quadro, emBranco) ? resposta : -1;
    }

    //nesse método irá fazer a pintura
    static void pintar(int linhas, int colunas, int x, int y, String[] emBranco) {

        //variavel declarada para saber o limite das bordas do quadro
        int linhaLimite = linhas + x, clim = colunas + y, i, j;

        //for declarado para limitar caso ultrapase as bordas
        for (i = x; i < linhaLimite; i++) {
            StringBuilder cor = new StringBuilder(emBranco[i]);

            //no for abaixo mostrará caso 0 indicar branco ou 1 indicar preto, e irá fazer a pintura do
            // quadro com base nessa perspectiva
            for (j = y; j < clim; j++) {
                char ch = (emBranco[i].charAt(j) == '1') ? '0' : '1';
                cor.replace(j, j + 1, ch + "");
            }

            //em seguida o quadro branco será pintado com as cores
            emBranco[i] = cor.toString();
        }
    }

    //método que fará a comparação da resposta da pintura com o quadro a ser pintado
    static boolean compara(int n, int m, String[] quadro, String[] emBranco) {
        int i, j;

        //declaração da resposta sendo verdadeira
        boolean resposta = true;

        //nesse for irá até a enésima posição, logo em seguida o quadro passará de ser branco para um quadro pintado
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (quadro[i].charAt(j) != emBranco[i].charAt(j)) {
                    resposta = false;
                }
            }
        }

        //por fim mostrará a resposta do quadro pintado
        return resposta;
    }
}
