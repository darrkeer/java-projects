package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in = new Scanner(System.in);
    private final String name;

    public HumanPlayer(String name) {
        this.name = name;
    }

    @Override
    public Move move(Position pos, Cell cell) {
        System.out.println(pos.outputPosition());
        System.out.println(cell + "'s move.");
        System.out.println("Enter move:");
        Move mv;
        while (true) {
            int r = 0;
            int c = 0;

            boolean ok = false;
            while (!ok) {
                ok = true;
                if (!in.hasNextInt()) {
                    ok = false;
                    in.next();
                } else {
                    r = in.nextInt();
                }
                if (!in.hasNextInt()) {
                    ok = false;
                    in.next();
                } else {
                    c = in.nextInt();
                }
                if (!ok)
                    System.out.println("Incorrect move. Enter again:");
            }

            mv = new Move(r - 1, c - 1, cell);
            if (!pos.isValid(mv)) {
                System.out.println("Incorrect move. Enter again:");
                continue;
            }
            break;
        }
        return mv;
    }

    @Override
    public String toString() {
        return name;
    }
}
