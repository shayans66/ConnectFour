package com.company;

public class Square {

    public static final int EMPTY = 0;
    public static final int X = 1;
    public static final int O = 1;

    private int status;
    private boolean isEmpty;

    // empty cell
    public Square() {
        status = 0;
        isEmpty = true;
    }
    public Square(int status){
        this.status = status;
        if(status == EMPTY){
            isEmpty = true;
        }
        else{
            isEmpty = false;
        }
    }

    public int getStatus() {
        return status;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setStatus(int status) {
        this.status = status;
        if(status == EMPTY){
            isEmpty = true;
        }
        else{
            isEmpty = false;
        }
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }


    public String toString(){
        return ""+this.getStatus();
    }

}
