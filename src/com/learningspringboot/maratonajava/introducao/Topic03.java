package com.learningspringboot.maratonajava.introducao;

public class Topic03 {
    public static void main(String[] args) {
        // Aritimeticos
        int number01 = 10;
        int number02 = 20;

        int sum = number01+number02;
        int sub = number01-number02;
        double div = (double) number02/number01;
        int mult = number01*number02;

        System.out.println("Soma: " + sum);
        System.out.println("Subtração: " + sub);
        System.out.println("Divisão: " + div);
        System.out.println("Multiplicação: " + mult);

        // Relacionais
        boolean tenIsGreaterThanTwenty = 10 >= 20;
        boolean tenIsLessThanTwenty = 10 <= 20;
        boolean tenIsEqualTwenty = 10 == 20;
        boolean tenIsEqualTen = 10 == 10;
        boolean tenIsNotEqualTen = 10 != 10;

        // Lógicos (&& - AND, || - OR, ! - NOT)
        int age = 35;
        float money = 3500F;

        boolean withinTheLaw = age > 30 && money > 4612;
        System.out.println(withinTheLaw);
    }
}
