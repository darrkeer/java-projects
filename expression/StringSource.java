package expression;

public class StringSource implements CharSource{
    private final String s;
    private int ind = -1;

    private static char END = '\0';

    public StringSource(String s){
        this.s = s;
    }

    @Override
    public char next() {
        if(!hasNext())
            return END;
        return s.charAt(++ind);
    }

    @Override
    public boolean hasNext() {
        return ind < s.length() - 1;
    }

    @Override
    public void reverse(int len){
        ind -= len;
    }
}
