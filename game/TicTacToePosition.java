package game;

import java.util.Map;

public class TicTacToePosition implements Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, '0',
            Cell.E, '.',
            Cell.B, ' '
    );

    private final Cell[][] cells;
    private final Cell turn;

    public TicTacToePosition(Cell[][] cells, Cell turn) {
        this.cells = cells;
        this.turn = turn;
    }

    @Override
    public int getN() {
        return cells.length;
    }

    @Override
    public int getM() {
        if (cells.length == 0)
            return 0;
        return cells[0].length;
    }

    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < getN()
                && 0 <= move.getCol() && move.getCol() < getM()
                && move.getCell() == turn && cells[move.getRow()][move.getCol()] == Cell.E;
    }

    @Override
    public String outputPosition() {
        StringBuilder sb = new StringBuilder("Board:" + System.lineSeparator());
        for (int j = 1; j <= getM(); j++)
            sb.append("\t").append(j);
        sb.append(System.lineSeparator());
        for (int i = 0; i < getN(); i++) {
            sb.append(i + 1);
            for (int j = 0; j < getM(); j++)
                sb.append("\t").append(SYMBOLS.get(cells[i][j]));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Cell getTurn() {
        return turn;
    }
}
