package Avaliacao1_PAA;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Uri_1661 {

    //declaracao de variaveis estaticas
    public static long NumeroPessoas;
    public static long[] Garrafas;
    public static long Soma;

    public static void main(String args[]) throws NumberFormatException, IOException {
        Scanner s = new Scanner(System.in);

        while (s.hasNext()) {
            NumeroPessoas = s.nextLong();
            if (NumeroPessoas == 0) break;

            Garrafas = new long[100000 + 1];

            for (int i = 0; i < NumeroPessoas; i++) {
                Garrafas[i] = s.nextLong();
            }

            long resultado = 0;
            for (int i = 1; i < NumeroPessoas; i++) {
                Soma = Garrafas[i - 1];
                Garrafas[i] += Garrafas[i - 1];

                resultado += (Soma < 0) ? (Soma * -1) : Soma; //equivalente ao ifelse
            }
            System.out.println(resultado);
        }

    }
}
