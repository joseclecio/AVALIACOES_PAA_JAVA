public class Teste2 {
    public static void main(String[] args) {
        int[][] M = new int[100][4]; //materiz declarada

        // for de atribuição de valores para a M
        for (int i = 0; i < 100; i++) { //matriz de 0 a 100
            for (int j = 0; j < 4; j++) { // matriz de 0 a 4

                M[i][j] = (int) ((Math.random() * 100) + 1); // gerando numero aleatorio
                System.out.print(M[i][j] + " | "); // mostrando

            }
            System.out.println("");
        }

        System.out.println("");

        int[] VC = new int[4]; //vetor para armazenar os menores valores da matriz

        int[] aux1 = new int[4]; //vetor auxiliar
        // descobre qual o menor numero de cada coluna
        for (int i = 0; i < 100; i++) {//matriz de 0 a 100
            for (int j = 0; j < 4; j++) {// matriz de 0 a 4

                aux1[j] = M[i][j]; //vetor auxiliar para armazenar os dados da matriz

                if (i == 0) {
                    VC[j] = aux1[j];
                }

                if (j == 3) {

                    for (int j2 = 0; j2 < 4; j2++) {
                        if (VC[j2] > aux1[j2]) {
                            VC[j2] = aux1[j2];
                        }
                    }
                }
            }
        }

        //apenas mostra os menores numero de cada coluna
        for (int i = 0; i < VC.length; i++) {

            System.out.println("Menor da " + i + "º coluna é " + VC[i]);

        }
    }
}
