package game;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class OlympMain {
    public static void main(String[] args) {
        if (args.length != 5 && args.length != 4) {
            System.out.println("Incorrect arguments count. Usage: OlympMain <m> [n] <k> <players> <bots>");
            return;
        }

        int n = 0;
        int m = 0;
        int k = 0;
        int pc = 0;
        int bc = 0;
        try {
            if (args.length == 5) {
                n = Integer.parseInt(args[0]);
                m = Integer.parseInt(args[1]);
                k = Integer.parseInt(args[2]);
                pc = Integer.parseInt(args[3]);
                bc = Integer.parseInt(args[4]);
            }
            if (args.length == 4) {
                n = Integer.parseInt(args[0]);
                k = Integer.parseInt(args[1]);
                pc = Integer.parseInt(args[2]);
                bc = Integer.parseInt(args[3]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect arguments values.");
            return;
        }
        if (n < 0 || m < 0 || k < 0 || pc < 0 || bc < 0) {
            System.out.println("Incorrect arguments values.");
            return;
        }

        if (pc + bc == 0) {
            System.out.println("Not enough players!");
            return;
        }

        Player[] players = new Player[pc + bc];
        Scanner sc = new Scanner(System.in);
        Set<String> st = new HashSet<>();
        for (int i = 0; i < pc; i++) {
            while (true) {
                System.out.println("Enter player " + (i + 1) + " name:");
                String name = sc.next();
                if (st.contains(name)) {
                    System.out.println("Player with name '" + name + "' already in the game!");
                    continue;
                }
                System.out.println("Player '" + name + "' added!");
                st.add(name);
                players[i] = new HumanPlayer(name);
                break;
            }
        }

        for (int i = pc; i < pc + bc; i++)
            players[i] = new RandomPlayer();

        OlympSystem sys;
        if (args.length == 5) {
            sys = new OlympSystem(players, n, m, k);
        } else {
            sys = new OlympSystem(players, n, k);
        }
        sys.startOlymp();
    }
}
