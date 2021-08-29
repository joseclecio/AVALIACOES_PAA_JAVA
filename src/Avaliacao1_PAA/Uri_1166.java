package Avaliacao1_PAA;

import java.util.Scanner;

public class Uri_1166 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for(int k = 1; k <= TC; k++)
        {
            int n = sc.nextInt();
            int[] estacas = new int[n];

            boolean adicionada = true;
            int i;
            for(i = 1; adicionada; i++)
            {
                adicionada = false;
                for(int j = 0; j < n && !adicionada; j++)
                    if(estacas[j]==0 || estacas[j]+i == ((int)(Math.sqrt(estacas[j]+i))*((int)Math.sqrt(estacas[j]+i))))
                    {
                        estacas[j] = i;
                        adicionada = true;
                    }

            }
            System.out.println(i-2);
        }



    }
}
