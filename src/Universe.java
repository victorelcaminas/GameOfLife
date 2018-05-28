/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victor
 */
public class Universe {

    private boolean[][] matrix;
    private boolean[][] newMatrix;

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    public Universe() {
        Singleton singleton = Singleton.getInstance();
        matrix = new boolean[singleton.getNumRows()][singleton.getNumCols()];
        newMatrix = new boolean[singleton.getNumRows()][singleton.getNumCols()];
        for (int i = 0; i < singleton.getNumRows(); i++) {
            for (int j = 0; j < singleton.getNumCols(); j++) {
                matrix[i][j] = false;
                newMatrix[i][j] = false;
            }
        }
    }

    public void tick() {
        Singleton singleton = Singleton.getInstance();
        int numNeighbours;
        for (int i = 0; i < singleton.getNumRows(); i++) {
            for (int j = 0; j < singleton.getNumCols(); j++) {
                newMatrix[i][j] = false;
                numNeighbours = numNeighbours(i,j);
                if (matrix[i][j] && (numNeighbours ==2 || numNeighbours ==3)) {
                    newMatrix[i][j] = true;
                } else {
                    if ( ! matrix[i][j] && numNeighbours == 3) {
                        newMatrix[i][j] = true;
                    }
                }
            }
        }
        boolean[][] temp = matrix;
        matrix = newMatrix;
        newMatrix = temp;
        
    }

    public int numNeighbours(int row, int col) {
        int counter = 0;
        Singleton singleton = Singleton.getInstance();
        if (row - 1 >= 0) {
            if (col - 1 >= 0) {
                if (matrix[row - 1][col - 1]) {
                    counter++;
                }
            }
            if (matrix[row - 1][col]) {
                counter++;
            }
            if (col + 1 < singleton.getNumCols()) {
                if (matrix[row - 1][col + 1]) {
                    counter++;
                }
            }
        }
        if (col - 1 >= 0) {
            if (matrix[row][col - 1]) {
                counter++;
            }
        }
        if (col + 1 < singleton.getNumCols()) {
            if (matrix[row][col + 1]) {
                counter++;
            }
        }
        if (row + 1 < singleton.getNumRows()) {
            if (col - 1 >= 0) {
                if (matrix[row + 1][col - 1]) {
                    counter++;
                }
            }
            if (matrix[row + 1][col]) {
                counter++;
            }
            if (col + 1 < singleton.getNumCols()) {
                if (matrix[row + 1][col + 1]) {
                    counter++;
                }
            }
        }
        return counter;
    }

}
