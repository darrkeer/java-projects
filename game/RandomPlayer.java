package game;

import java.util.Random;

public class RandomPlayer implements Player{
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position, final Cell cell) {
//        TicTacToePosition realPosition = (Tic) position;
//        realPosition.(1, 1, Cell.X);

        if (cell == Cell.B) {
            throw new IllegalArgumentException();
        }
        while (true) {
            int r = random.nextInt(position.getN());
            int c = random.nextInt(position.getM());
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }

    @Override
    public String toString(){
        return "bot" + hashCode();
    }
}
