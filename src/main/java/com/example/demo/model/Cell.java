package com.example.demo.model;

public class Cell {
    private int number;
    private boolean isLocked;

    public Cell(int number, boolean isLocked) {
        this.number = number;
        this.isLocked = isLocked;
    }

    public int getNumber() {
        return number;
    }

public boolean isLocked() {
        return isLocked;
    }
}
