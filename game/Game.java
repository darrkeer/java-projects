package game;

public class Game {
    private final Player[] players = new Player[2];

    private final Board board;

    private final boolean logFinalBoard;

    public Game(Player p1, Player p2, Board board, boolean logFinalBoard) {
        this.players[0] = p1;
        this.players[1] = p2;
        this.board = board;
        this.logFinalBoard = logFinalBoard;
    }

    public Game(Player p1, Player p2, Board board) {
        this(p1, p2, board, false);
    }

    public int play() {
        while (true) {
            for (int p = 0; p < 2; p++) {
                Result res = move(players[p]);
                switch (res) {
                    case WIN:
                        return p + 1;
                    case LOSE:
                        return (p + 1) % 2 + 1;
                    case DRAW:
                        return 0;
                }
            }
        }
    }

    private Result move(Player p) {
        Position pos = board.getPosition();
        Move mv;
        try {
            mv = p.move(pos, pos.getTurn());
        } catch (Exception e) {
            return Result.LOSE;
        }
        if (!pos.isValid(mv)) {
            System.out.println(pos.getTurn() + " player made incorrect move.");
            return Result.LOSE;
        }
        Result res = board.applyMove(mv);
        if (logFinalBoard && res != Result.UNDEFINED) {
            System.out.print("Final ");
            System.out.println(board.getPosition().outputPosition());
        }
        return res;
    }
}
