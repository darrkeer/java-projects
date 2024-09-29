import java.io.*;
import java.util.*;

public class WordStatCountAffixL {

    public static boolean isChar(int c){
        return Character.isAlphabetic(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }
    public static LinkedHashMap<String, Integer> push(LinkedHashMap<String, Integer> mp, String word) {
        Integer cnt = mp.get(word);
        if(cnt != null)
            mp.put(word, cnt + 1);
        else
            mp.put(word, 1);
        return mp;
    }
    public static void main(String[] args) {
        if(args.length < 2) {
            System.err.println("Wrong arguments: you have to write <input.file> <output.file>\n");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];
        LinkedHashMap<String, Integer> mp = new LinkedHashMap<String, Integer>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "utf8"));
            try {
                StringBuilder s = new StringBuilder();
                int ch;
                while((ch = reader.read()) != -1) {
                    if(isChar(ch)) {
                        s.append(Character.toLowerCase((char)ch));
                        continue;
                    }
                    String word = s.toString();
                    s.setLength(0);
                    if(word.length() < 2)
                        continue;
                    String pref = word.substring(0, 2);
                    String suff = word.substring(word.length() - 2);
                    mp = push(mp, pref);
                    mp = push(mp, suff);
                    s.setLength(0);
                }
            } catch (IOException e) {
                System.err.println("In/out exception: " + e.getMessage());
            } finally {
                reader.close();
            }

            WordEntry words[] = new WordEntry[mp.size()];
            int ind = 0;
            for (Map.Entry<String, Integer> e : mp.entrySet()) {
                String word = e.getKey();
                Integer num = e.getValue();
                words[ind] = new WordEntry(word, num, ind);
                ind++;
            }
            Arrays.sort(words);

            BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(outputFile), "utf8"));
            try {
                for(WordEntry entry : words) {
                    writer.write(entry.word + " " + String.valueOf(entry.count));
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("In/out exception: " + e.getMessage());
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("In/out exception: " + e.getMessage());
        }
    }

    public static class WordEntry implements Comparable<WordEntry> {
        String word;
        int count;
        int ind;

        public WordEntry(String _word, int _count, int _ind) {
            word = _word;
            count = _count;
            ind = _ind;
        }

        @Override
        public int compareTo(WordEntry other) {
            if(this.count != other.count)
                return this.count - other.count;
            return this.ind - other.ind;
        }
    }
}