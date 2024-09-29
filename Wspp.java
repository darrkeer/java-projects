import java.io.*;
import java.util.*;

public class Wspp{
    public static void main(String[] args){
        if(args.length != 2){
            System.err.println("Wrong arguments!");
            System.exit(1);
        }
        String inFile = args[0];
        String outFile = args[1];
        Map<String, IntList> mp = new LinkedHashMap<>();
        try{
            FastScanner sc = new FastScanner(new FileInputStream(inFile), new LetterWhitelist());
            String word;
            int cnt = 1;
            while((word = sc.getNext()) != null){
                word = word.toLowerCase();
                if(mp.get(word) == null)
                    mp.put(word, new IntList());
                mp.get(word).append(cnt);
                cnt++;
            }
            sc.close();
        } catch (FileNotFoundException e){
            System.err.println("Input file not found: " + e.getMessage());
            System.exit(1);
        }

        try{
            BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(outFile), "utf8"));
            try{
                for (Map.Entry<String, IntList> entry : mp.entrySet()){
                    String word = entry.getKey();
                    IntList lst = entry.getValue();
                    writer.write(word + " " + String.valueOf(lst.getSize()) + " ");
                    for(int i=0; i<lst.getSize(); i++){
                        writer.write(String.valueOf(lst.get(i)));
                        if(i != lst.getSize() - 1)
                            writer.write(" ");
                        else
                            writer.newLine();
                    }
                }
            } catch (IOException e){
                System.err.println("In/out exception: " + e.getMessage());
                System.exit(1);
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e){
            System.err.println("Cant modify output file: " + e.getMessage());
            System.exit(1);
        } catch (IOException e){
            System.err.println("In/out exception: " + e.getMessage());
            System.exit(1);
        }
    }
}