import java.io.*;
import java.util.*;

public class WsppSortedRFirst {
    private static class WsppStatistic {
        IntList occurrence;
        int count;
    }

    static String getReversed(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong arguments!");
            System.exit(1);
        }
        String inFile = args[0];
        String outFile = args[1];
        Map<String, IntList> mp = new TreeMap<>(); // общая стукртура
        Map<String, Integer> wordCnt = new LinkedHashMap<>();
        try {
            FastScanner sc = new FastScanner(new FileInputStream(inFile), new LetterWhitelist());
            String word;
            int cnt = 1;
            int prevLineCnt = 0;
            while ((word = sc.getNext()) != null) {
                do {
                    word = getReversed(word.toLowerCase());
                    wordCnt.put(word, wordCnt.getOrDefault(word, 0) + 1);
                    if (mp.get(word) == null) {
                        mp.put(word, new IntList());
                    }
                    IntList lst = mp.get(word);
                    if (lst.getSize() == 0 || lst.get(lst.getSize() - 1) <= prevLineCnt)
                        lst.append(cnt);
                    cnt++;
                } while ((word = sc.getNext(true)) != null);
                prevLineCnt = cnt - 1;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
            System.exit(1);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf8"));
            try {
                for (Map.Entry<String, IntList> entry : mp.entrySet()) {
                    String word = entry.getKey();
                    IntList lst = entry.getValue();
                    writer.write(getReversed(word) + " " + wordCnt.get(word) + " ");
                    for (int i = 0; i < lst.getSize(); i++) {
                        writer.write(Integer.toString(lst.get(i)));
                        if (i != lst.getSize() - 1) {
                            writer.write(" ");
                        } else {
                            writer.newLine();
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("In/out exception: " + e.getMessage());
                System.exit(1);
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cant modify output file: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("In/out exception: " + e.getMessage());
            System.exit(1);
        }
    }
}
