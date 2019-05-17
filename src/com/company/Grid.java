package com.company;

public class Grid {
    // height
    public final int ROWS = 6;
    // length
    public final int COLS = 7;

    public Square[][] grid;

    public Grid(){
        grid  = new Square[ROWS][COLS];

        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLS; j++){
                grid[i][j] = new Square( Square.EMPTY );
            }
        }
    }

    public void printGrid(){
        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLS; j++){
                if( grid[i][j].getStatus() == Square.X){
                    System.out.print("X ");
                }
                else if( grid[i][j].getStatus() == Square.O){
                    System.out.print("O ");
                }
                else if( grid[i][j].getStatus() == Square.EMPTY){
                    System.out.print("- ");
                }

            }
            System.out.println();
        }
        for(int i=1; i<=COLS; i++){
            System.out.print(i+" ");
        }
        System.out.println();
    }


}
