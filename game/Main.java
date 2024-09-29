package game;

public class Main {
    public static void main(String[] args) {
        if(args.length != 3 && args.length != 2){
            System.out.println("Incorrect arguments count. Usage: Main <m> <n> <k> or Main <d> <k>.");
            return;
        }

        int n = 0;
        int m = 0;
        int k = 0;
        try{
            if(args.length == 3) {
                n = Integer.parseInt(args[0]);
                m = Integer.parseInt(args[1]);
                k = Integer.parseInt(args[2]);
            }
            if (args.length == 2) {
                n = Integer.parseInt(args[0]);
                k = Integer.parseInt(args[1]);
            }
        } catch (NumberFormatException e){
            System.out.println("Incorrect arguments values.");
            return;
        }
        if(n < 0 || m < 0 || k < 0){
            System.out.println("Incorrect arguments values.");
            return;
        }

        // :NOTE: formatting
        Game game;
        if (args.length == 3)
            game = new Game(new HumanPlayer("player"), new RandomPlayer(), new TicTacToeBoard(n, m, k), true);
        else
            game = new Game(new HumanPlayer("player"), new RandomPlayer(), new CircleTicTacToeBoard(n, k), true);
        int res = game.play();

        switch (res){
            case 1:
                System.out.println("X player wins!");
                break;
            case 2:
                System.out.println("O player wins!");
                break;
            case 0:
                System.out.println("Draw!");
                break;
        }
    }
}
