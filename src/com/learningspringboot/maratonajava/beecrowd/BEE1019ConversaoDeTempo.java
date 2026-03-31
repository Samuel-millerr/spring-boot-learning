package com.learningspringboot.maratonajava.beecrowd;

import java.io.IOException;
import java.util.Scanner;

public class BEE1019ConversaoDeTempo {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int seg = scanner.nextInt();
        int segLenght = seg;
        int min = 0;
        int hrs = 0;

        for (int i = 1; i <= segLenght; i++) {
            if (seg-59 > 0) {
                seg-=60;
                min++;
                if (min == 60) {
                    min = 0;
                    hrs++;
                }
            }
        }

        System.out.println(hrs + ":" + min + ":" + seg);
    }
}
