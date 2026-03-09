package com.learningspringboot.maratonajava.introducao;

/*
Prática

Crie variáveis para os campos descritos abaixo entre <> e imprima a seguinte mensagem:

Eu <nome>, morando no endereço <endereço>,
confirmo que recebi o salário de <salario>, na data <data>.
 */
public class Topic02Exercise {
    public static void main(String[] args) {
        String name = "Samuel";
        String adress = "Rua Sarandi Saranda 558";
        double salary = 1621.20;
        String date = "09/03/2025";

        System.out.printf(
                "Eu %s, morando no endereço %s, confirmo que recebi o salário de %f, na data %s",
                name, adress, salary, date
        );
    }
}
