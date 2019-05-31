package com.company;
import java.util.Scanner;
public class Main {


    public static void main(String[] args) {
        //System.out.println("jdslj");



        // TwoPlayerGame game = new TwoPlayerGame();
        // game.startGame();


        System.out.println("Welcome to Connect Four!");
        System.out.println("Please type \"t\" to play 2 player mode and \"s\" to play single player mode");
        Scanner s = new Scanner(System.in);
        boolean m = s.hasNext("s");
        if(m) {
            CpuGame game = new CpuGame();
            game.startGame();
        } else  {
            TwoPlayerGame gameTwoP = new TwoPlayerGame();
            gameTwoP.startGame();
        }


//        CpuGame game = new CpuGame();
//        game.startGame();
//
    }
}
