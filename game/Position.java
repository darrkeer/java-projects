package game;

public interface Position {
    int getN();

    int getM();

    boolean isValid(Move move);

    String outputPosition();

    Cell getTurn();
}
