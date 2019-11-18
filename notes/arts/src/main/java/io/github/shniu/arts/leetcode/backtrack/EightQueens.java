package io.github.shniu.arts.leetcode.backtrack;

/**
 * 八皇后问题，简单实现
 */
public class EightQueens {
    int[] result = new int[8];
    int count;

    public void cal8queens(int row) {
        if (row == 8) {
            printQueens(result);
            count++;
            return;
        }

        for (int col = 0; col < 8; col++) {
            if (isOk(row, col)) {
                result[row] = col;
                cal8queens(row + 1);
            }
        }
    }

    private boolean isOk(int row, int col) {
        int leftup = col - 1, rightup = col + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == col) return false;
            if (leftup >= 0 && result[i] == leftup) return false;
            if (rightup < 8 && result[i] == rightup) return false;
            --leftup;
            ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (result[row] == col) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.cal8queens(0);
        System.out.println(eightQueens.count);
    }
}
