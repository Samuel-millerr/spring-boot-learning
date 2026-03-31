package com.learningspringboot.maratonajava.beecrowd;

import java.io.IOException;
import java.util.Scanner;

public class BEE1004ProdutoSimples {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int num01 = 0;
        int num02 = 0;

        num01 = Integer.parseInt(scanner.nextLine());
        num02 = Integer.parseInt(scanner.nextLine());

        int PROD = num01 * num02;
        System.out.println("PROD = " + PROD);
    }
}
