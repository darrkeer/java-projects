package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {

    private static final Set<Character> special = new HashSet<>();
    private static final List<Markup> heads = new ArrayList<>();
    private static final List<Markup> markups = new ArrayList<>();
    private static final Map<Character, String> replace = Map.of('<', "&lt;", '>', "&gt;", '&', "&amp;");

    static {
        special.add('*');
        special.add('_');
        special.add('-');
        special.add('`');
        special.add('>');
        special.add('<');
        special.add('}');
        special.add('{');
        special.add('&');

        markups.add(new Markup("**", "**", "<strong>", "</strong>"));
        markups.add(new Markup("__", "__", "<strong>", "</strong>"));
        markups.add(new Markup("*", "*", "<em>", "</em>"));
        markups.add(new Markup("_", "_", "<em>", "</em>"));
        markups.add(new Markup("--", "--", "<s>", "</s>"));
        markups.add(new Markup("`", "`", "<code>", "</code>"));
        markups.add(new Markup("<<", ">>", "<ins>", "</ins>"));
        markups.add(new Markup("}}", "{{", "<del>", "</del>"));

        heads.add(new Markup("", "", "<p>", "</p>"));
        for (int i = 1; i <= 6; i++) {
            heads.add(new Markup("#".repeat(i) + " ", "", "<h" + i + ">", "</h" + i + ">"));
        }
    }
    static boolean isSpecialChar(char c) {
        return special.contains(c);
    }

    static String getNextParagraph(BufferedReader r) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        String sep = System.lineSeparator();
        boolean start = true;
        while ((line = r.readLine()) != null) {
            if (line.isEmpty()) {
                if (start)
                    continue;
                else
                    break;
            }
            start = false;
            sb.append(line);
            sb.append(sep);
        }
        if (sb.length() > sep.length() && sep.equals(sb.substring(sb.length() - sep.length())))
            sb.delete(sb.length() - sep.length(), sb.length());
        return sb.toString();
    }

    public static Object repChar(char c){
        for (Map.Entry<Character, String> entry : replace.entrySet()) {
            Character from = entry.getKey();
            String to = entry.getValue();
            if (from.equals(c)) {
                return to;
            }
        }
        return c;
    }

    static void parseParagraph(String par, StringBuilder sb) {
        boolean[] was = new boolean[markups.size()];
        int[] last = new int[markups.size()];
        boolean[] spec = new boolean[par.length()];

        for(int i=0; i<par.length(); i++)
            spec[i] = isSpecialChar(par.charAt(i));

        int ind;
        for (int i = 0; i < markups.size(); i++) {
            ind = 0;
            while (ind < par.length()) {
                if (spec[ind] && (markups.get(i).comparePrefix(par, ind) || markups.get(i).compareSuffix(par, ind)))
                    last[i] = ind;
                if (par.charAt(ind) == '\\' && par.length() > ind + 1 && isSpecialChar(par.charAt(ind + 1)))
                    ind += 2;
                else
                    ind++;
            }
        }

        ind = 0;
        while (ind < par.length()) {
            boolean found = false;
            if(spec[ind]){
                for (int i = 0; i < markups.size(); i++) {
                    if (was[i] && markups.get(i).compareSuffix(par, ind)) {
                        ind += markups.get(i).writeSuffix(sb);
                        found = true;
                        was[i] = false;
                        break;
                    } else if (!was[i] && markups.get(i).comparePrefix(par, ind) && last[i] != ind) {
                        ind += markups.get(i).writePrefix(sb);
                        found = true;
                        was[i] = true;
                        break;
                    }
                }
            }
            if (found)
                continue;

            //another cases
            if (par.charAt(ind) == '\\' && par.length() > ind + 1 && spec[ind + 1]) {
                sb.append(repChar(par.charAt(ind + 1)));
                ind += 2;
                continue;
            }
            sb.append(repChar(par.charAt(ind)));
            ind++;
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect arguments count! Usage: Md2Html <in_file> <out_file>");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        StringBuilder text = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8))){

            String par;
            while (!(par = getNextParagraph(reader)).isEmpty()) {
                int h = 0;
                for (int i = 1; i < heads.size(); i++) {
                    if (heads.get(i).comparePrefix(par, 0)) {
                        h = i;
                        break;
                    }
                }
                heads.get(h).writePrefix(text);
                parseParagraph(par.substring(heads.get(h).getIndexPrefix().length()), text);
                heads.get(h).writeSuffix(text);
                text.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e){
            System.err.println("Input/Output files not found.");
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
            writer.write(text.toString());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
