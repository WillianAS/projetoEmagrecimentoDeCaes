package com.example.willian.projetopurina;

/**
 * Created by willian on 08/11/17.
 */

public class CalculadoraPrevisao {

    public int calculaPrevisao(float peso, float pressaoEmagrecimento, float pesoIdeal) {

        int countMonth = 1;

        do {
            if (countMonth == 3) {
                pressaoEmagrecimento += 5;
            }
            else if (countMonth == 5) {
                pressaoEmagrecimento += 5;
            }

            peso = peso - (peso * (pressaoEmagrecimento/1000));

            countMonth += 1;
        } while (peso > pesoIdeal);


        return countMonth;
    }
}
