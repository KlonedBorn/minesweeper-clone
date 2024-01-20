package com.kloneborn;

public enum Difficulty {
    EASY(10, 10, 10),
    MEDIUM(16, 16, 40),
    HARD(30, 16, 99);

    private int columns, rows, mines;

    private Difficulty(int columns, int rows, int mines) {
        this.columns = columns;
        this.rows = rows;
        this.mines = mines;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getMines() {
        return mines;
    }

}
