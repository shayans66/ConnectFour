package com.company;

public class Grid {
    // height
    public final int ROWS = 6;
    // length
    public final int COLS = 7;

    public Square[][] grid = new Square[ROWS][COLS];

    public Grid(){
        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLS; j++){
                grid[i][j] = new Square();
            }
        }
    }

}
