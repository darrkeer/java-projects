package expression;

public interface CharSource {
    char next();
    boolean hasNext();

    void reverse(int len);
}
