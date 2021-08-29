package Avaliacao1_PAA;

import java.util.Scanner;

import java.io.IOException;
import java.util.*;

public class Uri_1054 {

    //declaracao de variaveis estaticas
    public static int CasoDeTestes; //variavel para os casos de testes
    public static int NumeroDeRochas, Distancia; //declaração do numero de rochas e da distancia sendo como inteiro
    public static String[] LinhaDescricaoPedras; //vetor declarado linha das pedras
    public static Vector<Integer> vetor; //vetor para armazenamento

    //método main contendo NumberFormatException que será lançado por precaução caso
    // não puder converter a string em um tipo numérico
    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner s = new Scanner(System.in); //importação do Scanner para captura dos dados que forem digitados
        int Casos = 1; //declaração de variavel inteira para Casos de testes iniciando em 1

        CasoDeTestes = s.nextInt(); //captura do que foi digitado pelo usuario na variavel CasoDeTestes


        while (CasoDeTestes-- > 0) { //aqui utiliza-se um while que será um loop maior que zero
            NumeroDeRochas = s.nextInt(); //captura dos numeros de rochas no tipo inteiro
            Distancia = s.nextInt(); //captura da distancia do tipo inteiro
            vetor = new Vector<>(); //extanciamento do vetor

            vetor.add(0); // adição do zero para o vetor
            vetor.add(0);

            //no for abaixo utiliza-se a Força Bruta pela Busca Linear, onde o for vai de zero até o NumeroDeRochas
            //fazendo com que o vetor de String LinhaDescricaoPedras capture a letra e o número digitado pelo usuario
            //logo, tendo uma quebra da string em torno das correspondências da expressão regular fornecida pelo usuario
            //em seguida será adicionado a variavel D a LinhaDescricaoPedras onde será comparada com a letra B que neste caso
            //seria BIG, caso for igual a B será adicionado ao vetor a variavel D.
            for (int i = 0; i < NumeroDeRochas; i++) {
                LinhaDescricaoPedras = s.next().split("-", -1);
                int D = Integer.parseInt(LinhaDescricaoPedras[1]);
                vetor.add(D);
                if (LinhaDescricaoPedras[0].equals("B"))
                    vetor.add(D);
            }

            //aqui será adicionada a distancia entre as margens
            vetor.add(Distancia);
            vetor.add(Distancia);

            //aqui abaixo tive que colocar o resultado pelo menor numero possivel para que fosse calculado pelo Math
            //o for será da posição 2 até o tamanho do vetor, fiz isso para que quando fosse expressado pelo get, seria
            //diminuido -2
            int Resultado = -10000000;
            for (int i = 2; i < vetor.size(); i++) {
                Resultado = Math.max(Resultado, vetor.get(i) - vetor.get(i - 2));
            }

            //amostra do resultado final
            System.out.printf("Case %d: %d\n", Casos++, Resultado);

        }
    }
}