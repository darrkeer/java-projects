package game;

import java.util.Arrays;
import java.util.List;

public class TicTacToeBoard implements Board {
    private final int k;
    private static final int[][] checks = new int[][]{
            new int[]{1, 1}, new int[]{1, -1}, new int[]{1, 0}, new int[]{0, 1},
            new int[]{-1, -1}, new int[]{-1, 1}, new int[]{-1, 0}, new int[]{0, -1}
    };

    private int dr = 0;
    private int dc = 0;

    private final int n;
    private final int m;

    private final Cell[][] cells;
    private Cell turn = Cell.X;

    public TicTacToeBoard(int n, int m, int k) {
        this.k = k;
        this.n = n;
        this.m = m;
        this.cells = new Cell[n][m];
        for(Cell[] row : cells)
            Arrays.fill(row, Cell.E);
    }

    private boolean isFilteredDiag(int r, int c, List<Cell> allowed){
        if(!allowed.contains(cells[r][c]))
            return false;
        int[] cnt = new int[]{1, 1, 1, 1};
        for (int dir = 0; dir < checks.length; dir++) {
            int i = r;
            int j = c;
            for(int t=0; t<k; t++){
                i += checks[dir][0];
                j += checks[dir][1];
                if (i < 0 || j < 0 || i >= n || j >= m)
                    break;
                if (!allowed.contains(cells[i][j]))
                    break;
                cnt[dir % 4]++;
            }
        }
        return cnt[0] >= k || cnt[1] >= k || cnt[2] >= k || cnt[3] >= k;
    }

    private boolean couldWin(Cell cell){
        while(dr < n){
            while(dc < m){
                if(isFilteredDiag(dr, dc, List.of(cell, Cell.E)))
                    return true;
                dc++;
            }
            dc = 0;
            dr++;
        }
        return false;
    }

    @Override
    public Result applyMove(Move move) {
        cells[move.getRow()][move.getCol()] = move.getCell();
        turn = turn == Cell.X ? Cell.O : Cell.X;
        if (isFilteredDiag(move.getRow(), move.getCol(), List.of(move.getCell())))
            return Result.WIN;
        if(!couldWin(Cell.X) && !couldWin(Cell.O))
            return Result.DRAW;
        return Result.UNDEFINED;
    }

    @Override
    public Position getPosition() {
        return new TicTacToePosition(cells, turn);
    }

    protected void changeCell(int r, int c, Cell cell){
        cells[r][c] = cell;
    }
}
