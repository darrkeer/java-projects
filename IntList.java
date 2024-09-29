import java.util.Arrays;

public class IntList {
    private int maxSize = 1;
    private int size = 0;
    private int[] lst = new int[maxSize];

    public IntList() {}

    public void append(int x){
        if(size + 1 >= maxSize){
            maxSize *= 2;
            lst = Arrays.copyOf(lst, maxSize);
        }
        size++;
        lst[size - 1] = x;
    }

    public int get(int i){
        return lst[i];
    }

    public int getSize(){
        return size;
    }
}