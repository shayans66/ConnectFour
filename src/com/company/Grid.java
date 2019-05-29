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
        System.out.println("");
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
     * Returns true/false if given square type has NUM pieces in a row
     * num will mostly be four
     *
     * Precondition: Status equals Square.X (1) or Square.O (2)
     */
    public boolean hasNumInARow(int status, int num){
        // might remove
        if(status == Square.EMPTY) return false;
        //

        int counter = 0;

        // check for horizontal matches
        for(int r=0; r<grid.length; r++){
            for(int c=0; c<grid[0].length; c++){

                if(grid[r][c].getStatus() == status){
                    counter++;
                    if(counter == num){
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
<<<<<<< HEAD
        counter = 0;
=======

        counter = 0;

>>>>>>> 707e214594af068a72de3a8d8dbb6f6e7b30b5c6
        // check for vertical matches
        for(int c=0; c<grid[0].length; c++){
            for(int r=0; r<grid.length; r++){
                if(grid[r][c].getStatus() == status){
                    counter++;
                    if(counter == num){
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
        for(int r=0; r<ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                for(int s = 0; s < num; s++){
                    try{
                        if(grid[r+s][c+s].getStatus() == status){
                            counter++;
                            if(counter == num){
                                return true;
                            }
                        }
                        else{
                            counter = 0;
                        }
                    }
                    catch(Exception e){
                        counter = 0;
                    }
                }
            }
            counter = 0;
        }


        // check inverse diaganolly (bottom up)
        //30 21 12 03
        //31 22 13 04
        for(int r=0; r<ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                for(int s = 0; s < num; s++){
                    try{
                        if(grid[r-s][c+s].getStatus() == status){
                            counter++;
                            if(counter == num){
                                return true;
                            }
                        }
                        else{
                            counter = 0;
                        }
                    }
                    catch(Exception e){
                        counter = 0;
                    }
                }
            }
            counter = 0;
        }

        return false;
    }
    public boolean isThereWin(){
        boolean result = hasNumInARow(Square.X, 4) || hasNumInARow(Square.O, 4);
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
        if(hasNumInARow(Square.X, 4)){
            return Square.X;
        }
        if(hasNumInARow(Square.O, 4)){
            return Square.O;
        }
        return Square.EMPTY;
    }

    /**
     *
     * @param row
     * @param col
     * @return
     * returns whether a piece can be put at this specific index
     */
    public boolean canPieceBePutHere(int row, int col){
        int row1 = getRowInCol(col);
        return row == row1;
    }

    /**
     *
     * @param col
     * @return
     * returns the row index in which an inserted piece would go.
     * returns -1 if the column is full, thus piece cannot go in
     */
    public int getRowInCol(int col){
        if(col<1 || col>7){
            return -1;
        }
        for(int r=ROWS-1; r>=0; r--){
            if( grid[r][col-1].getStatus() == Square.EMPTY ) {
                return r+1;
            }

        }
        return -1;
    }

    /**
     * If there is 3 in a row for either side, and a blank piece after, the bot will put a piece there
     * @return
     * a row,col int array specifying the row and col (for output) that bot would need to put in a piece at the end of
     *  a 3 in a row
     * returns null if you can't
     */
    public int[] getBotMove(){
        int[] arr = null;

        int status = grid[0][0].getStatus();

        int counter = 0;

        int row; int col;

        // check for horizontal matches
        for(int r=0; r<grid.length; r++){
            for(int c=0; c<grid[0].length; c++){

                if(grid[r][c].getStatus() == status){
                    counter++;
                    if(counter == 3){


                        if(canPieceBePutHere(r,c+1)){
                            return new int[]{r,c+1};
                        }

                    }
                }
                else{
                    counter = 0;
                    status = grid[r][c].getStatus();
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
                    if(counter == 3){
                        if(canPieceBePutHere(r+1,c)){
                            return new int[]{r+1,c};
                        }
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
        for(int r=0; r<ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                for(int s = 0; s < 3; s++){
                    try{
                        if(grid[r+s][c+s].getStatus() == status){
                            counter++;
                            if(counter == 3){
                                if(canPieceBePutHere(r+1,c+1)){
                                    return new int[]{r+1,c+1};
                                }
                            }
                        }
                        else{
                            counter = 0;
                        }
                    }
                    catch(Exception e){
                        counter = 0;
                    }
                }
            }
            counter = 0;
        }
        // check inverse diaganolly (bottom up)
        //30 21 12 03
        //31 22 13 04
        for(int r=0; r<ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                for(int s = 0; s < 3; s++){
                    try{
                        if(grid[r-s][c+s].getStatus() == status){
                            counter++;
                            if(counter == 3){
                                if(canPieceBePutHere(r-1,c+1)){
                                    return new int[]{r-1,c+1};
                                }
                            }
                        }
                        else{
                            counter = 0;
                        }
                    }
                    catch(Exception e){
                        counter = 0;
                    }
                }
            }
            counter = 0;
        }



        return null;
    }


}
