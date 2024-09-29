import java.io.*;
import java.util.*;

public class WordStatInput {
    public static boolean isChar(char c) {
        return Character.isAlphabetic(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }

    public static void main(String[] args) throws IOException {

        String inputFile = args[0];
        String outputFile = args[1];

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(inputFile),
                        "utf8"
                )
        );

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(outputFile),
                        "utf8"
                )
        );

        LinkedHashMap<String, Integer> mp = new LinkedHashMap<String, Integer>();
        String line;
        while ((line = reader.readLine()) != null) {
            int skip = 0;

            for (int i = 0; i < line.length(); i++) {
                if (i < skip)
                    continue;
                char c = line.charAt(i);
                if (isChar(c)) {
                    int j = i + 1;
                    while (j < line.length() && isChar(line.charAt(j)))
                        j++;

                    String word = (line.substring(i, j)).toLowerCase();
                    if (mp.containsKey(word))
                        mp.put(word, mp.get(word) + 1);
                    else
                        mp.put(word, 1);

                    skip = j;
                }
            }
        }

        for (Map.Entry<String, Integer> e : mp.entrySet()) {
            String word = e.getKey();
            Integer num = e.getValue();

            writer.write(word + " " + String.valueOf(num));
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}