package Avaliacao1_PAA;

public class Teste {
    public void funcao(int A, int B) {

        int fat = 1, cont = 0, soma = 0, media = 0; //declaração de variaveis

        if (A > B) { //verifica se B é maior do que A
            System.out.println("B tem que ser maior que A");
        } else {//Se B for maior que A então executa o else abaixo
            for (int i = 1; i < B; i++) { //calculo de fatorial com o for
                fat *= i; //formula para calcular o fatorial

                if (i % 2 != 0) { //mostrar somente os números impares

                    if (i > A) { //mostrar os numeros impares com fatorial entre A e B

                        cont++; //conta quantas vezes A é maior que B
                        soma += fat; // soma o fatorial
                        media = soma / cont; //calcula a média do fatorial
                        System.out.println("Fatorial de " + i + ": " + fat);
                    }
                }
            }
            System.out.print("Media de fatoriais: " + media);
        }

    }

    public static void main(String[] args) {

        Teste t = new Teste();

        t.funcao(1, 9);

    }
}
