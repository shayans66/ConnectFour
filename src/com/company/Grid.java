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

    public boolean isGridFull(){
        for(int r=0; r<grid.length; r++){
            for(int c=0; c<grid[0].length; c++){
                if(grid[r][c].getStatus() != Square.EMPTY ){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isColFull(int col){
        for(int r=ROWS-1; r>=0; r--){
            if( grid[r][col].getStatus() != Square.EMPTY ) {
                return false;
            }

        }
        return true;
    }
    public void setX(int col){
        for(int r=ROWS-1; r>=0; r--){
            if( grid[r][col].getStatus() == Square.EMPTY ) {
                grid[r][col].setStatus(Square.X);
                break;
            }
        }
    }
    public void setO(int col){
        for(int r=ROWS-1; r>=0; r--){
            if( grid[r][col].getStatus() == Square.EMPTY ) {
                grid[r][col].setStatus(Square.O);
                break;
            }
        }
    }

    /**
     * Returns int if anyone won
     * @return
     * Square.EMPTY (0) if no one
     * Square.X (1) if X won
     * Square.O (2) if O won
     */
    public int getWinner(){
        int counter = 0;
        int status = grid[0][0].getStatus();

        // check for horizontal matches
        for(int r=0; r<grid.length - 3; r++){
            for(int c=0; c<grid[0].length; c++){

                if(grid[r][c].getStatus() == status){
                    counter++;
                    if(counter == 4){
                        return status;
                    }
                }
                else{
                    counter = 0;
                    status = grid[r][c].getStatus();
                }

            }
            // initialize back after going back each time
            counter = 0;
            status = grid[r][0].getStatus();
        }

        // check for vertical matches
        for(int c=0; c<grid[0].length; c++){
            for(int r=0; r<grid.length-3; r++){

                if(grid[r][c].getStatus() == status){
                    counter++;
                    if(counter == 4){
                        return status;
                    }
                }
                else{
                    counter = 0;
                    status = grid[r][c].getStatus();
                }

            }
            // initialize back after going back each time
            counter = 0;
            status = grid[0][c].getStatus();
        }

        // check for diaganol matches

        return Square.EMPTY;
    }

}
