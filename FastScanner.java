import java.io.*;
import java.nio.charset.StandardCharsets;

public class FastScanner{
    private static final int MAX_BUFFER_SIZE = 512;

    private CharacterWhitelist whitelist;
    private Reader reader;
    private char[] buffer = new char[MAX_BUFFER_SIZE];
    private int bufSize = 0;
    private int bufInd = MAX_BUFFER_SIZE;

    public FastScanner(InputStream stream, CharacterWhitelist whitelist) {
        this.whitelist = whitelist;
        this.reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        updateChar();
    }

    public FastScanner(InputStream stream){
        this(stream, new DefaultWhitelist());
    }

    public FastScanner(String s, CharacterWhitelist whitelist) {
        this.whitelist = whitelist;
        this.reader = new StringReader(s);
        updateChar();
    }

    public FastScanner(String s){
        this(s, new DefaultWhitelist());
    }

    private boolean updateChar() {
        ++bufInd;
        if(bufInd >= bufSize){
            buffer[0] = '\0';
            try{
                int x = reader.read(buffer);
                bufInd = 0;
                if (x == -1){
                    bufSize = 0;
                    return false;
                }
                bufSize = x;
            } catch (IOException e){
                System.err.println("In/out exception: " + e.getMessage());
                close();
                System.exit(1); // :NOTE: исключения
            }
        }
        return true;
    }

    public String getNext(boolean thisLine) {
        while(!whitelist.isWhitelistCharacter(buffer[bufInd]) || buffer[bufInd] == '\0'){
            if(thisLine && isLineSeparator() || !updateChar())
                return null;
        }
        StringBuilder s = new StringBuilder();
        while(whitelist.isWhitelistCharacter(buffer[bufInd]) && buffer[bufInd] != '\0'){
            s.append(buffer[bufInd]);
            if(!updateChar())
                break;
        }
        return s.toString();
    }

    public String getNext(){
        return getNext(false);
    }

    private boolean isLineSeparator() {
        int ln = System.lineSeparator().length();
        int last = bufSize - bufInd;
        try{
            while(last < ln && bufSize < MAX_BUFFER_SIZE){
                int x = reader.read(buffer, bufSize, MAX_BUFFER_SIZE - bufSize);
                if (x == -1)
                    return true;
                bufSize += x;
                last = bufSize - bufInd;
            }
            if(last < ln){
                for(int i=0; i<last; i++)
                    buffer[i] = buffer[bufInd + i];
                bufSize = last;
                bufInd = 0;
                while(last < ln){
                    int x = reader.read(buffer, last, MAX_BUFFER_SIZE - last);
                    if(x == -1)
                        return true;
                    bufSize += x;
                    last = bufSize - bufInd;
                }
            }
        } catch(IOException e){
            System.err.println("In/out exception: " + e.getMessage());
            close();
            System.exit(1);
        }
        StringBuilder s = new StringBuilder();
        for(int i=0; i<ln; ++i)
            s.append(buffer[bufInd + i]);
        return s.toString().equals(System.lineSeparator());
    }

    public String getNextLine() {
        StringBuilder s = new StringBuilder();
        boolean flag = buffer[bufInd] == '\0';
        while(!isLineSeparator()){
            s.append(buffer[bufInd]);
            updateChar();
        }
        for(int i=0; i<System.lineSeparator().length(); i++)
            updateChar();
        if(s.isEmpty() && flag)
            return null;
        return s.toString();
    }

    public void close() {
        try{
            reader.close();
        } catch (IOException e){
            System.err.println("In/out exception: " + e.getMessage());
            System.exit(1);
        }
    }
}
