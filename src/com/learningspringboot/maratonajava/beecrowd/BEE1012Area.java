package com.learningspringboot.maratonajava.beecrowd;

import java.io.IOException;
import java.util.Scanner;

public class BEE1012Area {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        double PI = 3.14159;

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double triangulo = (a * c) / 2;
        double circulo = PI * Math.pow(c, 2);
        double trapezio = ((a + b) * c) / 2;
        double quadrado = b * b;
        double retangulo = a * b;

        System.out.printf("""
                TRIANGULO: %.3f
                CIRCULO: %.3f
                TRAPEZIO: %.3f
                QUADRADO: %.3f
                RETANGULO: %.3f
                """, triangulo, circulo, trapezio, quadrado, retangulo);
    }
}
