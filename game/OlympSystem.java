package game;

import java.util.*;

public class OlympSystem {
    final private Player[] players;
    final private Map<Integer, List<String>> places;

    final private int n;
    final private int m;
    final private int k;

    final private boolean isCircle;

    private OlympSystem(Player[] players, int n, int m, int k, boolean isCircle) {
        this.players = players;
        this.n = n;
        this.m = m;
        this.k = k;
        this.isCircle = isCircle;
        this.places = new TreeMap<>();
    }

    public OlympSystem(Player[] players, int n, int m, int k) {
        this(players, n, m, k, false);
    }

    public OlympSystem(Player[] players, int n, int k) {
        this(players, n, 0, k, true);
    }

    private int play(Player p1, Player p2) {
        System.out.println("Team X - " + p1);
        System.out.println("Team O - " + p2);
        int round = 1;
        while (true) {
            System.out.println("Round " + round + ". Begin!");
            Game game;
            if (!isCircle)
                game = new Game(p1, p2, new TicTacToeBoard(n, m, k), true);
            else
                game = new Game(p1, p2, new CircleTicTacToeBoard(n, k), true);
            int res = game.play();
            if (res == 1) {
                System.out.println("Player " + p1 + " wins!");
                return 1;
            }
            if (res == 2) {
                System.out.println("Player " + p2 + " wins!");
                return 2;
            }
            System.out.println("Draw. Rematch!");
        }
    }

    private void nextTour(ArrayList<Player> current) {
        if (current.size() == 1) {
            places.put(1, places.getOrDefault(1, new ArrayList<>()));
            places.get(1).add(current.get(0).toString());
            return;
        }
        Collections.shuffle(current);
        ArrayList<Player> qualified = new ArrayList<>();
        for (int i = 0; i < current.size(); i += 2) {
            int qi = 0;
            if (i + 1 < current.size()) {
                System.out.println("Game: " + current.get(i) + " and " + current.get(i + 1));
                qi = play(current.get(i), current.get(i + 1)) - 1;
            }
            System.out.println("Player " + current.get(i + qi) + " qualified to next round!");
            qualified.add(current.get(i + qi));
            if (i + (qi + 1) % 2 < current.size()) {
                int place = current.size() - current.size() / 2 + 1;
                places.put(place, places.getOrDefault(place, new ArrayList<>()));
                places.get(place).add(current.get(i + (qi + 1) % 2).toString());
            }
        }
        nextTour(qualified);
    }

    public void startOlymp() {
        System.out.println("Olympiad started!");
        nextTour(new ArrayList<>(List.of(players)));
        System.out.println("Olympiad ended!");
        System.out.println("Places:");
        for(Map.Entry<Integer, List<String>> entry : places.entrySet()){
            int place = entry.getKey();
            List<String> awardee = entry.getValue();
            for(String s : awardee)
                System.out.println(place + " - " + s);
        }
    }
}
