package com.company;

import java.util.Scanner;

public class TwoPlayerGame {

    Scanner input = new Scanner(System.in);

    private Grid grid;

    public TwoPlayerGame() {
        grid = new Grid();
    }

    public void startGame(){
        System.out.println("-----------------------------------");
        System.out.println("Welcome to Two-Player Connect Four!");
        System.out.println("-----------------------------------");

        grid.printGrid();

        while( !grid.isGridFull() && !grid.isThereWin()){
            // Ask for X's turn
            askForTurn(Square.X);

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
    public void askForTurn(int status) {
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

}
