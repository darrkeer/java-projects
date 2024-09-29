import java.lang.System;
import java.util.Arrays;

public class ReverseSumHexDecAbc {
    static int[][] push2d(int[] x, int[][] dest, int size) {
        if (size == dest.length)
            dest = Arrays.copyOf(dest, size * 2);
        dest[size] = x;
        return dest;
    }

    static int[] push(int x, int[] dest, int size) {
        if (size == dest.length)
            dest = Arrays.copyOf(dest, size * 2);
        dest[size] = x;
        return dest;
    }

    static int parseNumber(String s){
        if (s.length() > 2 && s.charAt(0) == '0')
            return (int) Long.parseLong(s.substring(2), 16);
        StringBuilder sb = new StringBuilder(s);
        int sign = 1;
        if(sb.charAt(0) == '-'){
            sb = sb.deleteCharAt(0);
            sign = -1;
        }
        if(Character.isAlphabetic(sb.charAt(0))){
            for(int i=0; i<sb.length(); i++)
                sb.setCharAt(i, (char)('0' + sb.charAt(i) - 'a'));
        }
        return sign * (int) Long.parseLong(sb.toString());
    }

    static String toAbc(int x){
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        for(int i=0; i<sb.length(); i++){
            if(Character.isDigit(sb.charAt(i)))
                sb.setCharAt(i, (char)(sb.charAt(i) - '0' + 'a'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] matrix = new int[1][1];
        int[] line = new int[1];
        int[] sizes = new int[1];
        int[] last = new int[1];

        int matrixSize = 1;
        int lineSize = 1;
        int lastSize = 1;

        FastScanner linesc = new FastScanner(System.in);
        String line_s;
        while ((line_s = linesc.getNextLine()) != null) {
            FastScanner numsc = new FastScanner(line_s);
            String num_s;
            while ((num_s = numsc.getNext()) != null) {
                line = push(parseNumber(num_s), line, lineSize);
                ++lineSize;
            }

            matrix = push2d(line, matrix, matrixSize);
            sizes = push(lineSize, sizes, matrixSize);
            matrixSize++;

            line = new int[1];
            lineSize = 1;
        }

        for (int i = 1; i < matrixSize; i++) {
            while (lastSize < sizes[i]) {
                last = push(0, last, lastSize);
                matrix[0] = push(0, matrix[0], lastSize);
                lastSize++;
            }

            for (int j = 1; j < sizes[i]; j++) {
                int k = last[j];
                matrix[i][j] = matrix[i][j] + matrix[k][j] + matrix[i][j - 1] - matrix[k][j - 1];
                last[j] = i;

                System.out.print(toAbc(matrix[i][j]));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}