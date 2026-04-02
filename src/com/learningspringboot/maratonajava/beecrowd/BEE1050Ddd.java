package com.learningspringboot.maratonajava.beecrowd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BEE1050Ddd {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        List<Integer> ddds = new ArrayList<>(List.of(61, 71, 11, 21, 32, 19, 27, 31));
        List<String> states = new ArrayList<>(List.of("Brasilia", "Salvador", "Sao Paulo", "Rio de Janeiro", "Juiz de Fora", "Campinas", "Vitoria", "Belo Horizonte"));

        int ddd = scanner.nextInt();

        if (ddds.contains(ddd)) {
            int index = ddds.indexOf(ddd);
            System.out.println(states.get(index));
        } else {
            System.out.println("DDD nao cadastrado");
        }

        scanner.close();
    }
}
