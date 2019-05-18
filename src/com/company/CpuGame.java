package com.company;

import java.util.Random;
import java.util.Scanner;

public class CpuGame {
    Grid grid;
    Random rand = new Random();
    Scanner input = new Scanner(System.in);

    public CpuGame() {
        grid = new Grid();


    }

    public void startGame(){
        System.out.println("-----------------------------------");
        System.out.println("Welcome to CPU Connect Four!");
        System.out.println("-----------------------------------");

        grid.printGrid();

        while( !grid.isGridFull() && !grid.isThereWin()){

            // Ask for X's turn ( THE BOT )

            getBotTurn(Square.X);

            grid.printGrid();


            if(grid.isGridFull() || grid.isThereWin()){
                break;
            }

            // Ask for O's turn
            askForTurn(Square.O);

            grid.printGrid();
        }

        String winner = "";

        if( grid.getWinner()==Square.X){
            winner = "X";
        }
        if( grid.getWinner()==Square.O){
            winner = "O";
        }

        System.out.println(winner + " is the winner! Congratulations to "+winner);
    }

    public void askForTurn(int status){
        String obj = "";
        if (status == Square.X) obj = "X";
        if (status == Square.O) obj = "O";
        System.out.print(obj + "'s turn! Enter a column from 1-7: ");

        int col = input.nextInt();

        System.out.println();
        while (col < 1 || col > 7) {
            System.out.print("Out of bonds, try again: ");
            col = input.nextInt();
            System.out.println();
        }
        while(grid.isColFull(col) == true ){
            System.out.print("This column is full, try again: ");
            col = input.nextInt();
            System.out.println();
        }

        grid.setCol(col, status);
    }

    public void getBotTurn(int status){
        // 6 Rows, 7 Columns
        System.out.print("It is the bot's turn! Press enter to continue. ");
        input.nextLine();

        int col = 1 + rand.nextInt(7);

        while( grid.isColFull(col)){
            col = 1 + rand.nextInt(7);
        }

        grid.setCol(col, status);
    }
}