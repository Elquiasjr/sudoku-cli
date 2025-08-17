package com.example.demo.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private Cell[][] cells;

    public Board() {
        cells = new Cell[9][9];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCell(int row, int col, Cell cell){
        cells[row][col] = cell;
    }

    public void populateEmptyCells(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if(cells[i][j] == null){
                    cells[i][j] = new Cell(0, false);
                }
            }
        }
    }

    public void insertCell(int row, int col, Cell cell){
        if(cells[row][col].isLocked()){
            System.out.println("Essa célula está bloqueada");
            return;
        } else {
            cells[row][col] = cell;
            System.out.println("Número inserido com sucesso");
        }
    }

    public void removeCell(int row, int col){
        if(cells[row][col].isLocked()){
            System.out.println("Essa célula está bloqueada");
            return;
        } else {
            cells[row][col] = new Cell(0, false);
            System.out.println("Número removido com sucesso");
        }
    }

    public List<Integer> getNumbers(){
        return Arrays.stream(cells)
        .flatMap(Arrays::stream)
        .map(Cell::getNumber)
        .collect(Collectors.toList());
    } 
    
}
