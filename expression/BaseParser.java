package expression;

public class BaseParser {
    private final CharSource source;

    public static final char END = '\0';

    private char ch;
    private int amount;

    public BaseParser(CharSource source){
        this.source = source;
        this.amount = 0;
        ch = source.next();
    }

    public char peek(){
        return ch;
    }

    public void except(char excepted){
        if(!take(excepted)){
            throw new IllegalArgumentException("Excepted '" + excepted + "' found '" + ch + "'");
        }
    }

    public void except(String s){
        for(int i=0; i<s.length(); i++){
            except(s.charAt(i));
        }
    }

    public char take(){
        char res = ch;
        if (source.hasNext()){
            ch = source.next();
            amount++;
        }
        else{
            ch = END;
        }
        return res;
    }

    public void reverse(int len){
        source.reverse(len + 1);
        amount -= len + 1;
        ch = source.next();
    }

    public boolean take(char expected){
        if(peek() == expected){
            take();
            return true;
        }
        return false;
    }

    public int getAmount(){
        return amount;
    }
}
