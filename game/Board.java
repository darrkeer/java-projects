package game;

public interface Board {
    Result applyMove(Move m);

    Position getPosition();
}
