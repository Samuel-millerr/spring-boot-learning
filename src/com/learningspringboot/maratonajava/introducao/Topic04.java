package com.learningspringboot.maratonajava.introducao;

public class Topic04 {
    public static void main(String[] args) {
        int oldAge = 20;
        boolean isAuthorizedBuyDrink = oldAge >= 18;

        if (isAuthorizedBuyDrink) {
            System.out.println("Autorizado a comprar bebida alcólica");
            ;
        }

        if (!isAuthorizedBuyDrink) {
            System.out.println("Não autorizado a comprar bebida alcólica");
        }

        if (isAuthorizedBuyDrink) {
            System.out.println("Autorizado a comprar bebida alcólica");
        } else {
            System.out.println("Não autorizado a comprar bebida alcólica");
        }

        if (oldAge < 15) {
            System.out.println("CATEGORIA: Infantil");
        } else if (oldAge < 18) {
            System.out.println("CATEGORIA: Juvenil");
        } else {
            System.out.println("CATEGORIA: Adulto");
        }

        // Ternary operator

        double salary = 6000;
        String message = "Eu vou doar 10% do meu salário para caridade";
        String messageNegative = "Ainda não tneho condições de doar 10% do meu salário para caridade";

        String result = (salary > 5000) ? message : messageNegative;
        System.out.println(result);
    }
}
