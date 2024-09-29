import java.util.Scanner;
import java.lang.System;
import java.util.Arrays;

public class ReverseSumHexDec {
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

    public static void main(String[] args) {
        int[][] matrix = new int[1][1];
        int[] line = new int[1];
        int[] sizes = new int[1];
        int[] last = new int[1];

        int matrixSize = 1;
        int lineSize = 1;
        int lastSize = 1;

        Scanner linesc = new Scanner(System.in);
        while (linesc.hasNextLine()) {
            String s = linesc.nextLine();
            Scanner numsc = new Scanner(s);

            while (numsc.hasNext()) {
                String num = numsc.next();
                if (num.length() > 2 && num.charAt(0) == '0')
                    line = push((int) Long.parseLong(num.substring(2), 16), line, lineSize);
                else
                    line = push((int) Long.parseLong(num), line, lineSize);
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

                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}