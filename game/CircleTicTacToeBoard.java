package game;

public class CircleTicTacToeBoard extends TicTacToeBoard{
    public CircleTicTacToeBoard(int d, int k) {
        super(d, d, k);
        for(int i=0; i<d; i++){
            for(int j=0; j<d; j++){
                int x = i * 2 + 1;
                int y = j * 2 + 1;
                if((x - d) * (x - d) + (y - d) * (y - d) > d * d - 2)
                    changeCell(i, j, Cell.B);
            }
        }
        changeCell(d / 2, d / 2, Cell.E);
    }
}
