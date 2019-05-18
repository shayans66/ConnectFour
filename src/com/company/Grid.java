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
                if(grid[r][c].getStatus() == Square.EMPTY ){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isColFull(int col){
        for(int r=ROWS-1; r>=0; r--){
            if( grid[r][col-1].getStatus() == Square.EMPTY ) {
                return false;
            }

        }
        return true;
    }
    public void setX(int col){
        for(int r=ROWS-1; r>=0; r--){
            if( grid[r][col-1].getStatus() == Square.EMPTY ) {
                grid[r][col-1].setStatus(Square.X);
                break;
            }
        }
    }
    public void setO(int col){
        for(int r=ROWS-1; r>=0; r--){
            if( grid[r][col-1].getStatus() == Square.EMPTY ) {
                grid[r][col-1].setStatus(Square.O);
                break;
            }
        }
    }
    public void setCol(int col, int status){
        if(status==Square.X){
            setX(col);
        }
        if(status==Square.O){
            setO(col);
        }
    }

    /**
     * Returns true/false if given square type won (has 4 in a row combination
     *
     * Precondition: Status equals Square.X (1) or Square.O (2)
     */
    public boolean isWinner(int status){
        // might remove
        if(status == Square.EMPTY) return false;
        //

        int counter = 0;

        // check for horizontal matches
        for(int r=0; r<grid.length; r++){
            for(int c=0; c<grid[0].length; c++){

                if(grid[r][c].getStatus() == status){
                    counter++;
                    if(counter == 4){
                        return true;
                    }
                }
                else{
                    counter = 0;

                }

            }
            // initialize back after going back each time
            counter = 0;

        }

        // check for vertical matches
        for(int c=0; c<grid[0].length; c++){
            for(int r=0; r<grid.length; r++){

                if(grid[r][c].getStatus() == status){
                    counter++;
                    if(counter == 4){
                        return true;
                    }
                }
                else{
                    counter = 0;
                }

            }
            // initialize back after going back each time
            counter = 0;
        }

        // check for diaganol matches
        //00 11 22 33
        //01 12 23 34
        for(int r=0; r<=2; r++) {
            for (int c = 0; c <= 3; c++) {
                if( grid[r][c].getStatus()==status && grid[r+1][c+1].getStatus()==status && grid[r+2][c+2].getStatus()==status && grid[r+3][c+3].getStatus()==status){
                    return true;
                }
            }
        }
        // check inverse diaganolly (bottom up)
        //30 21 12 03
        //31 22 13 04
        for(int r=3; r<=5; r++) {
            for (int c = 0; c <= 3; c++) {
                if( grid[r][c].getStatus()==status && grid[r-1][c+1].getStatus()==status && grid[r-2][c+2].getStatus()==status && grid[r-3][c+3].getStatus()==status){
                    return true;
                }
            }
        }

        return false;
    }
    public boolean isThereWin(){
        boolean result = isWinner(Square.X) || isWinner(Square.O);
        return result;
    }

    /**
     * Returns who won if any
     * @return
     * Square.X (1) if X won
     * Square.O (2) if O Won
     * Squrae.EMPTY (0) if no one won yet
     */
    public int getWinner(){
        if(isWinner(Square.X)){
            return Square.X;
        }
        if(isWinner(Square.O)){
            return Square.O;
        }
        return Square.EMPTY;
    }


}
