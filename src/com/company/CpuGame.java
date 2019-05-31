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
        System.out.println("Welcome to Single Player Connect Four!");
        System.out.println("-----------------------------------");

        System.out.println("Would you like to play first or second?");
        System.out.println("Please enter 1 for first and 2 for second.");

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        if(n % 2 == 1) {
            System.out.println("you have chosen to play first");
            grid.printGrid();
            playerFirst();
        } else {
            System.out.println("you have chosen to play second");
            grid.printGrid();
            playerSecond();
        }
    }

    public void playerFirst() {
        while( !grid.isGridFull() && !grid.isThereWin()){

            // Ask for O's turn ( THE PLAYER )
            askForTurn(Square.O);
            //getBotTurn(Square.X);

            grid.printGrid();

            if(grid.isGridFull() || grid.isThereWin()){
                break;
            }

            // Ask for bot's turn (x)
            getBotTurn(Square.X);

            grid.printGrid();
        }



        String winner = getWinner();
        System.out.print(winner);


    }

    public void playerSecond() {
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

        String winner = getWinner();
        System.out.print(winner);
    }

    // returns winner message, if there is win
    public String getWinner() {
        String winner = "";

        if( grid.getWinner()==Square.X){
            winner = "X";
        }
        if( grid.getWinner()==Square.O){
            winner = "O";
        } if(grid.isGridFull() == true) {
            return ("There are no more moves available. It's a tie");
        }

        winner = (winner + " is the winner! Congratulations to "+ winner);
        return winner;

    }

    public void askForTurn(int status){
        String obj = "";
        if (status == Square.X) obj = "X";
        if (status == Square.O) obj = "O";
        System.out.print(obj + "'s turn! Enter a column from 1-7: ");

        int col = input.nextInt();
        input.nextLine();

        System.out.println();
        while (col < 1 || col > 7) {
            System.out.print("Out of bounds, try again: ");
            col = input.nextInt();
            System.out.println();
        }
        while(grid.isColFull(col)){
            System.out.print("This column is full, try again: ");
            col = input.nextInt();
            System.out.println();
        }

        grid.setCol(col, status);
    }

    public void getBotTurn(int status){
        // 6 Rows, 7 Columns
        String obj = "";
        if(status == Square.X) obj = "X";
        if(status == Square.O) obj = "O";
        System.out.println("It is the bot's ( " + obj + " ) turn! Press enter to continue. ");

        input.nextLine();
        //System.out.println("*");
        //input.nextLine();
        //System.out.println("**");

        int[] arr = grid.getBotMove();

        if( ( grid.hasNumInARow(Square.X, 3) || grid.hasNumInARow(Square.O, 3) ) && arr!=null  ){


            int row = arr[0];
            int col = arr[1];

            if(grid.canPieceBePutHere(row,col)){

                grid.setCol(col,status);
                return;
            }
        }


        int col = 1 + rand.nextInt(7);

        while( grid.isColFull(col)){
            col = 1 + rand.nextInt(7);
        }

        grid.setCol(col, status);

    }

}