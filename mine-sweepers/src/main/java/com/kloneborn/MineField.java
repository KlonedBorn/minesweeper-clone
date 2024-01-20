package com.kloneborn;

import java.util.List;
import java.util.Random;

public class MineField {
    private int matrix[];
    private int columns;
    private int rows;
    private int mines;

    private MineField(int columns, int rows, int mines) {
        this.columns = columns;
        this.rows = rows;
        this.mines = mines;
        generate();
    }

    private void generate() {
        int size = columns * rows;
        matrix = new int[size];
        Random rand = new Random();

        for (int i = 0; i < mines; i++) {
            int x, y;
            do {
                x = rand.nextInt(columns);
                y = rand.nextInt(rows);
            } while (matrix[x + columns * y] == 9);

            matrix[x + columns * y] = 9; // Place mine at (x, y)

            for (Integer[] neighbor : NEIGHBORS) {
                int xf = x + neighbor[0];
                int yf = y + neighbor[1];

                // Check if neighbor indices are within bounds
                if (xf >= 0 && xf < columns && yf >= 0 && yf < rows) {
                    if (matrix[xf + columns * yf] != 9)
                        matrix[xf + columns * yf]++;
                }
            }
        }
    }

    public int poke(int x, int y) {
        return matrix[x + columns * y];
    }

    public static final MineField create(int cols, int rows, int mines) {
        return new MineField(cols, rows, mines);
    }

    public static final List<Integer[]> NEIGHBORS = List.of(
            new Integer[] { -1, -1 },
            new Integer[] { -1, 0 },
            new Integer[] { -1, 1 },
            new Integer[] { 0, -1 },
            new Integer[] { 0, 1 },
            new Integer[] { 1, -1 },
            new Integer[] { 1, 0 },
            new Integer[] { 1, 1 });

    public int[] getMatrix() {
        return matrix;
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

    public static List<Integer[]> getNeighbors() {
        return NEIGHBORS;
    }
}
