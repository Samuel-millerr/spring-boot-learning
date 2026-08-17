package com.learningspringboot.maratonajava.introducao;

public class Topic4 {
    public static void main(String[] args) {
        int oldAge = 20;
        boolean isAuthorizedBuyDrink = oldAge >= 18;

        if(isAuthorizedBuyDrink) {
            System.out.println("Autorizado a comprar bebida alcólica");;
        }

        if(!isAuthorizedBuyDrink) {
            System.out.println("Não autorizado a comprar bebida alcólica");
        }

        System.out.println("Fora do if");
    }
}
