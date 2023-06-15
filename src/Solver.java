import java.util.*;

public class Solver {
    int[][] board;

    public Solver(int[][] board) {
        this.board = board;
    }

    public void printBoard() {
        for (int row = 0; row < 9; row++) {
            if (row != 0 && row % 3 == 0)
                System.out.println("------+-------+------");
            for (int col = 0; col < 9; col++) {
                if (col != 0 && col % 3 == 0)
                    System.out.print("| ");
                if (board[row][col] == 0)
                    System.out.print(". ");
                else
                    System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public void solve() {
        int i = 1;
        while (i != 0) {
            i = solveOnce();
        }
    }

    public int solveOnce() {
        int count = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = board[row][col];
                if (value == 0) {
                    HashSet<Integer> possibilities = getPossibilities(row, col);
                    if (possibilities.size() == 1) {
                        board[row][col] = possibilities.iterator().next();
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private HashSet<Integer> getPossibilities(int row, int col) {
        HashSet<Integer> candidateNums = new HashSet<>();
        HashSet<Integer> usedNums = new HashSet<>();

        if (board[row][col] > 0)
            return candidateNums;

        int boxX = (col / 3) * 3;
        int boxY = (row / 3) * 3;

        for (int i = 0; i < 9; i++) {
            candidateNums.add(i + 1);  // record numbers 1-9 as possibilities
            usedNums.add(board[row][i]);  // row
            usedNums.add(board[i][col]);  // column
            usedNums.add(board[boxY + i / 3][boxX + i % 3]);  // 3x3 box
        }

        candidateNums.removeAll(usedNums);  // remove all used nums from possible nums to find unused nums
        return candidateNums;
    }
}
